package Controller;

import Model.Totem;
import Service.ConexoesInterface;

public class InsertComponentes {
    public static void inserirComponentesNoBD(ConexoesInterface conexoes, Totem totem ) {
        Integer codigoTotem = totem.getCodigoTotem();
        System.out.println("Inserindo informações dos componentes dessa maquina no banco de dados!");
        if (totem.getProcessador() != null) {
            conexoes.getCon().update("insert into hardware(tipo,fkTotem) values('processador',?)",codigoTotem);
        }
        if (totem.getMemoria() != null) {
            conexoes.getCon().update("insert into hardware(tipo,fkTotem) values('memoria',?)",codigoTotem);
        }
        if (totem.getGrupoDisco() != null) {
            conexoes.getCon().update("insert into hardware(tipo,fkTotem) values('disco',?)",codigoTotem);
        }
        try {
            InsertDados.inserirDadosNoBanco(conexoes, totem);
        }catch (Exception e) {
            System.out.println("Houve um erro durante o processo de monitoramento!");
            System.out.println(e);
        }
    }
}
