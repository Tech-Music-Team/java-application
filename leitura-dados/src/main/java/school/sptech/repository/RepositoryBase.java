package school.sptech.repository;

import org.springframework.jdbc.core.JdbcTemplate;


public abstract class RepositoryBase {

    protected final JdbcTemplate jdbcTemplate;

    protected RepositoryBase(ConexaoBanco conexaoBanco) {
        this.jdbcTemplate = conexaoBanco.getJdbcTemplate();
    }
}
