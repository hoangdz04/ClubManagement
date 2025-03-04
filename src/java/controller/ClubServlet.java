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
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Clubs;

/**
 *
 * @author A
 */
public class ClubServlet extends HttpServlet {
    private ClubDAO clubDAO;
    public ClubServlet() {
        super();
        clubDAO = new ClubDAO();
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
            out.println("<title>Servlet ClubServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClubServlet at " + request.getContextPath () + "</h1>");
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
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user") == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//        
//        ClubDAO clubDAO = new ClubDAO();
//        List<Clubs> clubList = clubDAO.getAllClubs();
//        request.setAttribute("clubs", clubList);
//        request.getRequestDispatcher("clubs.jsp").forward(request, response);

HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("view/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listClubs(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteClub(request, response);
                break;
            default:
                listClubs(request, response);
                break;
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
        if (action == null) {
            action = "insert";
        }

        switch (action) {
            case "insert":
                insertClub(request, response);
                break;
            case "update":
                updateClub(request, response);
                break;
            default:
                listClubs(request, response);
                break;
        }
    }
    
    private void listClubs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Clubs> clubs = clubDAO.getAllClubs();
        request.setAttribute("clubs", clubs);
        request.getRequestDispatcher("view/clubs.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        Clubs club = clubDAO.getClubById(clubId);
        request.setAttribute("club", club);
        request.getRequestDispatcher("view/club-form.jsp").forward(request, response);
    }

    private void insertClub(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String clubName = request.getParameter("clubName");
        String description = request.getParameter("description");
        String date = request.getParameter("establishedDate");
        Clubs club = new Clubs(0, clubName, description, java.sql.Date.valueOf(date));
        clubDAO.addClub(club);
        response.sendRedirect("clubs?action=list");
    }

    private void updateClub(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        String clubName = request.getParameter("clubName");
        String description = request.getParameter("description");
        String date = request.getParameter("establishedDate");
        Clubs club = new Clubs(clubId, clubName, description, java.sql.Date.valueOf(date));
        clubDAO.updateClub(club);
        response.sendRedirect("clubs?action=list");
    }

    private void deleteClub(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int clubId = Integer.parseInt(request.getParameter("clubId"));
        clubDAO.deleteClub(clubId);
        response.sendRedirect("clubs?action=list");
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
