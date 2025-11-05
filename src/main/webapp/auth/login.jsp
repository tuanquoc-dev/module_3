<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Login</title>
    <meta charset="UTF-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <h3 class="mb-3">Đăng nhập Admin</h3>

            <c:if test="${param.registered == '1'}">
                <div class="alert alert-success">Đăng ký thành công. Vui lòng đăng nhập.</div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/auth?action=login" method="post">
                <div class="mb-3">
                    <label class="form-label">Tên đăng nhập</label>
                    <input type="text" class="form-control" name="username" required/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Mật khẩu</label>
                    <input type="password" class="form-control" name="password" required/>
                </div>
                <div class="d-flex justify-content-between align-items-center">
                    <button class="btn btn-primary" type="submit">Đăng nhập</button>
                    <a href="${pageContext.request.contextPath}/auth?action=register">Đăng ký Admin</a>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
