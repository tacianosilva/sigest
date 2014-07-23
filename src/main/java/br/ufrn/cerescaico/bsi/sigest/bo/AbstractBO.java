package br.ufrn.cerescaico.bsi.sigest.bo;


/**
 * Classe de negócio abstrata que contém método auxiliares para os BO's do
 * sistema Sigest.
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

    protected boolean checkNullVazio(String obj) {
        if (obj == null || "".equals(obj)) {
            return true;
        }
        return false;
    }

    protected boolean checkNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    protected boolean checkNullZero(Integer id) {
        if (id == null || id == 0) {
            return true;
        }
        return false;
    }

    //TODO Adicionar API de Envio de E-mail
    /*
    protected MailMessage getMailMessage(Email mail) {
        MailMessage email = new MailMessage();
        email.setTo(mail.getDestino());
        email.setSubject(mail.getAssunto());
        email.setText(mail.getConteudo());
        return email;
    }*/
}
