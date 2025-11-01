<%--
  Created by IntelliJ IDEA.
  User: vuquo
  Date: 10/25/2025
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Patient</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">
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
                    <li>
                        <a href="#" class="nav-link px-0 align-middle">
                            <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Phòng Khám</span>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="nav-link px-0 align-middle">
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
            <h3>Quản Lý Đơn Thuốc</h3>
            <button type="button" class="btn btn-primary"><a href="/prescriptions?action=add">Thêm Đơn Thuốc</a></button>
            <table class="table">
                <thead>
                <tr>
                    <th>Mã Đơn Thuốc</th>
                    <th>Ngày Kê Đơn</th>
                    <th>Ghi Chú</th>
                    <th>Ngày Kiểm Tra</th>
                    <th>Thao Tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${prescriptions}">
                    <tr>
                        <th scope="row">${item.id_prescription}</th>
                        <th>${item.prescription_date}</th>
                        <td>${item.note}</td>
                        <td>${item.medicalRecord.exam_date}</td>
                        <td>
                            <a href="/prescriptions?action=edit&idEdit=${item.id_prescription}"><i class="fa-solid fa-pen-to-square"></i></a>
                            <a href="/prescriptions?action=delete&idDelete=${item.id_prescription}"><i class="fa-solid fa-trash"></i></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <!-- Modal markup với id -->
            <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteLabel">Xóa Đơn Thuốc</h5>
                            <a href="${pageContext.request.contextPath}/prescriptions?action=home" class="btn-close"></a>
                        </div>
                        <div class="modal-body">
                            <p>Bạn Có Chắc Muốn Xóa Đơn Thuốc
                                <strong><c:out value="${prescriptionDelete != null ? prescriptionDelete.id_prescription : '---'}"/></strong>?
                            </p>
                        </div>
                        <div class="modal-footer">
                            <a href="${pageContext.request.contextPath}/prescriptions?action=home" class="btn btn-secondary">Quay lại</a>

                            <form id="deleteForm" action="${pageContext.request.contextPath}/prescriptions?action=delete" method="post" style="display:inline;">
                                <input type="hidden" name="id_prescription" value="<c:out value='${prescriptionDelete.id_prescription}'/>" />
                                <!-- phải là submit để gửi form -->
                                <button type="submit" class="btn btn-danger">Xóa</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- end col -->
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // Khi trang load, tự động show modal nếu tồn tại phần tử modal
    document.addEventListener('DOMContentLoaded', function () {
        var modalEl = document.getElementById('deleteModal');
        if (modalEl) {
            var modal = new bootstrap.Modal(modalEl, {
                backdrop: 'static' // tùy chọn: click outside không đóng
            });
            modal.show();
        }
    });
</script>
</body>
</html>

</html>
