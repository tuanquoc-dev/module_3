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
                        <a href="/prescriptions?action=home" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-bootstrap"></i> <span class="ms-1 d-none d-sm-inline">Quản Lý Đơn Thuốc</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Quản Lý Thuốc</span>
                        </a>
                    </li>
                    <li>
                        <a href="/rooms?action=home" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Phòng Khám</span>
                        </a>
                    </li>
                    <li>
                        <a href="/medicalRecords?action=home" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Hồ Sơ Khám Bệnh</span>
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
            <h3>Quản Lý Thuốc</h3>

            <div class="d-flex mb-3">
                <a class="btn btn-primary me-2" href="/medicines?action=add">Thêm Thuốc</a>
                <form class="d-flex" action="/medicines" method="get">
                    <input type="hidden" name="action" value="search"/>
                    <input name="keyword" class="form-control me-2" type="search" placeholder="Tìm theo tên thuốc" value="${keyword}" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Tìm</button>
                </form>
            </div>

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Mã Thuốc</th>
                    <th>Tên Thuốc</th>
                    <th>Liều</th>
                    <th>Tần Suất</th>
                    <th>Số Ngày</th>
                    <th>Mã Đơn</th>
                    <th>Ngày Đơn</th>
                    <th>Thao Tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${medicines}">
                    <tr>
                        <td>${item.id_medicine}</td>
                        <td>${item.medicine_name}</td>
                        <td>${item.default_dosage}</td>
                        <td>${item.frequency}</td>
                        <td>${item.days}</td>
                        <td>${item.id_prescription}</td>
                        <td><c:out value="${item.prescription != null ? item.prescription.prescription_date : ''}"/></td>
                        <td>
                            <a href="/medicines?action=edit&idEdit=${item.id_medicine}" class="me-2"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a href="/medicines?action=delete&idDelete=${item.id_medicine}"><i class="fa-solid fa-trash"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous">
</script>
</html>
