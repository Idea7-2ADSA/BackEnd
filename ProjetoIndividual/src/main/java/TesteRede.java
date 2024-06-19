import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.Date;

public class TesteRede {
    public static void main(String[] args) throws InterruptedException, IOException {
        Conexao conexao = new Conexao();
        VerificaConexao vfC = new VerificaConexao();
        VerificaVelocidade vfV = new VerificaVelocidade();
        EfeitoSonoro ef = new EfeitoSonoro();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        VerificaVelocidade vf = new VerificaVelocidade();

        while (true){
        if (vfC.conexaoInternet()){
                System.out.println("O toten está conectado a internet");
                Rede novaRede = new Rede();
                novaRede.setVelocidade(vf.velocidadeInternet());

                con.update("INSERT INTO Rede (velocidade, dataHora) VALUES (?, CURRENT_TIMESTAMP)",
                        novaRede.getVelocidade());

                Thread.sleep(10000);
            }
        else {
            ef.efeitoSonoro();
            System.out.println("Enviando documento de Log para o usuário");
            Log.createLog(TesteRede.class, "ERRO", "O toten está ficou sem internet ás"+  new Date());
        }
        }
    }
}
