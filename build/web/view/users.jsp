<%-- 
    Document   : users
    Created on : Mar 3, 2025, 11:50:23 PM
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Users" %>
<%
    List<Users> users = (List<Users>) request.getAttribute("users");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        .fade-in {
            animation: fadeIn 1s ease-in-out;
        }
        .table th, .table td {
            vertical-align: middle;
        }
        .btn-custom {
            margin-right: 5px;
        }
        .container {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .table thead {
            background-color: #343a40;
            color: #fff;
        }
        .table tbody tr:hover {
            background-color: #f1f1f1;
        }
        .btn-secondary{
            margin-top: -24px;
        }
        
        .btn-outline-primary{
            margin-left: 14px;
 
        }
    </style>
</head>
<body class="p-4 bg-light">
    
    <div class="container fade-in">
        <h1 class="display-4 mb-4 text-primary">User Management</h1>
        
        <div class="d-grid gap-2 mb-4">
            <a href="view/user-form.jsp" class="btn btn-primary ">Add New User</a>
            <a href="${pageContext.request.contextPath}/Account" class="btn btn-primary ">Return</a>
        </div>
        <div class="table-responsive">
            <form action="${pageContext.request.contextPath}/User" method="get" class="mb-4">
                <input type="hidden" name="action" value="search">
                <div class="input-group">
                    <input type="text" name="keyword" class="form-control" placeholder="Search users..." value="${param.keyword}">
                    <button class="btn btn-outline-primary" type="submit">Search</button>
                </div>
            </form>
            <table class="table table-bordered table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Club</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Users user : users) { %>
                    <tr>
                        <td><%= user.getUserId() %></td>
                        <td><%= user.getFullName() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getRoleId() %></td>
                        <td><%= user.getClubId() %></td>
                        <td>
                            <a href="User?action=view&userId=<%= user.getUserId() %>" class="btn btn-info btn-sm btn-custom">View</a>
                            <a href="User?action=edit&userId=<%= user.getUserId() %>" class="btn btn-warning btn-sm btn-custom">Edit</a>
                            <a href="User?action=delete&userId=<%= user.getUserId() %>" class="btn btn-danger btn-sm btn-custom" onclick="return confirmDelete('User?action=delete&userId=<%= user.getUserId() %>');">Delete</a>
                        </td>
                    </tr>
                    <% } %>
                    
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
                                    function confirmDelete(url) {
                                        Swal.fire({
                                            title: "Are you sure?",
                                            text: "You won't be able to revert this!",
                                            icon: "warning",
                                            showCancelButton: true,
                                            confirmButtonColor: "#d33",
                                            cancelButtonColor: "#3085d6",
                                            confirmButtonText: "Yes, delete it!"
                                        }).then((result) => {
                                            if (result.isConfirmed) {
                                                window.location.href = url;
                                            }
                                        });
                                        return false;
                                    }
        </script>
</body>
</html>
