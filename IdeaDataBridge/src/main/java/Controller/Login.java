package Controller;

import Model.Totem;
import Service.ConexoesInterface;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;
import java.util.Scanner;

public class Login {
    public static void login(ConexoesInterface conexoes, Totem totem) {
        Scanner leitor = new Scanner(System.in);
        String hostNameTotem = totem.getHostName();
        List<Totem> totensHostName = conexoes.getCon().query
                ("SELECT codigoTotem, hostName FROM totem WHERE hostName = ?",new BeanPropertyRowMapper<>(Totem.class),hostNameTotem);
        System.out.println(totensHostName);
        if (totensHostName.isEmpty()) {
            System.out.println("Insira o código do totem:");
            Integer codigoTotem = leitor.nextInt();
            totem.setCodigoTotem(codigoTotem);
            List<Totem> totensCodigo = conexoes.getCon().query
                    ("SELECT codigoTotem, hostName FROM totem WHERE codigoTotem = ?", new BeanPropertyRowMapper<>(Totem.class),codigoTotem);
            System.out.println(totensCodigo);
            if (totensHostName.isEmpty() && totensCodigo.isEmpty()) {
                System.out.println("Esse totem ainda não foi cadastrado!");
            }else {
                System.out.println("""
                        Totem encontrado!

                        Inserindo o hostName do totem no banco!
                        """);
                conexoes.getCon().update("update totem set hostName = ? where codigoTotem = ?", hostNameTotem,codigoTotem);
                InsertComponentes.inserirComponentesNoBD(conexoes,totem);
            }
        }else {
            try {
                InsertDados.inserirDadosNoBanco(conexoes, totem);
            }catch (Exception e) {
                System.out.println("Houve um erro durante o processo de monitoramento!");
                System.out.println(e);
            }

        }
    }
}
