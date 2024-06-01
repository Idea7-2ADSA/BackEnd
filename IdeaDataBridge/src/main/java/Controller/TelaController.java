package Controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TelaController {
    private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        iniciarTempoOcioso();
    }

    private static void iniciarTempoOcioso() {
        System.out.println("iniciarTempoOcioso()");

        executor.scheduleAtFixedRate(new TarefaBrilhoController(), 0, 500, TimeUnit.MILLISECONDS);
    }
}
