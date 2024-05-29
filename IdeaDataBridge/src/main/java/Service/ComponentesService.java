package Service;

import Controller.ConexoesController;
import Model.TipoHardware;
import Model.Totem;

public class ComponentesService {
    public static void inserirComponentesNoBD(ConexoesController conexoes, Totem totem ) {
        Integer codigoTotem = totem.getCodigoTotem();
        System.out.println("Inserindo informações dos componentes dessa maquina no banco de dados!");
        if (totem.getProcessador() != null) {
            conexoes.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.PROCESSADOR,codigoTotem);
        }
        if (totem.getMemoria() != null) {
            conexoes.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.MEMORIA,codigoTotem);
        }
        if (totem.getGrupoDisco() != null) {
            conexoes.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.DISCO,codigoTotem);
        }
        try {
            DadosService.inserirDadosNoBanco(conexoes, totem);
        }catch (Exception e) {
            System.out.println("Houve um erro durante o processo de monitoramento!");
            System.out.println(e);
        }
    }
}
