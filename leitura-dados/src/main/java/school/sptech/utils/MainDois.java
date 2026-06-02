package school.sptech.utils;

import school.sptech.repository.ConexaoBanco;
import school.sptech.repository.JavaMailRepository;
import school.sptech.service.JavaMail;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.time.LocalDate;
import java.util.*;

public class MainDois {
    public static void main(String[] args) {
    JavaMailRepository javaMailRepository = new JavaMailRepository(new ConexaoBanco());

        javaMailRepository.enviarEmails();



    }
}
