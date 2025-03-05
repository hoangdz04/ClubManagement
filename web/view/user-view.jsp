<%-- 
    Document   : user-view
    Created on : Mar 4, 2025, 9:41:34 PM
    Author     : A
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="request" class="model.Users" />
<html>
<head>
    
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #007bff, #00c6ff);
            color: #fff;
        }
        .fade-in {
            animation: fadeIn 2s;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        .card {
            background: #fff;
            color: #000;
            border: none;
            border-radius: 15px;
        }
        .card-title {
            font-size: 1.75rem;
            font-weight: bold;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body class="d-flex align-items-center justify-content-center min-vh-100">
    <div class="card p-4 shadow-lg fade-in" style="width: 24rem;">
        <h1 class="card-title text-center mb-4">User Details</h1>
        <div class="card-body">
            <p class="mb-2"><strong>Full Name:</strong> ${user.fullName}</p>
            <p class="mb-2"><strong>Email:</strong> ${user.email}</p>
            <p class="mb-2"><strong>Role:</strong> ${user.roleId == 1 ? "Admin" : user.roleId == 2 ? "Chairman" : user.roleId == 3 ? "Team Leader": "Member"}</p>
            <p class="mb-2"><strong>Club:</strong> ${user.clubName}</p>
            <a href="${pageContext.request.contextPath}/User" class="btn btn-primary mt-3 w-100">Back to User List</a>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
