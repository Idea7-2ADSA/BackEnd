package Service;

import Controller.ConexoesController;
import Model.HardWare;
import Model.TipoHardware;
import Model.Totem;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class ComponentesService {
    public static void inserirComponentesNoBD(ConexoesController conexoes, ConexoesController conexoesMysql, Totem totem ) {
        List<HardWare> hardwares = conexoesMysql.getCon().query("select idHardWare, tipo from hardware where fkTotem = ?", new BeanPropertyRowMapper<>(HardWare.class),totem.getCodigoTotem());
        if (hardwares.isEmpty()) {
            Integer codigoTotem = totem.getCodigoTotem();
            System.out.println("Inserindo informações dos componentes dessa maquina no banco de dados!");
            if (totem.getProcessador() != null) {
                conexoes.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.PROCESSADOR.getNome(),codigoTotem);
                conexoesMysql.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.PROCESSADOR.getNome(),codigoTotem);
            }
            if (totem.getMemoria() != null) {
                conexoes.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.MEMORIA.getNome(),codigoTotem);
                conexoesMysql.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.MEMORIA.getNome(),codigoTotem);
            }
            if (totem.getGrupoDisco() != null) {
                conexoes.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.DISCO.getNome(),codigoTotem);
                conexoesMysql.getCon().update("insert into hardware(tipo,fkTotem) values(?,?)", TipoHardware.DISCO.getNome(),codigoTotem);
            }
            try {
                DadosService.inserirDadosNoBanco(conexoes, conexoesMysql, totem);
            }catch (Exception e) {
                System.out.println("Houve um erro durante o processo de monitoramento!");
                System.out.println(e);
            }
        }else {
            try {
                DadosService.inserirDadosNoBanco(conexoes, conexoesMysql, totem);
            }catch (Exception e) {
                System.out.println("Houve um erro durante o processo de monitoramento!");
                System.out.println(e);
            }
        }

    }
}
