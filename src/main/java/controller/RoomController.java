package controller;

import entity.Doctor;
import entity.Room;
import service.RoomService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name= "RoomController", value = "/rooms")
public class RoomController extends HttpServlet {

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("rooms/delete.jsp");
        int idDelete = Integer.parseInt(req.getParameter("idDelete"));
        Room roomDelete = this.roomService.findById(idDelete);
        req.setAttribute("roomDelete", roomDelete);
        dispatcher.forward(req, resp);
    }

    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("rooms/edit.jsp");
        int idEdit = Integer.parseInt(req.getParameter("idEdit"));
        Room roomEdit = this.roomService.findById(idEdit);
        req.setAttribute("roomEdit", roomEdit);
        dispatcher.forward(req, resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("rooms/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> rooms = this.roomService.findAll();
        req.setAttribute("rooms", rooms);
        RequestDispatcher dispatcher = req.getRequestDispatcher("rooms/home.jsp");
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
        int id_room = Integer.parseInt(req.getParameter("id_room"));
        this.roomService.delete(id_room);
        resp.sendRedirect("/rooms?action=home");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id_room = Integer.parseInt(req.getParameter("id_room"));
        String room_name = req.getParameter("room_name");
        String department = req.getParameter("department");
        String equipment = req.getParameter("equipment");
        Room newRoom = new Room(id_room, room_name, department, equipment);
        this.roomService.edit(id_room, newRoom);
        resp.sendRedirect("/rooms?action=home");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String room_name = req.getParameter("room_name");
        String department = req.getParameter("department");
        String equipment = req.getParameter("equipment");
        Room room = new Room(room_name, department, equipment);
        this.roomService.add(room);
        resp.sendRedirect("/rooms?action=home");
    }

}
