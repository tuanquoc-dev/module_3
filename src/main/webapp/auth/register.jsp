<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Admin Register</title>
  <meta charset="UTF-8"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <h3 class="mb-3">Đăng ký Admin</h3>

      <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
      </c:if>

      <form method="post" action="${pageContext.request.contextPath}/auth?action=register">
        <div class="mb-3">
          <label>Tên đăng nhập</label>
          <input type="text" name="username" class="form-control" required>
        </div>
        <div class="mb-3">
          <label>Mật khẩu</label>
          <input type="password" name="password" class="form-control" required>
        </div>
        <div class="mb-3">
          <label>Nhập lại mật khẩu</label>
          <input type="password" name="password2" class="form-control" required>
        </div>
        <div class="mb-3">
          <label>Họ tên</label>
          <input type="text" name="full_name" class="form-control">
        </div>
        <div class="mb-3">
          <label>Email</label>
          <input type="email" name="email" class="form-control">
        </div>
        <div class="mb-3">
          <label>Vai trò</label><br>
          <label><input type="radio" name="role" value="user" checked> Người dùng</label>
          <label class="ms-3"><input type="radio" name="role" value="admin"> Quản trị</label>
        </div>
        <button type="submit" class="btn btn-primary">Đăng ký</button>
        <a href="/auth?action=login" class="btn btn-primary">Quay Lại</a>
      </form>

    </div>
  </div>
</div>
</body>
</html>
