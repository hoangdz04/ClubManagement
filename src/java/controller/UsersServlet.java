/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import model.Users;
import java.sql.Connection;
/**
 *
 * @author A
 */
public class UsersServlet extends HttpServlet {
     private UsersDAO usersDAO;

    @Override
    public void init() throws ServletException {
        Connection connection = DBContext.getInstance().getConnection();
        usersDAO = new UsersDAO();
    }

   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsersServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsersServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        
        try {
            switch (action) {
                case "new":
                    showForm(request, response, null);
                    break;
                case "edit":
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    Users user = usersDAO.getUserById(userId);
                    showForm(request, response, user);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
          String action = request.getParameter("action");
        if ("insert".equals(action)) {
            insertUser(request, response);
        } else if ("update".equals(action)) {
            updateUser(request, response);
        }
    }
    
    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Users> usersList = usersDAO.getAllUsers();
        request.setAttribute("users", usersList);
        request.getRequestDispatcher("/view/users.jsp").forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, Users user) throws ServletException, IOException {
        request.setAttribute("user", user);
        request.getRequestDispatcher("/view/user-form.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Users user = new Users(
                0,
                request.getParameter("fullName"),
                request.getParameter("email"),
                request.getParameter("password"),
                Integer.parseInt(request.getParameter("roleId")),
                Integer.parseInt(request.getParameter("clubId"))
        );
        try {
            usersDAO.addUser(user);
            response.sendRedirect("User");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Users user = new Users(
                Integer.parseInt(request.getParameter("userId")),
                request.getParameter("fullName"),
                request.getParameter("email"),
                request.getParameter("password"),
                Integer.parseInt(request.getParameter("roleId")),
                Integer.parseInt(request.getParameter("clubId"))
        );
        try {
            usersDAO.updateUser(user);
            response.sendRedirect("User");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        try {
            usersDAO.deleteUser(userId);
            response.sendRedirect("User");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

       
}
