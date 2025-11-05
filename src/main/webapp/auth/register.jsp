<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

      <form action="${pageContext.request.contextPath}/auth?action=register" method="post">
        <div class="mb-3">
          <label class="form-label">Tên đăng nhập</label>
          <input type="text" class="form-control" name="username" required/>
        </div>
        <div class="mb-3">
          <label class="form-label">Mật khẩu</label>
          <input type="password" class="form-control" name="password" required/>
        </div>
        <div class="mb-3">
          <label class="form-label">Nhập lại mật khẩu</label>
          <input type="password" class="form-control" name="password2" required/>
        </div>
        <div class="mb-3">
          <label class="form-label">Họ & Tên</label>
          <input type="text" class="form-control" name="full_name"/>
        </div>
        <div class="mb-3">
          <label class="form-label">Email</label>
          <input type="email" class="form-control" name="email"/>
        </div>
        <div class="d-flex justify-content-between">
          <button class="btn btn-success" type="submit">Đăng ký</button>
          <a href="${pageContext.request.contextPath}/auth?action=login" class="btn btn-secondary">Quay lại đăng nhập</a>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
