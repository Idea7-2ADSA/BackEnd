package Controller;

import Service.ConexaoMySqlService;

public class CriadorController {
    public ConexoesController criarConexaoMySQL() {
        return new ConexaoMySqlService();
    }
}
