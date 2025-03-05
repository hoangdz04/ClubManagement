package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import model.Users;

public class SendOtpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("user_su");
        String email = request.getParameter("email");
        String password = request.getParameter("password_su");
        int roleId = 5;
        
// Tạo mã OTP ngẫu nhiên
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);

        // Lưu OTP vào session để kiểm tra sau này
        HttpSession session = request.getSession();
        session.setAttribute("otp", otp);
        session.setAttribute("email", email);

        // Gửi email chứa OTP
        boolean emailSent = sendOtpEmail(email, otp);
        if (emailSent) {
            Users user = new Users(username, email, password, roleId);
            if (username.isBlank() || email.isBlank() || password.isBlank()){
                user=null;
            }
            else{
                user = AccountDAO.addAccount(user);
            }
            if(user != null){
                request.setAttribute("errorMessage", "Sending email successfully!");
                request.setAttribute("user", user);
                response.sendRedirect("view/verify-email.jsp");
            }
        } else {
            request.setAttribute("errorMessage", "Error when sending email. Please try again");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
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
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("OTP Code for authenticate gmail account");
            message.setText("Your OTP Code is: " + otp + ". This code is activated for 5 minutes.");

            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
