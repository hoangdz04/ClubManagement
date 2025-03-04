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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>User Management</h2>
    <a href="view/user-form.jsp">Add New User</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Club</th>
            <th>Actions</th>
        </tr>
        <% for (Users user : users) { %>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getFullName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getRoleId() %></td>
            <td><%= user.getClubId() %></td>
            <td>
                <a href="User?action=edit&userId=<%= user.getUserId() %>">Edit</a> |
                <a href="User?action=delete&userId=<%= user.getUserId() %>" onclick="return confirm('Are you sure?');">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>