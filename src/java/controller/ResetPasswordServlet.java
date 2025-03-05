package controller;

import dao.UsersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email"); // Lấy email từ session
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra nếu không có email trong session (chưa qua bước xác thực OTP)
        if (email == null) {
            request.setAttribute("errorMessage", "You did not authenticate OTP!");
            request.getRequestDispatcher("view/forgot-password.jsp").forward(request, response);
            return;
        }

        // Kiểm tra xác nhận mật khẩu
        if (password == null || confirmPassword == null || !password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Password is not the same. Please try again.");
            request.getRequestDispatcher("view/reset-password.jsp").forward(request, response);
            return;
        }

        // Cập nhật mật khẩu trong database
        UsersDAO userDao = new UsersDAO();
        boolean updated = userDao.updatePassword(email, password);

        if (updated) {
            session.invalidate(); // Xóa session sau khi đặt lại mật khẩu thành công
            request.setAttribute("errorMessage", "Password has set successfully.");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Error when setting password. Try again.");
            request.getRequestDispatcher("view/reset-password.jsp").forward(request, response);
        }
    }
}
