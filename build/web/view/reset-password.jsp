<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card p-4 shadow-lg" style="width: 400px;">
            <h3 class="text-center">Reset Password</h3>

            <%-- Kiểm tra nếu không có email trong session, điều hướng về forgot-password.jsp --%>
            <% if (session.getAttribute("email") == null) { %>
                <script>
                    alert("Bạn chưa xác thực OTP. Hãy nhập email trước!");
                    window.location.href = "forgot-password.jsp";
                </script>
            <% } %>

            <%-- Hiển thị thông báo nếu có lỗi --%>
            <% String message = (String) request.getAttribute("message"); %>
            <% if (message != null) { %>
                <div class="alert alert-danger"><%= message %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/ResetPasswordServlet" method="post" onsubmit="return validatePassword()">
                <div class="mb-3">
                    <label for="password" class="form-label">New password:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>

                <div class="mb-3">
                    <label for="confirmPassword" class="form-label">Confirm password:</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                </div>

                <button type="submit" class="btn btn-primary w-100">Change password</button>
            </form>

            <div class="text-center mt-3">
                <a href="login.jsp">Return to Login</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        function validatePassword() {
            let password = document.getElementById("password").value;
            let confirmPassword = document.getElementById("confirmPassword").value;

            if (password.length < 6) {
                alert("Mật khẩu phải có ít nhất 6 ký tự!");
                return false;
            }

            if (password !== confirmPassword) {
                alert("Mật khẩu xác nhận không khớp!");
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
