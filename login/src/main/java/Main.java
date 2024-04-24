 import Model.HardWare;
 import Model.Totem;
 import Service.Conexao;
 import org.springframework.jdbc.core.BeanPropertyRowMapper;
 import org.springframework.jdbc.core.JdbcTemplate;

 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import java.util.Scanner;

 public class Main {
    public static Conexao conexao = new Conexao();
    public static JdbcTemplate conn = conexao.getCon();
    static Main main = new Main();
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
        login(conn);
    }
    public static void login(JdbcTemplate conn) {
        Scanner leitor = new Scanner(System.in);
        String macAddressTotem = totem.getMacAddress();
        System.out.println(totem.getMacAddress());
        List<Totem> totensMacAAdress = conn.query
                ("SELECT codigoTotem, macAddress FROM totem WHERE macAddress = ?",new BeanPropertyRowMapper<>(Totem.class),macAddressTotem);
        System.out.println(totensMacAAdress);
        if (totensMacAAdress.isEmpty()) {
            System.out.println("Insira o código do totem:");
            Integer codigoTotem = leitor.nextInt();
            totem.setCodigoTotem(codigoTotem);
            List<Totem> totensCodigo = conn.query
                    ("SELECT codigoTotem, macAddress FROM totem WHERE codigoTotem = ?", new BeanPropertyRowMapper<>(Totem.class),codigoTotem);
            System.out.println(totensCodigo);
            if (totensMacAAdress.isEmpty() && totensCodigo.isEmpty()) {
                System.out.println("Esse totem ainda não foi cadastrado!");
            }else {
                System.out.println("""
                        Totem encontrado!

                        Inserindo o macAddress do totem no banco!
                        """);
                conn.update("update totem set macAddress = ? where codigoTotem = ?", macAddressTotem,codigoTotem);
                main.inserirComponentesNoBD(conn);
            }
        }else {
            main.inserirComponentesNoBD(conn);
//            main.inserirDadosNoBanco(conn);
        }
    }

    public void inserirComponentesNoBD(JdbcTemplate conn) {
        Integer codigoTotem = totem.getCodigoTotem();
        System.out.println("Inserindo informações dos componentes dessa maquina no banco de dados!");
        if (totem.getProcessador() != null) {
            conn.update("insert into hardware(tipo,fkTotem) values('processador',?)",codigoTotem);
        }
        if (totem.getMemoria() != null) {
            conn.update("insert into hardware(tipo,fkTotem) values('memoria',?)",codigoTotem);
        }
        if (totem.getGrupoDisco() != null) {
            conn.update("insert into hardware(tipo,fkTotem) values('disco',?)",codigoTotem);
        }
//        main.inserirDadosNoBanco(conn);
    }

    public void inserirDadosNoBanco(JdbcTemplate conn) {
        Integer codigoTotem = totem.getCodigoTotem();
        Integer idProcesador = null;
        Integer idMemoria = null;
        Integer idDisco = null;
        Date date = new Date();
        String dataHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        List<HardWare> componentes = conn.query
                ("selec * from hardware where codigoTotem = ?", new BeanPropertyRowMapper<>(HardWare.class),codigoTotem);
        for (HardWare componente : componentes) {
            if (componente.getTipo().equalsIgnoreCase("processador")) {
                idProcesador = componente.getId();
            } else if (componente.getTipo().equalsIgnoreCase("memoria")) {
                idMemoria = componente.getId();
            }else {
                idDisco = componente.getId();
            }
        }
        System.out.println("Monitorando os componentes dessa maquina...");
        //Inserindo dados do processador
        conn.update
                ("insert into dadosHardware(uso, dataHora, nome, fkHardWare,fkTotem) values(?, ?, ?, ?, ?)",
                totem.getProcessadorUso(), dataHora, totem.getProcessadorNome(), idProcesador, totem.getCodigoTotem());
    }
}
