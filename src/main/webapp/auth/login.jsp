<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <h3 class="mb-3">Đăng nhập</h3>

            <c:if test="${param.registered == '1'}">
                <div class="alert alert-success">Đăng ký thành công. Vui lòng đăng nhập.</div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form method="post" action="${pageContext.request.contextPath}/auth?action=login">
                <div class="mb-3">
                    <label>Tên đăng nhập</label>
                    <input type="text" name="username" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label>Mật khẩu</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label>Vai trò</label><br>
                    <label><input type="radio" name="role" value="user" checked> Người dùng</label>
                    <label class="ms-3"><input type="radio" name="role" value="admin"> Quản trị</label>
                </div>
                <button type="submit" class="btn btn-primary">Đăng nhập</button>
                <a href="auth/register.jsp" class="btn btn-primary">Đăng Kí</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
