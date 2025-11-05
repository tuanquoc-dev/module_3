package controller;

import entity.Admin;
import service.AdminService;
import util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AuthController", value = "/auth")
public class AuthController extends HttpServlet {

    private final AdminService adminService = new AdminService();

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

    private void doRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");
        String password2 = req.getParameter("password2");
        String fullName = req.getParameter("full_name");
        String email = req.getParameter("email");

        if (username.isEmpty() || password.isEmpty() || !password.equals(password2)) {
            req.setAttribute("error", "Dữ liệu không hợp lệ hoặc mật khẩu không khớp.");
            RequestDispatcher rd = req.getRequestDispatcher("auth/register.jsp");
            rd.forward(req, resp);
            return;
        }

        // kiểm tra username tồn tại
        if (adminService.findByUsername(username) != null) {
            req.setAttribute("error", "Tên đăng nhập đã tồn tại.");
            RequestDispatcher rd = req.getRequestDispatcher("auth/register.jsp");
            rd.forward(req, resp);
            return;
        }

        // tạo salt + hash
        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hashPassword(password, salt);

        entity.Admin admin = new entity.Admin(username, hash, salt, fullName, email);
        boolean ok = adminService.add(admin);
        if (ok) {
            resp.sendRedirect(req.getContextPath() + "/auth?action=login&registered=1");
        } else {
            req.setAttribute("error", "Đăng ký thất bại. Liên hệ admin.");
            RequestDispatcher rd = req.getRequestDispatcher("auth/register.jsp");
            rd.forward(req, resp);
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password");

        Admin admin = adminService.authenticate(username, password);
        if (admin != null) {
            HttpSession session = req.getSession();
            // lưu thông tin admin cần thiết vào session (không lưu password/salt)
            session.setAttribute("admin", admin);
            // set thời hạn session (ví dụ 30 phút)
            session.setMaxInactiveInterval(30*60);
            resp.sendRedirect(req.getContextPath() + "/doctors?action=home");
        } else {
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
            RequestDispatcher rd = req.getRequestDispatcher("auth/login.jsp");
            rd.forward(req, resp);
        }
    }
}
