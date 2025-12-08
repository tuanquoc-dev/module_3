<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Kiểm tra đăng nhập
    entity.User user = (entity.User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/auth?action=login");
        return;
    }
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Trang Người Dùng - Phòng Khám ABC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="pricing.css">
</head>

<body class="bg-light">

<!-- Header -->
<header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 me-md-auto fw-bold text-primary">Phòng Khám ABC</h5>
    <nav class="my-2 my-md-0 me-md-3">
        <a class="p-2 text-dark" href="#">Trang Chủ</a>
        <a class="p-2 text-dark" href="#">Dịch Vụ</a>
        <a class="p-2 text-dark" href="#">Bác Sĩ</a>
        <a class="p-2 text-dark" href="#">Giới Thiệu</a>
        <a class="p-2 text-dark" href="#">Liên Hệ</a>
        <a class="p-2 text-dark fw-semibold" href="#">Đặt Lịch Ngay</a>
    </nav>
    <div class="d-flex align-items-center">
        <span class="me-3 text-dark">👋 Xin chào, <strong><%= user.getUsername() %></strong></span>
        <a class="btn btn-outline-danger" href="<%= request.getContextPath() %>/auth?action=logout">Đăng xuất</a>
    </div>
</header>

<!-- Intro -->
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-5 fw-bold text-primary">Đặt Lịch Khám Trực Tuyến</h1>
    <p class="text-muted">Hãy điền đầy đủ thông tin bên dưới để đặt lịch khám tại Phòng Khám ABC</p>
</div>

<!-- Main form -->
<main class="container">
    <div class="col-lg-8 mx-auto">
        <div class="card shadow-sm p-4 mb-5 bg-white rounded">
            <form action="<%= request.getContextPath() %>/booking" method="post" novalidate>
                <div class="row g-3">
                    <div class="col-md-6">
                        <label class="form-label">Họ Tên</label>
                        <input type="text" class="form-control" name="fullName" value="<%= user.getFull_name() %>" >
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Email</label>
                        <input type="email" class="form-control" name="email" value="<%= user.getEmail() %>" >
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Ngày Sinh</label>
                        <input type="date" class="form-control" name="dob" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Giới Tính</label>
                        <select class="form-select" name="gender" required>
                            <option selected disabled>Chọn...</option>
                            <option>Nam</option>
                            <option>Nữ</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Địa Chỉ</label>
                        <input type="text" class="form-control" name="address" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Số Điện Thoại</label>
                        <input type="tel" class="form-control" name="phone" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Khoa Khám</label>
                        <input type="text" class="form-control" name="department" required>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label">Ngày Khám</label>
                        <input type="date" class="form-control" name="date" required>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Triệu Chứng</label>
                        <textarea class="form-control" name="symptom" rows="2" required></textarea>
                    </div>
                </div>
                <div class="d-grid mt-4">
                    <button class="btn btn-primary btn-lg" type="submit">Gửi Yêu Cầu Đặt Lịch</button>
                </div>
            </form>
        </div>
    </div>
</main>

<!-- Footer -->
<footer class="pt-4 my-md-5 pt-md-5 border-top text-center text-muted">
    <small>&copy; 2025 Phòng Khám ABC — Designed by tuanq</small>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
