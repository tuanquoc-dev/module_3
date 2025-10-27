package controller;

import entity.Patient;
import service.PatientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "PatientController", value = "/patients")
public class PatientController extends HttpServlet {

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
            case "delete":
                showFormDelete(req, resp);
                break;
            default:
                showNotFoundErr(req, resp);
                break;
        }
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("patients/delete.jsp");
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        Patient patientDelete = this.patientService.findById(idDelete);
        req.setAttribute("patientDelete", patientDelete);
        dispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("patients/edit.jsp");
        int idEdit = Integer.parseInt(req.getParameter("idEdit"));
        Patient patientEdit = this.patientService.findById(idEdit);
        req.setAttribute("patientEdit", patientEdit);
        dispatcher.forward(req, resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("patients/add.jsp");
        dispatcher.forward(req,resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Patient> patients = this.patientService.findAll();
        req.setAttribute("patients", patients);
        RequestDispatcher dispatcher = req.getRequestDispatcher("patients/home.jsp");
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

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_patient = Integer.parseInt(req.getParameter("id_patient"));
        System.out.println("delete function" + id_patient);
        this.patientService.delete(id_patient);
        resp.sendRedirect("/patients?action=home");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id_patient = Integer.parseInt(req.getParameter("id_patient"));
        String name =  req.getParameter("name");
        Date birth_date = Date.valueOf(req.getParameter("birth_date"));
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Patient newPatient = new Patient(id_patient, name, birth_date, gender, phone, address);
        this.patientService.edit(id_patient, newPatient);
        resp.sendRedirect("/patients?action=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name =  req.getParameter("name");
        Date birth_date = Date.valueOf(req.getParameter("birth_date"));
        String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        Patient patient = new Patient(name, birth_date, gender, phone, address);
        this.patientService.add(patient);
        resp.sendRedirect("/patients?action=home");
    }
}
