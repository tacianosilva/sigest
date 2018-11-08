package br.ufrn.cerescaico.bsi.sigest.util;

import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

/* //TODO Código Antigo - Remover depois!

props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP do seu servidor de email
props.put("mail.smtp.socketFactory.port", "465"); // Porta do servidor smtp
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // Define a conexão do tipo
                                            // SSL
props.put("mail.smtp.socketFactory.fallback", "false");
props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
// props.put("mail.smtp.ssl.trust", "smtpserver");
props.put("mail.smtp.starttls.enable", true);
props.put("mail.smtp.auth", true); // Define que é necessário autenticação
//props.put("mail.smtp.port", "587"); // Porta do seu servidor smtp
props.put("mail.smtp.port", "465"); // Porta do seu servidor smtp
//System.setProperty("javax.net.debug", "ssl,handshake");
props.put("mail.smtp.ssl.protocols","SSLv3 TLSv1"); */
public class TesteMail {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

/*        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(
                    "tacianosilva@copin.ufcg.edu.br"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("tacianosilva@gmail.com"));
            message.setSubject("Testing Subject 2");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }*/
    }
}
