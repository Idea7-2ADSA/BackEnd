package Controller;
import Service.ConexaoMySqlService;
import Service.ConexaoSqlService;

public class CriadorController {
    public ConexoesController criarConexaoMySQL() {
        return new ConexaoMySqlService();
    }

    public ConexaoSqlService criarConexaoSQL() {return  new ConexaoSqlService();}
}
