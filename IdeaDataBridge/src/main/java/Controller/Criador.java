package Controller;

import Service.ConexaoMySQL;
import Service.ConexoesInterface;

public class Criador {
    public ConexoesInterface criarConexaoMySQL() {
        return new ConexaoMySQL();
    }
}
