package Controller;

import Service.ConexaoMySqlService;
import Service.ConexaoSqlService;

public class CriadorControllerSQL {

    public ConexoesController criarConexaoSQL() {
        return new ConexaoSqlService();
    }

}
