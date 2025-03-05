package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class VerifyEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEnteredOtp = request.getParameter("otp");
        Integer otp = (Integer) session.getAttribute("otp");

        if (otp != null && userEnteredOtp.equals(String.valueOf(otp))) {
            session.removeAttribute("otp"); // Xóa OTP sau khi xác thực thành công
            response.sendRedirect("view/register-success.jsp");
        } else {
            request.setAttribute("errorMessage", "Mã OTP không chính xác!");
            request.getRequestDispatcher("view/verify-email.jsp").forward(request, response);
        }
    }
}
