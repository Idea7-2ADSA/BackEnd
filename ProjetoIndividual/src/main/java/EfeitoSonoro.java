import javax.sound.sampled.*;
import java.io.*;

public class EfeitoSonoro {

    public static void main(String[] args) {
        EfeitoSonoro efeitoSonoro = new EfeitoSonoro();
        efeitoSonoro.efeitoSonoro();
    }

    public void efeitoSonoro() {
        String filePath = "som.wav";
        // Tenta reproduzir o efeito sonoro
        try {
            playSoundFromFile(filePath);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void playSoundFromFile(String filePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        File audioFile = new File(filePath);

        // Carrega o arquivo de áudio
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

        // Obtém um Clip de áudio
        Clip clip = AudioSystem.getClip();

        // Abre o stream de áudio como um clip
        clip.open(audioStream);

        // Inicia a reprodução do clip
        clip.start();

        // Espera até que o som termine
        while (!clip.isRunning()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Espera até que o som termine
        while (clip.isRunning()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Fecha o clip e libera os recursos
        clip.close();
        audioStream.close();
    }
}
