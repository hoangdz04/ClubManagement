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
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2><%= isEdit ? "Edit User" : "Add User" %></h2>
    <form action="${pageContext.request.contextPath}/User" method="post">
        <input type="hidden" name="action" value="<%= isEdit ? "update" : "insert" %>">
        <% if (isEdit) { %>
            <input type="hidden" name="userId" value="<%= user.getUserId() %>">
        <% } %>
        
        <label>Full Name:</label>
        <input type="text" name="fullName" value="<%= isEdit ? user.getFullName() : "" %>" required>
        
        <label>Email:</label>
        <input type="email" name="email" value="<%= isEdit ? user.getEmail() : "" %>" required>
        
        <label>Password:</label>
        <input type="password" name="password" required>
        
        <label>Role:</label>
        <select name="roleId">
            <option value="1" <%= isEdit && user.getRoleId() == 1 ? "selected" : "" %>>Admin</option>
            <option value="2" <%= isEdit && user.getRoleId() == 2 ? "selected" : "" %>>Club Manager</option>
            <option value="3" <%= isEdit && user.getRoleId() == 3 ? "selected" : "" %>>Member</option>
        </select>
        
        <label>Club ID:</label>
        <input type="number" name="clubId" value="<%= isEdit ? user.getClubId() : "" %>">
        
        <button type="submit"><%= isEdit ? "Update User" : "Add User" %></button>
    </form>
</body>
</html>