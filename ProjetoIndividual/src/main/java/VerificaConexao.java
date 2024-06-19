import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
public class VerificaConexao {
    public Boolean conexaoInternet() {
        try {
            URL url = new URL("http://www.google.com");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            Integer responseCode = conexao.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            return false;
        }
    }
}