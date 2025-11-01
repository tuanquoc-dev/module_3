package controller;

import entity.Medicine;
import entity.Prescription;
import service.MedicineService;
import service.PrescriptionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MedicineController", value = "/medicines")
public class MedicineController extends HttpServlet {

    private final MedicineService medicineService = new MedicineService();
    private final PrescriptionService prescriptionService = new PrescriptionService();

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
            case "search":
                search(req, resp);
                break;
            default:
                showNotFoundErr(req, resp);
                break;
        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Medicine> medicines;
        if (keyword == null || keyword.trim().isEmpty()) {
            medicines = medicineService.findAll();
        } else {
            medicines = medicineService.findByName(keyword.trim());
        }
        req.setAttribute("medicines", medicines);
        req.setAttribute("keyword", keyword);
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicines/home.jsp");
        dispatcher.forward(req, resp);
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicines/delete.jsp");
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        Medicine medicineDelete = this.medicineService.findById(idDelete);
        req.setAttribute("medicineDelete", medicineDelete);
        dispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicines/edit.jsp");
        int idEdit =  Integer.parseInt(req.getParameter("idEdit"));
        Medicine medicineEdit = medicineService.findById(idEdit);
        req.setAttribute("medicineEdit", medicineEdit);
        req.setAttribute("prescriptions", prescriptionService.findAll());
        dispatcher.forward(req,resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prescription> prescriptions = prescriptionService.findAll();
        req.setAttribute("prescriptions", prescriptions);
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicines/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Medicine> medicines = this.medicineService.findAll();
        req.setAttribute("medicines", medicines);
        RequestDispatcher dispatcher = req.getRequestDispatcher("medicines/home.jsp");
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
        int id_medicine = Integer.parseInt(req.getParameter("id_medicine"));
        System.out.println("delete function" + id_medicine);
        this.medicineService.delete(id_medicine);
        resp.sendRedirect("/medicines?action=home");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id_medicine = Integer.parseInt(req.getParameter("id_medicine"));
        String medicine_name = req.getParameter("medicine_name");
        String default_dosage = req.getParameter("default_dosage");
        String frequency = req.getParameter("frequency");
        int days = Integer.parseInt(req.getParameter("days"));
        int id_prescription = Integer.parseInt(req.getParameter("id_prescription"));
        Prescription prescription = new Prescription(id_prescription);
        Medicine newMedicine = new Medicine(id_medicine, medicine_name, default_dosage, frequency, days, id_prescription, prescription);
        // sửa: truyền id_medicine làm id để edit
        this.medicineService.edit(id_medicine, newMedicine);
        resp.sendRedirect("/medicines?action=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String medicine_name = req.getParameter("medicine_name");
        String default_dosage = req.getParameter("default_dosage");
        String frequency = req.getParameter("frequency");
        int days = Integer.parseInt(req.getParameter("days"));
        int id_prescription = Integer.parseInt(req.getParameter("id_prescription"));
        Prescription prescription = new Prescription(id_prescription);
        Medicine medicine = new Medicine(medicine_name, default_dosage, frequency, days, id_prescription, prescription);
        this.medicineService.add(medicine);
        resp.sendRedirect("/medicines?action=home");
    }
}
