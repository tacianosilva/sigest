package br.ufrn.cerescaico.bsi.sigest.bo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Classe de negócio abstrata que contém método auxiliares para os BO's do
 * sistema Sigest.
 *
 * @author Taciano Morais Silva
 * @version 12/08/2010, 20h58m
 * @since 12/08/2010, 20h58m
 */
public class AbstractBO {

    /**
     * Código do Usuário Administrador.
     */
    protected static final Integer ADMIN = 1;

    /**
     * String Vazio.
     */
    protected static final String STRING_VAZIO = "";

    /**
     * Tamanho mínimo para um nome e login.
     */
    protected static final int TAMANHO_MINIMO = 6;

    protected AbstractBO() {
    }

    /**
     * Verifica se a string passada como parâmetro é nula ou vazia.
     * @param obj Uma string.
     * @return True se a string passada é nula ou vazia.
     */
    protected boolean checkNullVazio(String obj) {
        if (obj == null || "".equals(obj)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o objeto passado como parâmetro é nulo.
     * @param obj Um objeto.
     * @return True se o objeto passado é nulo.
     */
    protected boolean checkNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se o inteiro passado como parâmetro é nulo ou tem valor zero.
     * @param id Um inteiro.
     * @return True se o inteiro passado é nulo ou de valor zero.
     */
    protected boolean checkNullZero(Integer id) {
        if (id == null || id == 0) {
            return true;
        }
        return false;
    }

    //TODO Colocar como protected depois dos testes!
    public static void enviarEmailNotificacao(String texto) {
        String remetente = "tacianosilva@gmail.com";
        String destinatario = "tacianosilva@yahoo.com.br";
        String assunto = "Notificação Sigest";
        String mensagem = texto;
        enviaEmail(remetente, destinatario, assunto, mensagem);
    }

    // Método que envia o email
    public static void enviaEmail(String remetente, String destinatario,
            String assunto, String mensagem) {

        Session session = Session.getDefaultInstance(getPropriedades(),
                getAuthenticator());

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente)); // Seta o remetente
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)); // Define o
                                                            // destinatário
            message.setSubject(assunto); // Define o assunto
            message.setText(mensagem); // Mensagem do email

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // Método que retorna a autenticação de sua conta de email
    public static Authenticator getAuthenticator() {

        Authenticator autenticacao = new Authenticator() {

            public PasswordAuthentication getPasswordAuthentication() {

                // Preencha com seu email e com sua senha
                return new PasswordAuthentication("tacianosilva@gmail.com",
                        "EvouDDb25DDsj");
            }
        };

        return autenticacao;
    }

    // Método que retorna as propriedades de configuração do servidor de email
    public static Properties getPropriedades() {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return props;
    }
}
