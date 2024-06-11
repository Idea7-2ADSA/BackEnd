package Service;
import Controller.ConexoesController;
import Model.Alerta;
import Model.TipoHardware;
import Model.Totem;

import java.util.Date;
import java.util.Map;

public class AlertaService {
    public static void  alertas(Totem totem, ConexoesController conexoes, ConexoesController conexoesMysql) {
        final Integer USO_MAXIMO_PROCESSADOR = 70;
        final Integer USO_MAXIMO_RAM = 80;
        final Integer USO_MAXIMO_DISCO = 95;
        Date dataJava = new Date();
        Integer diaSemana = dataJava.getDay();
        Date dataSql = TransformadorData.transformar(dataJava.getDate(),dataJava.getMonth(),dataJava.getYear());


        if (totem.getProcessadorUso() < USO_MAXIMO_PROCESSADOR && totem.getPorcentagemUsoMemoria() >= USO_MAXIMO_RAM) {
            conexoes.getCon().update(
                    "insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                    Alerta.AMARELO.getNome(), TipoHardware.MEMORIA.getNome(), diaSemana, dataSql, totem.getCodigoTotem());
            conexoesMysql.getCon().update(
                    "insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                    Alerta.AMARELO, TipoHardware.MEMORIA, diaSemana, dataSql, totem.getCodigoTotem());

        }else if (totem.getProcessadorUso() >= USO_MAXIMO_PROCESSADOR && totem.getPorcentagemUsoMemoria() < USO_MAXIMO_RAM) {
            conexoes.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                    Alerta.AMARELO.getNome(), TipoHardware.PROCESSADOR.getNome(), diaSemana, dataSql, totem.getCodigoTotem());
            conexoesMysql.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                    Alerta.AMARELO, TipoHardware.PROCESSADOR, diaSemana, dataSql, totem.getCodigoTotem());

        }else if (totem.getProcessadorUso() >= USO_MAXIMO_PROCESSADOR && totem.getPorcentagemUsoMemoria() >= USO_MAXIMO_RAM) {
            for (Map.Entry<String, Long> volume: totem.getPorcentagemUsoVolumes().entrySet()) {
                if (volume.getValue() >= USO_MAXIMO_DISCO) {
                    conexoes.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                            Alerta.VERMELHO.getNome(), "Todos", diaSemana, dataSql, totem.getCodigoTotem());
                    conexoesMysql.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                            Alerta.VERMELHO, "Todos", diaSemana, dataSql, totem.getCodigoTotem());

                }else {
                    conexoes.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                            Alerta.AMARELO.getNome(), "Processador e Memória", diaSemana, dataSql, totem.getCodigoTotem() );
                    conexoesMysql.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                            Alerta.AMARELO, "Processador e Memória", diaSemana, dataSql, totem.getCodigoTotem());
                }
            }
        }else {
            conexoes.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                    Alerta.VERDE.getNome(), "Nenhum", diaSemana, dataSql, totem.getCodigoTotem());
            conexoesMysql.getCon().update("insert into alerta(tipoAlerta, hardWareCorrespondente, diaDaSemana, dataAlerta, fkTotem) values(?, ?, ?, ?, ?)",
                    Alerta.VERDE, "Nenhum", diaSemana, dataSql, totem.getCodigoTotem());
        }
    }
}