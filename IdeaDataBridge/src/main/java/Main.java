 import Controller.CriadorController;
 import Controller.LoginController;
 import Model.Totem;
 import Controller.ConexoesController;

 import java.util.Map;

 public class Main {
    static CriadorController criador = new CriadorController();
    static Totem totem = new Totem();
    public static void main(String[] args) {
        System.out.println("""
            ooooo       .o8                       ooooooooo
            `888'      "888                      d""\"""\""8'
             888   .oooo888   .ooooo.   .oooo.         .8'
             888  d88' `888  d88' `88b `P  )88b       .8'
             888  888   888  888ooo888  .oP"888      .8'
             888  888   888  888    .o d8(  888     .8'
            o888o `Y8bod88P" `Y8bod8P' `Y888""8o   .8'

            """);
        LoginController.login(criador.criarConexaoMySQL(), totem);
    }

     public void alertas(Long usoProcessador, Long usoRAM, Map<String, Long> usoVolumes, ConexoesController conexoes) {
         final Double USO_MAXIMO_PROCESSADOR = 0.0;
         final Double USO_MAXIMO_RAM = 0.0;
         final Double USO_MAXIMO_DISCO = 0.0;
     }
}
