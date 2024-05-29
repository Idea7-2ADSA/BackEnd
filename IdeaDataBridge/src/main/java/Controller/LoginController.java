package Controller;

import Model.Totem;
import Service.ComponentesService;
import Service.DadosService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;
import java.util.Scanner;

public class LoginController {
    public static void login(ConexoesController conexoes, Totem totem) {
        Scanner leitor = new Scanner(System.in);
        String hostNameTotem = totem.getHostName();
        List<Totem> totensHostName = conexoes.getCon().query
                ("SELECT codigoTotem, hostName FROM totem WHERE hostName = ?",new BeanPropertyRowMapper<>(Totem.class),hostNameTotem);
        if (totensHostName.isEmpty()) {
            System.out.println("Insira o código do totem:");
            Integer codigoTotem = leitor.nextInt();
            totem.setCodigoTotem(codigoTotem);
            List<Totem> totensCodigo = conexoes.getCon().query
                    ("SELECT codigoTotem, hostName FROM totem WHERE codigoTotem = ?", new BeanPropertyRowMapper<>(Totem.class),codigoTotem);
            if (totensHostName.isEmpty() && totensCodigo.isEmpty()) {
                System.out.println("Esse totem ainda não foi cadastrado!");
            }else {
                System.out.println("""
                        Totem encontrado!

                        Inserindo o hostName do totem no banco!
                        """);
                conexoes.getCon().update("update totem set hostName = ? where codigoTotem = ?", hostNameTotem,codigoTotem);
                ComponentesService.inserirComponentesNoBD(conexoes,totem);
            }
        }else {
            try {
                DadosService.inserirDadosNoBanco(conexoes, totem);
            }catch (Exception e) {
                System.out.println("Houve um erro durante o processo de monitoramento!");
                System.out.println(e);
            }

        }
    }
}
