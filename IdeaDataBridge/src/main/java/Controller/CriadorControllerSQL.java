package Controller;

import Service.ConexaoSqlService;

public class CriadorControllerSQL {

    public ConexoesController criarConexaoSQL() {
        return new ConexaoSqlService();
    }

}
