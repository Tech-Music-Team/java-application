package school.sptech.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.entities.Logger;
import school.sptech.exception.TechMusicException;


public class ConexaoBanco {

    private final JdbcTemplate jdbcTemplate;

    public ConexaoBanco() throws TechMusicException {
        Logger.info(ConexaoBanco.class.getPackageName().toString(), ConexaoBanco.class.getName().toString(), "Iniciando conexão com banco de dados");

        try {
            String host = System.getenv().getOrDefault("DB_HOST", "127.0.0.1");
            String port = System.getenv().getOrDefault("DB_PORT", "3306");
            String database = System.getenv().getOrDefault("DB_DATABASE", "tech_music");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            String username = System.getenv().getOrDefault("DB_USER", "root");
            String password = System.getenv().getOrDefault("DB_PASSWORD", "p0o9i8u7");

            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(url);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);

            this.jdbcTemplate = new JdbcTemplate(basicDataSource);
            Logger.info(ConexaoBanco.class.getPackageName().toString(), ConexaoBanco.class.getName().toString(), "Conexão criado em: " + url);
        } catch (Exception e) {
            Logger.error(ConexaoBanco.class.getPackageName().toString(), ConexaoBanco.class.getName().toString(), "Falha ao conectar: " + e.getMessage());
            throw new TechMusicException("Falha ao conectar ao banco de dados", e);
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
