package school.sptech.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.service.JavaMail;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaMailRepository {
    private final JdbcTemplate jdbcTemplate;

    public JavaMailRepository(ConexaoBanco conexaoBanco) {
        this.jdbcTemplate = conexaoBanco.getJdbcTemplate();
    }


    public List<Map<String, Object>> getEmailsJavaMail() {


        return jdbcTemplate.queryForList("select u.email, l.email_secundario, l.nome, l.data_evento from usuario u join lineup l on u.id_usuario = l.fk_usuario;");


    }

    public List<List<String>> getInformacoesEnvioEmail() {
        JavaMailRepository javaMailRepository = new JavaMailRepository(new ConexaoBanco());

        List<List<String>> emails = new ArrayList<>();

        for (Map<String, Object> stringObjectMap : javaMailRepository.getEmailsJavaMail()) {

            if (LocalDate.now().plusDays(1).isEqual(LocalDate.parse(stringObjectMap.get("data_evento").toString()))) {
                List<String> infos = new ArrayList<>();
                infos.add(stringObjectMap.get("email").toString());
                if (stringObjectMap.get("email_secundario") != null){
                    infos.add(stringObjectMap.get("email_secundario").toString());
                }else{
                    infos.add(null);
                }

                infos.add(stringObjectMap.get("nome").toString());
                emails.add(infos);
            }

        }
        return emails;
    }


    public void enviarEmails() {
        JavaMail emailSender = new JavaMail();

        List<List<String>> informacoesEnvioEmail = getInformacoesEnvioEmail();

        for (List<String> strings : informacoesEnvioEmail) {
            try {
                emailSender.sendEmail(strings.get(0), "Seu evento é amanha!!", "O evento: "+ strings.get(2) + "acontecerá amanhã!! Fique atento!");
                System.out.println("E-mail enviado com sucesso!");
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            if (strings.get(1) != null) {
                try {
                    emailSender.sendEmail(strings.get(1), "Seu evento é amanha!!", "O evento: "+ strings.get(2) + "acontecerá amanhã!! Fique atento!");
                    System.out.println("E-mail enviado com sucesso!");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("EMAILS ENVIADOS!!!!!!!!!!");

    }

}

