<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card p-4 shadow-lg" style="width: 400px;">
            <h3 class="text-center">Forgot password</h3>
            
            <%-- Hiển thị thông báo lỗi hoặc thành công --%>
            <% String message = (String) request.getAttribute("errorMessage"); %>
            <% if (message != null) { %>
                <div class="alert alert-info"><%= message %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/ForgotPasswordServlet" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Enter your gmail:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Send OTP</button>
            </form>

            <div class="text-center mt-3">
                <a href="login.jsp">Return to Login</a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
