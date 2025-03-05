<%-- 
    Document   : user-form
    Created on : Mar 3, 2025, 11:50:37 PM
    Author     : A
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Users" %>
<%
    Users user = (Users) request.getAttribute("user");
    boolean isEdit = (user != null);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= isEdit ? "Edit User" : "Add User" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #007bff, #00c6ff);
            font-family: 'Roboto', sans-serif; 
        } 
        .form-container { 
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 600px;
            margin: auto;
            animation: fadeIn 1s ease-in-out;
        }
        .form-label {
            font-weight: bold;
        }
        .btn-animate {
            transition: background-color 0.3s, transform 0.3s;
        }
        .btn-animate:hover {
            background-color: #0056b3;
            transform: scale(1.05);
        }
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body class="d-flex align-items-center justify-content-center min-vh-100">
    <div class="form-container">
        
        <h1 class="text-center text-2xl font-bold mb-4"><%= isEdit ? "Edit User" : "Add User" %></h1>
        <form class="row g-3" action="${pageContext.request.contextPath}/User" method="post">
            <input type="hidden" name="action" value="<%= isEdit ? "update" : "insert" %>">
            <% if (isEdit) { %>
                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
            <% } %>
            
            <div class="col-md-12">
                <label for="fullName" class="form-label">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="<%= isEdit ? user.getFullName() : "" %>" class="form-control" required>
            </div>
            <div class="col-md-12">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" value="<%= isEdit ? user.getEmail() : "" %>" class="form-control" required>
            </div>
            <div class="col-md-12">
                <label for="password" class="form-label">Password:</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <div class="col-md-6">
                <label for="role" class="form-label">Role:</label>
                <select id="role" name="roleId" class="form-select">
                    <option value="1" <%= isEdit && user.getRoleId() == 1 ? "selected" : "" %>>Admin</option>
                    <option value="2" <%= isEdit && user.getRoleId() == 2 ? "selected" : "" %>>Chairman</option>
                    <option value="3" <%= isEdit && user.getRoleId() == 3 ? "selected" : "" %>>Team Leader</option>
                    <option value="4" <%= isEdit && user.getRoleId() == 4 ? "selected" : "" %>>Member</option>
                </select>
            </div>
            <div class="col-md-6">
                <label for="clubId" class="form-label">Club ID:</label>
                <input type="number" id="clubId" name="clubId" value="<%= isEdit ? user.getClubId() : "" %>" class="form-control">
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <button type="submit" class="btn btn-primary btn-animate "><%= isEdit ? "Update User" : "Add User" %></button>
                <a href="${pageContext.request.contextPath}/User" class="btn btn-primary">Return</a>
            </div>
             
        </form>
                 
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
