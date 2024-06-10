package Service;

import Model.Totem;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoSqlService extends ConexoesService {

        private final JdbcTemplate con;

    public ConexaoSqlService() {
        BasicDataSource bds = new BasicDataSource();

        bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        bds.setUrl("jdbc:sqlserver://100.27.172.173:1433;encrypt=false;databaseName=ideabd;");
        bds.setUsername("sa");
        bds.setPassword("Duda-2901");

        this.con = new JdbcTemplate(bds);
    }

    @Override
    public JdbcTemplate getCon() {
        return this.con;
    }

}


