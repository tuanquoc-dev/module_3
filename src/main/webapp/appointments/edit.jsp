<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Doctor</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
          integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container-fluid">
    <div class="row flex-nowrap">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
            <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                <a href="/"
                   class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <span class="fs-5 d-none d-sm-inline">Bảng Điều Khiển</span>
                </a>
                <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start"
                    id="menu">
                    <li class="nav-item">
                        <a href="#" class="nav-link align-middle px-0">
                            <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Tổng Quan</span>
                        </a>
                    </li>
                    <li>
                        <a href="#submenu1" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-speedometer2"></i> <span class="ms-1 d-none d-sm-inline">Lịch Khám</span>
                        </a>
                    </li>
                    <li>
                        <a href="/doctors?action=home" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-table"></i> <span class="ms-1 d-none d-sm-inline">Bác Sĩ</span></a>
                    </li>
                    <li>
                        <a href="/patients?action=home" class="nav-link px-0 align-middle ">
                            <i class="fs-4 bi-bootstrap"></i> <span class="ms-1 d-none d-sm-inline">Bệnh Nhân</span></a>
                    </li>
                    <li>
                        <a href="#submenu3" data-bs-toggle="collapse" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-grid"></i> <span class="ms-1 d-none d-sm-inline">Quản Lý Đơn Thuốc</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Quản Lý Thuốc</span>
                        </a>
                    </li>
                </ul>
                <hr>
                <div class="dropdown pb-4">
                    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle"
                       id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30"
                             class="rounded-circle">
                        <span class="d-none d-sm-inline mx-1">loser</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="#">Settings</a></li>
                        <li><a class="dropdown-item" href="#">Profile</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">Sign out</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col py-3">
            <h3>Sửa Lịch Khám</h3>
            <form action="/appointments?action=edit" method="post">
                <div class="mb-3">
                    <label for="id_appointment" class="form-label">Mã Lịch Hẹn</label>
                    <input type="number" id="id_appointment" name="id_appointment" class="form-control" value="${appointmentEdit.id_appointment}" readonly/>
                </div>

                <div class="mb-3">
                    <label for="appointment_date" class="form-label">Ngày Hẹn</label>
                    <input type="date" id="appointment_date" name="appointment_date" class="form-control" value="${appointmentEdit.appointment_date}" />
                </div>

                <div class="mb-3">
                    <label for="status" class="form-label">Trạng Thái</label>
                    <select name="status" id="status" class="form-select">
                            <option value="Đã Xác Nhận" <c:if test="${appointmentEdit != null && appointmentEdit.status == 'Đã Xác Nhận'}">selected</c:if>>Đã Xác Nhận</option>
                            <option value="Chưa Xác Nhận" <c:if test="${appointmentEdit != null && appointmentEdit.status == 'Chưa Xác Nhận'}">selected</c:if>>Chưa Xác Nhận</option>
                            <option value="Đã hủy" <c:if test="${appointmentEdit != null && appointmentEdit.status == 'Đã hủy'}">selected</c:if>>Đã hủy</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="id_patient" class="form-label">Bệnh Nhân</label>
                    <select name="id_patient" id="id_patient" class="form-select" >
                        <c:forEach var="item" items="${patients}">
                            <option value="${item.id_patient}"
                                    <c:if test="${appointmentEdit.id_patient == item.id_patient}">selected</c:if>>
                                    ${item.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="id_doctor" class="form-label">Bác Sĩ</label>
                    <select name="id_doctor" id="id_doctor" class="form-select" >
                        <c:forEach var="item" items="${doctors}">
                            <option value="${item.id_doctor}"
                                    <c:if test="${appointmentEdit.id_doctor == item.id_doctor}">selected</c:if>>
                                    ${item.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous">
</script>
</html>
