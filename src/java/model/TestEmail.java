package model;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.*;
import java.util.Properties;

public class TestEmail {
    public static void main(String[] args) {
        boolean emailSent = sendOtpEmail("trungcho1305@gmail.com", 123456);
        System.out.println("Email sent: " + emailSent);
    }

    private static boolean sendOtpEmail(String recipientEmail, int otp) {
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
            message.setSubject("Mã OTP đặt lại mật khẩu");
            message.setText("Mã OTP của bạn là: " + otp + ". Mã này có hiệu lực trong 5 phút.");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
