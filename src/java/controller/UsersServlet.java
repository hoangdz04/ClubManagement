/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<h1>Servlet UsersServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Users currentUser = (Users) session.getAttribute("user");
        if (action == null) {
            action = "list";
        }
        if (currentUser == null || (!currentUser.isAdmin() && !currentUser.isChairman())) {
            response.sendRedirect("login.jsp");  
        return;
        }

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
//                case "list":
//                    listUsers(request, response);
//                    break;
//                case "clubUsers":
//                    listClubUsers(request, response);
//                    break;
                case "view":
                    viewUser(request, response);
                    break;
                case "search":
                    searchUsers(request, response);
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("insert")) {
            insertUser(request, response);
        } else if (action.equals("update")) {
            updateUser(request, response);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Users> usersList = usersDAO.getAllUsers();
        request.setAttribute("users", usersList);
        request.getRequestDispatcher("/view/users.jsp").forward(request, response);
    }

//    private void listClubUsers (HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        HttpSession session = request.getSession();
//        Users user = (Users)session.getAttribute("user");
//        List<Users> usersList = usersDAO.getUsersByClubId(user.getClubId());
//        request.setAttribute("users", usersList);
//        request.getRequestDispatcher("/view/users.jsp").forward(request, response);
//    }
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

    private void viewUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Users user = null;
        try {
            user = usersDAO.getUserById(userId);
        } catch (Exception e) {
            System.out.println("Invalid user!");
        }

        if (user == null) {
            response.sendRedirect("users.jsp?error=User not found");
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/user-view.jsp");
        dispatcher.forward(request, response);
    }

    private void searchUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }
        String clubIdStr = request.getParameter("clubId");
        Integer clubId = null;
        if (clubIdStr != null && !clubIdStr.isEmpty()) {
            try {
                clubId = Integer.parseInt(clubIdStr);
            } catch (NumberFormatException e) {
                clubId = null;
            }
        }
        List<Users> users = usersDAO.searchUsers(keyword);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/view/users.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
