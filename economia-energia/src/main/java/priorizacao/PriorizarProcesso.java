package priorizacao;

public class PriorizarProcesso {
    private String processo;
    private String comando;
    private static final String sistemaOperacional = System.getProperty("os.name").toLowerCase();

    public static void main(String[] args) {
        priorizarProcesso("chrome");
    }

    public static void priorizarProcesso(String processo) {
        String comando;
        if (sistemaOperacional.contains("win")) {
            comando = """
                    powershell Get-WmiObject Win32_process -filter 'name = "%s"' | foreach-object { $_.SetPriority(256) }""".formatted(processo);
            enviarComando(comando);
        } else if (sistemaOperacional.endsWith("nux") || sistemaOperacional.endsWith("nix")) {
            comando = """
                    sudo renice -n -20 $(pgrep %s)""".formatted(processo);
            enviarComando(comando);
        } else {
            throw new UnsupportedOperationException("Sistema operacional não suportado para essa ação.");
        }
    }

    private static void enviarComando(String comando) {
        try {
            Runtime.getRuntime().exec(comando);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
