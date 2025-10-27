package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import service.AppointmentService;
import service.DoctorService;
import service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name ="AppointmentController", value = "/appointments")
public class AppointmentController extends HttpServlet {

    private final AppointmentService appointmentService = new AppointmentService();
    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();

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
//            case "delete":
//                showFormDelete(req, resp);
//                break;
//            default:
//                showNotFoundErr(req, resp);
//                break;
        }
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("appointments/edit.jsp");
        int idEdit =  Integer.parseInt(req.getParameter("idEdit"));
        Appointment appointmentEdit = appointmentService.findById(idEdit);
        req.setAttribute("appointmentEdit", appointmentEdit);
        req.setAttribute("patients", patientService.findAll());
        req.setAttribute("doctors", doctorService.findAll());
        dispatcher.forward(req,resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = patientService.findAll();
        List<Doctor> doctors = doctorService.findAll();
        req.setAttribute("patients", patients);
        req.setAttribute("doctors", doctors);
        RequestDispatcher dispatcher = req.getRequestDispatcher("appointments/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Appointment> appointments = this.appointmentService.findAll();
        req.setAttribute("appointments", appointments);
        RequestDispatcher dispatcher = req.getRequestDispatcher("appointments/home.jsp");
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
//            case "delete":
//                showFormDelete(req, resp);
//                break;
//            default:
//                showNotFoundErr(req, resp);
//                break;
        }

    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_appointment = Integer.parseInt(req.getParameter("id_appointment"));
        Date appointment_date = java.sql.Date.valueOf(req.getParameter("appointment_date"));
        String status = req.getParameter("status");
        int id_patient = Integer.parseInt(req.getParameter("id_patient"));
        int id_doctor = Integer.parseInt(req.getParameter("id_doctor"));
        Patient patient = new Patient(id_patient);
        Doctor doctor = new Doctor(id_doctor);
        Appointment newAppointment = new Appointment(appointment_date,status, id_patient, id_doctor, patient, doctor);
        this.appointmentService.edit(id_appointment, newAppointment);
        resp.sendRedirect("/appointments?action=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date appointment_date = java.sql.Date.valueOf(req.getParameter("appointment_date"));
        String status = req.getParameter("status");
        int id_patient = Integer.parseInt(req.getParameter("id_patient"));
        int id_doctor = Integer.parseInt(req.getParameter("id_doctor"));
        Patient patient = new Patient(id_patient);
        Doctor doctor = new Doctor(id_doctor);
        Appointment appointment = new Appointment(appointment_date,status, id_patient, id_doctor, patient, doctor);
        this.appointmentService.add(appointment);
        resp.sendRedirect("/appointments?action=home");
    }

}
