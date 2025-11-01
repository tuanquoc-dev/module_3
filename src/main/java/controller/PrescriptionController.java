package controller;

import entity.MedicalRecord;
import entity.Prescription;
import service.MedicalRecordService;
import service.PrescriptionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "PrescriptionController", value = "/prescriptions")
public class PrescriptionController extends HttpServlet {

    private final PrescriptionService prescriptionService = new PrescriptionService();
    private final MedicalRecordService medicalRecordService = new MedicalRecordService();

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("prescriptions/delete.jsp");
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        Prescription prescriptionDelete = this.prescriptionService.findById(idDelete);
        req.setAttribute("prescriptionDelete", prescriptionDelete);
        dispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("prescriptions/edit.jsp");
        int idEdit =  Integer.parseInt(req.getParameter("idEdit"));
        Prescription prescriptionEdit = prescriptionService.findById(idEdit);
        req.setAttribute("prescriptionEdit", prescriptionEdit);
        req.setAttribute("medicalRecords", medicalRecordService.findAll());
        dispatcher.forward(req,resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MedicalRecord> medicalRecords = medicalRecordService.findAll();
        req.setAttribute("medicalRecords", medicalRecords);
        RequestDispatcher dispatcher = req.getRequestDispatcher("prescriptions/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prescription> prescriptions = this.prescriptionService.findAll();
        req.setAttribute("prescriptions", prescriptions);
        RequestDispatcher dispatcher = req.getRequestDispatcher("prescriptions/home.jsp");
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
        int id_prescription = Integer.parseInt(req.getParameter("id_prescription"));
        System.out.println("delete function" + id_prescription);
        this.medicalRecordService.delete(id_prescription);
        resp.sendRedirect("/prescriptions?action=home");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_prescription = Integer.parseInt(req.getParameter("id_prescription"));
        Date prescription_date = java.sql.Date.valueOf(req.getParameter("prescription_date"));
        String note = req.getParameter("note");
        int id_medical_record = Integer.parseInt(req.getParameter("id_medical_record"));
        MedicalRecord medicalRecord = new MedicalRecord(id_medical_record);
        Prescription newPrescription = new Prescription(id_prescription, prescription_date,note, id_medical_record, medicalRecord);
        this.prescriptionService.edit(id_prescription, newPrescription);
        resp.sendRedirect("/prescriptions?action=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date prescription_date = java.sql.Date.valueOf(req.getParameter("prescription_date"));
        String note = req.getParameter("note");
        int id_medical_record = Integer.parseInt(req.getParameter("id_medical_record"));
        MedicalRecord medicalRecord = new MedicalRecord(id_medical_record);
        Prescription prescription = new Prescription(prescription_date,note, id_medical_record, medicalRecord);
        this.prescriptionService.add(prescription);
        resp.sendRedirect("/prescriptions?action=home");
    }
}
