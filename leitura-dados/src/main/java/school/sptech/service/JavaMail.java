package school.sptech.service;

import school.sptech.exception.TechMusicException;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Scanner;

public class JavaMail {


    // APENAS ALTERE AQUI
    private String email = "techmusicsptech@gmail.com";
    private String senhaApp= "zebplretkvnmgsvv";

    

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, senhaApp);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(body, "text/html; charset=utf-8");

        Transport.send(message);
    }

    private static final String TEMPLATE_LEMBRETE = "templates/email-lembrete.html";

    public static String montarTemplateLembrete(String nomeEvento) throws TechMusicException {
        String template = carregarTemplate(TEMPLATE_LEMBRETE);
        return template.replace("{{nomeEvento}}", escaparHtml(nomeEvento));
    }

    private static String carregarTemplate(String caminho) throws TechMusicException {
        try (InputStream input = JavaMail.class.getClassLoader().getResourceAsStream(caminho)) {
            if (input == null) {
                throw new TechMusicException("Template não encontrado no classpath: " + caminho);
            }
            try (Scanner scanner = new Scanner(input, StandardCharsets.UTF_8.name())) {
                return scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            }
        } catch (IOException e) {
            throw new TechMusicException("Erro ao carregar o template de e-mail: " + caminho, e);
        }
    }

    private static String escaparHtml(String texto) {
        if (texto == null) {
            return "";
        }
        return texto
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }


}