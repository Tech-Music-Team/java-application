package school.sptech.repository;

import school.sptech.entities.Log;
import org.springframework.jdbc.core.JdbcTemplate;

public class LogRepository {

    private final JdbcTemplate jdbcTemplate;

    public LogRepository(ConexaoBanco conexaoBanco) {
        this.jdbcTemplate = conexaoBanco.getJdbcTemplate();
    }

    public void inserir(Log logEntry) {
        jdbcTemplate.update(
            "INSERT INTO log (data_hora, nivel, aplicacao, modulo, classe, mensagem) VALUES (?, ?, ?, ?, ?, ?)",
            logEntry.getDataHora(),
            logEntry.getNivel().name(),
            logEntry.getAplicacao(),
            logEntry.getModulo(),
            logEntry.getClasse(),
            logEntry.getMensagem()
        );
    }
}
