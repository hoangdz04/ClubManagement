<%-- 
    Document   : clubs
    Created on : Mar 2, 2025, 4:45:14 PM
    Author     : A
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Clubs" %>

<%
    HttpSession userSession = request.getSession();
    if (userSession.getAttribute("user") == null) {
        response.sendRedirect("view/login.jsp");
        return;
    }
    
    List<Clubs> clubs = (List<Clubs>) request.getAttribute("clubs");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Club Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            @keyframes fadeIn {
                from {
                    opacity: 0;
                }
                to {
                    opacity: 1;
                }
            }
            .fade-in {
                animation: fadeIn 1s ease-in-out;
            }
            body {
                background-color: #f8f9fa;
            }
            .container {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .table thead th {
                background-color: #343a40;
                color: #fff;
            }
            .table tbody tr:hover {
                background-color: #f1f1f1;
            }
            .btn-primary, .btn-danger {
                transition: background-color 0.3s ease;
            }
            .btn-primary:hover {
                background-color: #0056b3;
            }
            .btn-danger:hover {
                background-color: #c82333;
            }
            .btn-color{
                background-color: #0d6efd;
               
            }
            .btn-color:hover{
                background-color: #0056b3; 
            }
            
            
        </style>

    </head>
    <body class="text-dark">
        <div class="container mt-5 fade-in">
            <h1 class="display-4 mb-4">Club Management</h1>
            <a href="view/club-form.jsp" class="btn btn-primary mb-4">Add New Club</a>
            <a href="${pageContext.request.contextPath}/Account" 
               class="btn btn-color text-white position-relative" 
               style="top: -12px; left: 20px;">
                Return
            </a>
            <div class="table-responsive">
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Established Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (clubs != null) { %>
                        <% for (Clubs club : clubs) { %>
                        <tr>
                            <td><%= club.getClubId() %></td>
                            <td><%= club.getClubName() %></td>
                            <td><%= club.getDescription() %></td>
                            <td><%= club.getEstablishedDate() %></td>
                            <td>
                                <a href="clubs?action=edit&clubId=<%= club.getClubId() %>" class="btn btn-sm btn-primary">Edit</a> 

                                <a href="#" class="btn btn-sm btn-danger" onclick="return confirmDelete('clubs?action=delete&clubId=<%= club.getClubId() %>');">Delete</a>

                            </td>
                        </tr>
                        <% } %>
                        <% } else { %>
                        <tr>
                            <td colspan="5" class="text-center">No clubs available</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
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
