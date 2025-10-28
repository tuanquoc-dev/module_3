package controller;

import entity.*;
import service.AppointmentService;
import service.MedicalRecordService;
import service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MedicalRecordController", value = "/medicalRecords")
public class MedicalRecordController extends HttpServlet {

    private final MedicalRecordService medicalRecordService = new MedicalRecordService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final RoomService roomService = new RoomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "home":
                showHome(req, resp);
                break;
            case "add":
                showFormAdd(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
            case "delete":
                showFormDelete(req, resp);
                break;
            default:
                showNotFoundErr(req, resp);
                break;
        }
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicalRecords/delete.jsp");
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        MedicalRecord medicalRecordDelete = this.medicalRecordService.findById(idDelete);
        req.setAttribute("medicalRecordDelete", medicalRecordDelete);
        dispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicalRecords/edit.jsp");
        int idEdit =  Integer.parseInt(req.getParameter("idEdit"));
        MedicalRecord medicalRecordEdit = medicalRecordService.findById(idEdit);
        req.setAttribute("medicalRecordEdit", medicalRecordEdit);
        req.setAttribute("appointments", appointmentService.findAll());
        req.setAttribute("rooms", roomService.findAll());
        dispatcher.forward(req,resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Appointment> appointments = appointmentService.findAll();
        List<Room> rooms = roomService.findAll();
        req.setAttribute("appointments", appointments);
        req.setAttribute("rooms", rooms);
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicalRecords/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MedicalRecord> medicalRecords = this.medicalRecordService.findAll();
        req.setAttribute("medicalRecords", medicalRecords);
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicalRecords/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void showNotFoundErr(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("errors/notFound.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                add(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            default:
                showNotFoundErr(req, resp);
                break;
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id_medical_record = Integer.parseInt(req.getParameter("id_medical_record"));
        System.out.println("delete function" + id_medical_record);
        this.medicalRecordService.delete(id_medical_record);
        resp.sendRedirect("/medicalRecords?action=home");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_medical_record = Integer.parseInt(req.getParameter("id_medical_record"));
        Date exam_date = java.sql.Date.valueOf(req.getParameter("exam_date"));
        String symptoms = req.getParameter("symptoms");
        String diagnosis = req.getParameter("diagnosis");
        String note = req.getParameter("note");
        int id_appointment = Integer.parseInt(req.getParameter("id_appointment"));
        int id_room = Integer.parseInt(req.getParameter("id_room"));
        Appointment appointment = new Appointment(id_appointment);
        Room room = new Room(id_room);
        MedicalRecord newMedicalRecord  = new MedicalRecord(exam_date,symptoms,diagnosis,note, id_appointment, id_room, appointment, room);
        this.medicalRecordService.edit(id_medical_record, newMedicalRecord);
        resp.sendRedirect("/medicalRecords?action=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date exam_date = java.sql.Date.valueOf(req.getParameter("exam_date"));
        String symptoms = req.getParameter("symptoms");
        String diagnosis = req.getParameter("diagnosis");
        String note = req.getParameter("note");
        int id_appointment = Integer.parseInt(req.getParameter("id_appointment"));
        int id_room = Integer.parseInt(req.getParameter("id_room"));
        Appointment appointment = new Appointment(id_appointment);
        Room room = new Room(id_room);
        MedicalRecord medicalRecord = new MedicalRecord(exam_date,symptoms,diagnosis,note, id_appointment, id_room, appointment, room);
        this.medicalRecordService.add(medicalRecord);
        resp.sendRedirect("/medicalRecords?action=home");
    }
}
