import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;

public class VerificaVelocidade {

    private Long velocidade;
    public Long velocidadeInternet(){
        String host = "www.google.com";
        try {
            InetAddress endereco = InetAddress.getByName(host);

            Long inicioTempoVelocidade = System.currentTimeMillis();
            Boolean alcance = endereco.isReachable(10000);
            Long finalTempoVelocidade = System.currentTimeMillis();

            if (alcance) {
                velocidade = finalTempoVelocidade - inicioTempoVelocidade;
                System.out.println("Velocidade Internet é de: " + velocidade + " milissegundos");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return velocidade;
    }

    public Long getVelocidade() {
        return velocidade;
    }
}
