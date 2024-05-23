package Service;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ConexoesInterface {
    public JdbcTemplate getCon();
}
