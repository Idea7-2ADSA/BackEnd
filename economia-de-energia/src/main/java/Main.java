import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String comando;
        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
        if (sistemaOperacional.contains("win")) {
            comando = """
                    powershell (Get-WmiObject -Namespace root/WMI -Class WmiMonitorBrightnessMethods).WmiSetBrightness(1, 1)""";
        } else if (sistemaOperacional.contains("nix") || sistemaOperacional.contains("nux")) {
            comando = """
                    monitor=$(xrandr | grep " connected" | cut -f1 -d " ");xrandr --output "$monitor" --brightness 0.15""";
        } else {
            throw new UnsupportedOperationException("Sistema operacional não suportado para essa ação.");
        }

        try {
            Process processo = Runtime.getRuntime().exec(comando);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
