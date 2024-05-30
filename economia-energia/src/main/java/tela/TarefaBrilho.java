package tela;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TarefaBrilho implements Runnable {

    private Point localMouse = new Point();
    private List<Point> posicoesMouse = new ArrayList<>();
    private Boolean brilhoBaixo = false;
    private TimerTask task;
    private Timer timer = new Timer();

    public TarefaBrilho() {
        posicoesMouse.add(localMouse.getLocation());
    }

    @Override
    public void run() {
        localMouse = MouseInfo.getPointerInfo().getLocation();

        if (brilhoBaixo && !posicoesMouse.get(0).equals(localMouse)) {
            System.out.println("mouse ativo.");
            BrilhoTela.aumentarBrilho();
            brilhoBaixo = false;
            if (task != null) {
                task.cancel();
            }

        } else if (!brilhoBaixo && posicoesMouse.get(0).equals(localMouse)) {
            System.out.println("mouse inativo.");
            brilhoBaixo = true;
            task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Brilho da tela diminuído.");
                    BrilhoTela.diminuirBrilho();
                }
            };
            timer.schedule(task, 15000);
        }

        posicoesMouse.clear();
        posicoesMouse.add(localMouse.getLocation());
    }
}
