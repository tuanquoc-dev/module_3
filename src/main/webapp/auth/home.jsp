<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  // simple session check here - nếu không có admin, redirect về login
  Object admin = session.getAttribute("admin");
  if (admin == null) {
    response.sendRedirect(request.getContextPath() + "/auth?action=login");
    return;
  }
%>
<html>
<head>
  <title>Admin Dashboard</title>
  <meta charset="UTF-8"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container py-4">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h3>Admin Dashboard</h3>
    <div>
      <span>Xin chào, <strong><c:out value="${((entity.Admin)sessionScope.admin).full_name != null ? ((entity.Admin)sessionScope.admin).full_name : ((entity.Admin)sessionScope.admin).username}"/></strong></span>
      <a class="btn btn-danger btn-sm ms-3" href="${pageContext.request.contextPath}/auth?action=logout">Đăng xuất</a>
    </div>
  </div>

  <!-- Nội dung admin -->
  <div class="card">
    <div class="card-body">
      Đây là trang quản trị. Bạn có thể thêm các link tới quản lý doctors/patients/medicines...
    </div>
  </div>
</div>
</body>
</html>
