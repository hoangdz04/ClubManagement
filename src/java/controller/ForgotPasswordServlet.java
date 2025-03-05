package controller;

import dao.UsersDAO;
import model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        UsersDAO userDao = new UsersDAO();
        Users user = userDao.getUserByEmail(email);

        if (user == null) {
            request.setAttribute("errorMessage", "This Email have not existed");
            request.getRequestDispatcher("view/forgot-password.jsp").forward(request, response);
        } else {
            // Tạo mã OTP ngẫu nhiên 6 chữ số
            Random rand = new Random();
            int otp = 100000 + rand.nextInt(900000);
            
            // Lưu OTP vào session để kiểm tra sau này
            request.getSession().setAttribute("otp", otp);
            request.getSession().setAttribute("email", email);

            // Gửi email chứa OTP
            boolean emailSent = sendOtpEmail(email, otp);
            if (emailSent) {
                request.setAttribute("errorMessage", "OTP code has sent to your gmail.");
                request.getRequestDispatcher("view/verify-otp.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Error when sending email. Please try again!");
                request.getRequestDispatcher("view/forgot-password.jsp").forward(request, response);
            }
        }
    }

    private boolean sendOtpEmail(String recipientEmail, int otp) {
        final String senderEmail = "clubmanagementprj@gmail.com"; // Thay bằng email của bạn
        final String senderPassword = "tmll buci lscu wrde"; // Thay bằng mật khẩu ứng dụng

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("OTP for resetting the password");
            message.setText("Your OTP code is: " + otp + ". This code is activated for 5 minutes.");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    
    
}
