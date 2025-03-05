<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>OTP Authentication</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card p-4 shadow-lg" style="width: 400px;">
            <h3 class="text-center">OTP authentication</h3>

            <%-- Hiển thị thông báo lỗi nếu có --%>
            <% String message = (String) request.getAttribute("errorMessage"); %>
            <% if (message != null) { %>
                <div class="alert alert-danger"><%= message %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/VerifyOtpServlet" method="post">
                <div class="mb-3">
                    <label for="otp" class="form-label">Please enter OTP code:</label>
                    <input type="text" class="form-control" id="otp" name="otp" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Submit</button>
            </form>

            <div class="text-center mt-3">
                <a href="view/forgot-password.jsp">Send OTP again</a> | 
                <a href="view/login.jsp">Return Login</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
