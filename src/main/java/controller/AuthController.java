package controller;

import entity.Admin;
import entity.User;
import service.AdminService;
import service.UserService;
import util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AuthController", value = "/auth")
public class AuthController extends HttpServlet {

    private final AdminService adminService = new AdminService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "register":
                showRegister(req, resp);
                break;
            case "login":
                showLogin(req, resp);
                break;
            case "logout":
                doLogout(req, resp);
                break;
            default:
                showLogin(req, resp);
                break;
        }
    }

    private void showRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("auth/register.jsp");
        rd.forward(req, resp);
    }

    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("auth/login.jsp");
        rd.forward(req, resp);
    }

    private void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/auth?action=login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "register":
                doRegister(req, resp);
                break;
            case "login":
                doLogin(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/auth?action=login");
                break;
        }
    }

    // ===================== ĐĂNG KÝ =====================
    private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");
        String role = req.getParameter("role"); // "admin" hoặc "user"

        if (username.isEmpty() || password.isEmpty() || !password.equals(password2)) {
            req.setAttribute("error", "Dữ liệu không hợp lệ hoặc mật khẩu không khớp.");
            RequestDispatcher rd = req.getRequestDispatcher("auth/register.jsp");
            rd.forward(req, resp);
            return;
        }

        // Kiểm tra username trùng trong cả admin và user
        boolean exists = (adminService.findByUsername(username) != null) ||
                (userService.findByUsername(username) != null);
        if (exists) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại.");
            RequestDispatcher rd = req.getRequestDispatcher("auth/register.jsp");
            rd.forward(req, resp);
            return;
        }

        // tạo salt + hash
        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);

        boolean ok = false;
        if ("user".equalsIgnoreCase(role)) {
            User user = new User(username, hash, salt, fullName, email);
            ok = userService.add(user);
        } else {
            Admin admin = new Admin(username, hash, salt, fullName, email);
            ok = adminService.add(admin);
        }

        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login&registered=1");
        } else {
            req.setAttribute("error", "Đăng ký thất bại. Liên hệ quản trị.");
            RequestDispatcher rd = req.getRequestDispatcher("auth/register.jsp");
            rd.forward(req, resp);
        }
    }

    // ===================== ĐĂNG NHẬP =====================
    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");
        String role = req.getParameter("role"); // "admin" hoặc "user"

        if ("user".equalsIgnoreCase(role)) {
            User user = userService.authenticate(username, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(30 * 60);
                resp.sendRedirect(req.getContextPath() + "auth/dashboard.jsp"); // dashboard người dùng
                return;
            }
        } else {
            Admin admin = adminService.authenticate(username, password);
            if (admin != null) {
                HttpSession session = req.getSession();
                session.setAttribute("admin", admin);
                session.setMaxInactiveInterval(30 * 60);
                resp.sendRedirect(req.getContextPath() + "/doctors?action=home"); // trang admin
                return;
            }
        }

        // Nếu sai
        req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
        RequestDispatcher rd = req.getRequestDispatcher("auth/login.jsp");
        rd.forward(req, resp);
    }
}
