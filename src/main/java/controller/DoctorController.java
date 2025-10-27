package controller;

import entity.Doctor;
import service.DoctorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DoctorController", value = "/doctors")
public class DoctorController extends HttpServlet {

    private final DoctorService doctorService = new DoctorService();

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("doctors/delete.jsp");
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        Doctor doctorDelete = this.doctorService.findById(idDelete);
        req.setAttribute("doctorDelete", doctorDelete);
        dispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("doctors/edit.jsp");
        int idEdit = Integer.parseInt(req.getParameter("idEdit"));
        Doctor doctorEdit = this.doctorService.findById(idEdit);
        req.setAttribute("doctorEdit", doctorEdit);
        dispatcher.forward(req, resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("doctors/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Doctor> doctors = this.doctorService.findAll();
        req.setAttribute("doctors", doctors);
        RequestDispatcher dispatcher = req.getRequestDispatcher("doctors/home.jsp");
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
        int id_doctor = Integer.parseInt(req.getParameter("id_doctor"));
        System.out.println("delete function" + id_doctor);
        this.doctorService.delete(id_doctor);
        resp.sendRedirect("/doctors?action=home");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id_doctor = Integer.parseInt(req.getParameter("id_doctor"));
        String name = req.getParameter("name");
        String specialty = req.getParameter("specialty");
        String qualification = req.getParameter("qualification");
        String phone = req.getParameter("phone");
        Doctor newDoctor = new Doctor(id_doctor, name, specialty, qualification, phone);
        this.doctorService.edit(id_doctor, newDoctor);
        resp.sendRedirect("/doctors?action=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String specialty = req.getParameter("specialty");
        String qualification = req.getParameter("qualification");
        String phone = req.getParameter("phone");
        Doctor doctor = new Doctor(name, specialty, qualification, phone);
        this.doctorService.add(doctor);
        resp.sendRedirect("/doctors?action=home");
    }
}
