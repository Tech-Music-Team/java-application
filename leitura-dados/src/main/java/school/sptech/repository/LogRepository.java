package school.sptech.repository;

import school.sptech.entities.Log;

public class LogRepository extends RepositoryBase {

    public LogRepository(ConexaoBanco conexaoBanco) {
        super(conexaoBanco);
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
