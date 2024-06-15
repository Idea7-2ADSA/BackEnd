package Controller;
import Model.Totem;
import Service.ComponentesService;
import Service.DadosService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;

public class LoginController {
    public static void login(ConexoesController conexoes,  ConexoesController conexoesMysql, Totem totem, Integer codigoTotem) {
        String hostNameTotem = totem.getHostName();
        List<Totem> totensHostName = conexoes.getCon().query
                ("SELECT codigoTotem, hostName FROM totem WHERE hostName = ?", new BeanPropertyRowMapper<>(Totem.class), hostNameTotem);
        if (totensHostName.isEmpty()) {
            totem.setCodigoTotem(codigoTotem);
            List<Totem> totensCodigo = conexoes.getCon().query
                    ("SELECT codigoTotem, hostName FROM totem WHERE codigoTotem = ?", new BeanPropertyRowMapper<>(Totem.class), codigoTotem);
            if (totensHostName.isEmpty() && totensCodigo.isEmpty()) {
                System.out.println("Esse totem ainda não foi cadastrado!");
            } else {
                System.out.println("""
                        Totem encontrado!

                        Inserindo o hostName do totem no banco!
                        """);
                conexoes.getCon().update("update totem set hostName = ? where codigoTotem = ?", hostNameTotem, codigoTotem);
                conexoesMysql.getCon().update("update totem set hostName = ? where codigoTotem = ?", hostNameTotem, codigoTotem);
                ComponentesService.inserirComponentesNoBD(conexoes, conexoesMysql, totem);
            }
        } else {
            try {
                  DadosService.inserirDadosNoBanco(conexoes, conexoesMysql, totensHostName.get(0));
            } catch (Exception e) {
                System.out.println("Houve um erro durante o processo de monitoramento!");
                System.out.println(e);
            }
        }
    }
}
