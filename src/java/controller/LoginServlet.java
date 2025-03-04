/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;


import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.*;
import model.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
/**
 *
 * @author A
 */
public class LoginServlet extends HttpServlet {
   
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
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            String user1 = request.getParameter("user");
            String password = request.getParameter("password");
            
            Users user = AccountDAO.getAccountsByUserAndPassword(user1, password);
            
//            if(username.isBlank() || password.isBlank()){
//                out.println("<h2> Please enter username and password! </h2>");
//            }else if(account != null){
//                out.println("<h1> Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//                out.println("<h2> Username: " + username + "</h2>");
//                out.println("<h2> Password: " + password + "</h2>");
//            }
//            else{
//                out.println("<h2> Error: Not match username and password! </h2>");
//        }

            if(user == null){
                String error = "Not match username and password!";
                request.setAttribute("errorMessage", error);
                RequestDispatcher rs = request.getRequestDispatcher("view/login.jsp");
                rs.forward(request, response);
            }else{
//                request.setAttribute("account", account);
//                RequestDispatcher rs = request.getRequestDispatcher("view/Welcome.jsp");
//                rs.forward(request, response);
                  HttpSession session = request.getSession();
                  session.setAttribute("user", user);
                  response.sendRedirect("Account");
            }
    }
    }
    
        
        

//        try (Connection conn = DBContext.getInstance().getConnection()) {
//            String sql = "SELECT * FROM Users WHERE fullName = ? AND password = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                HttpSession session = request.getSession();
//                session.setAttribute("userID", rs.getInt("userID"));
//                session.setAttribute("fullName", rs.getString("fullName"));
//                session.setAttribute("role", rs.getString("role"));
//                response.sendRedirect("view/admin.jsp"); // Chuyển đến dashboard sau khi login thành công
//            } else {
//                request.setAttribute("errorMessage", "Invalid username or password");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
//        } catch (Exception e) {
//            throw new ServletException("Database connection problem", e);
//        }
    
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
