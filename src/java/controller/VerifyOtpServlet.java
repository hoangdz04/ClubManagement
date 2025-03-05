package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;

public class VerifyOtpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEnteredOtp = request.getParameter("otp");
        Integer otp = (Integer) session.getAttribute("otp");

        if (otp != null && userEnteredOtp.equals(String.valueOf(otp))) {
            session.removeAttribute("otp");
            request.setAttribute("errorMessage", "Authenticate successfully! Set the password again.");
            request.getRequestDispatcher("view/reset-password.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "OTP code is wrong!");
            request.getRequestDispatcher("view/verify-otp.jsp").forward(request, response);
        }
    }
}
