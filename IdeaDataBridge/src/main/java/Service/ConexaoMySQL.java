package Service;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoMySQL extends Conexoes{
    private final JdbcTemplate con;

    public ConexaoMySQL() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/ideabd");
        bds.setUsername("usuario");
        bds.setPassword("usuario");

        con = new JdbcTemplate(bds);
    }

    @Override
    public JdbcTemplate getCon() {
        return this.con;
    }
}
