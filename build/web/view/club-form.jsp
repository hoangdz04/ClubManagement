<%-- 
    Document   : club-form
    Created on : Mar 2, 2025, 4:46:28 PM
    Author     : A
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Clubs" %>

<%
    Clubs club = (Clubs) request.getAttribute("club");
    boolean isEdit = (club != null);
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%= isEdit ? "Edit Club" : "Add Club" %></title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@700&family=Roboto:wght@400;700&display=swap" rel="stylesheet"/>
        <style>
            body {
                background-color: #e3f2fd;
                ;
                font-family: 'Roboto', sans-serif;
            }
            .form-container {
                background-color: #ffffff;
                padding: 2rem;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                width: 100%;
                animation: fadeIn 1s ease-in-out;
            }
            .form-title {
                color: #1565c0;
                text-align: center;
                font-size: 2rem;
                font-weight: bold;
            }
            .sidebar {
                background: linear-gradient(to right, #1565c0, #1e88e5);
                width: 50%;
                padding: 3rem;
                color: white;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                border-radius: 10px;
                animation: slideIn 1s ease-in-out;
            }
            .sidebar h2, .sidebar p {
                text-align: center;
            }
            .content {
                width: 50%;
                padding-left: 2rem;
            }
            .btn-primary {
                background-color: #1e88e5;
                ;
                border: none;
                transition: all 0.3s ease;
            }
            .btn-primary:hover {
                background-color: #1565c0;
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
            @keyframes slideIn {
                from {
                    opacity: 0;
                    transform: translateX(-100%);
                }
                to {
                    opacity: 1;
                    transform: translateX(0);
                }
            }
        </style>

    </head>
    <body class="d-flex vh-100 align-items-center justify-content-center">
        <div class="d-flex w-75">         
            <div class="sidebar">
                <h2>Club Management System</h2>
                <p>"Great clubs start with great leaders. Take the first step in building a strong and inspiring community today!"</p>
                <img src="${pageContext.request.contextPath}/img/comunity.jpg" class="rounded shadow" width="350" height="250" alt="Club Management Image">
            </div>
            <div class="content d-flex align-items-center justify-content-center">
                <div class="form-container">
                    <h1 class="form-title mb-4"><%= isEdit ? "Edit Club" : "Add Club" %></h1>
                    <form action="${pageContext.request.contextPath}/clubs" method="post">
                        <% if (isEdit) { %>
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="clubId" value="<%= club.getClubId() %>">
                        <% } else { %>
                        <input type="hidden" name="action" value="insert">
                        <% } %>
                        <div class="mb-3">
                            <label class="form-label" for="clubName">Club Name</label>
                            <input class="form-control" type="text" name="clubName" id="clubName" value="<%= isEdit ? club.getClubName() : "" %>" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="description">Description</label>
                            <textarea class="form-control" name="description" id="description" required><%= isEdit ? club.getDescription() : "" %></textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="establishedDate">Established Date</label>
                            <input class="form-control" type="date" name="establishedDate" id="establishedDate" value="<%= isEdit ? club.getEstablishedDate() : "" %>" required>
                        </div>
                        
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button class="btn btn-primary btn-animate" type="submit"><%= isEdit ? "Update Club" : "Add Club" %></button>
                            <a href="${pageContext.request.contextPath}/clubs?action=list" class="btn btn-primary">Return</a>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </body>
</html>

