package Service;

import Model.Totem;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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


//    // URL do banco de dados
//    private static final String DB_URL = "jdbc:sqlserver://http://100.27.172.173/:1433;databaseName=ideabd";
//    // Credenciais do banco de dados
//    private static final String USER = "sa";
//    private static final String PASS = "Duda-2901";
//
//    BasicDataSource bds = new BasicDataSource();
//
//
//    Connection conn = null;
//    PreparedStatement pstmt = null;
//    Totem totem = new Totem();
//
//            try {
//
//                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                String sql = "INSERT INTO dados_hardware" + "(porcentagemUso, dataHora, nomeComponente, fkHardware, fkTotem) VALUES (?, ?, ?, ?, ?)",  totem.getPorcentagemUsoMemoria(), LocalDateTime.now(), totem.getMemoriaNome() , 1, 1;
//
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1, "valor1");
//                pstmt.setString(2, "valor2");
//
//                // Executar a inserção
//                pstmt.executeUpdate();
//                System.out.println("Dados inseridos com sucesso!");
//
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                // Fechar recursos
//                try {
//                    if (pstmt != null) pstmt.close();
//                    if (conn != null) conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
}


