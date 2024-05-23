package Service;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class Conexoes implements ConexoesInterface {
    @Override
    public JdbcTemplate getCon() {
        return null;
    }
}
