///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Utils;
//
//import java.util.Properties;
//
///**
// *
// * @author Chaima
// */
//public class MailTools {
//
//    public static void sendMail(String recepient) throws Exception {
//        System.out.println("Preparing to send email");
//        Properties properties = new Properties();
//
//        //Enable authentication
//        properties.put("mail.smtp.auth", "true");
//        //Set TLS encryption enabled
//        properties.put("mail.smtp.starttls.enable", "true");
//        //Set SMTP host
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        //Set smtp port
//        properties.put("mail.smtp.port", "587");
//
//        //Your gmail address
//        String userName = "recservice6@gmail.com";
//        //Your gmail password
//        String password = "123456789rec";
//
//        //Create a session with account credentials
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(userName, password);
//            }
//
//        });
//
//        //Prepare email message
//        Message message = prepareMessage(session, userName, recepient);
//
//        //Send mail
//        Transport.send(message);
//        System.out.println("Message sent successfully");
//    }
//
//    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("Réclamation [Update]");
//            String htmlCode = "<h1> Reclamation </h1> <br/> <h2><b>User </b></h2>";
//            message.setContent(htmlCode, "text/html");
//            return message;
//        } catch (Exception ex) {
//        }
//        return null;
//    }
//}
