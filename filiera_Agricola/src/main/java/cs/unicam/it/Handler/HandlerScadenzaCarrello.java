package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Carrello.ItemCarrello;
import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Component
public class HandlerScadenzaCarrello {

    private static HandlerScadenzaCarrello instance;
    private Timer timer;

    private HandlerScadenzaCarrello() {
        timer = new Timer();
    }

    public static HandlerScadenzaCarrello getInstance() {
        if (instance == null) {
            instance = new HandlerScadenzaCarrello();
        }
        return instance;
    }

    @Scheduled(fixedRate = 60000)
    public void monitoraScadenze() {
        List<Carrello> carrelliAttivi = HandlerCarrelli.getInstance().getCarrelliAttivi();
        long now = System.currentTimeMillis();
        for (Carrello carrello : carrelliAttivi) {
            long elapsedTime = now - carrello.getTimestamp().getTime();
            if (elapsedTime >= TimeUnit.MINUTES.toMillis(11)) {
                carrello.svuota();
                HandlerCarrelli.getInstance().getCarrelliAttivi().remove(carrello);
                System.out.println("Carrello scaduto!");
            }
        }
    }

    public void stopMonitoraggioScadenze() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

}
