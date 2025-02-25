package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Service.CarrelloService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Component
public class HandlerScadenzaCarrello {

    private static HandlerScadenzaCarrello instance;
    private Timer timer;
    private CarrelloService carrelloService = new CarrelloService();

    private HandlerScadenzaCarrello() {
        timer = new Timer();
    }

    public static HandlerScadenzaCarrello getInstance() {
        if (instance == null) {
            instance = new HandlerScadenzaCarrello();
        }
        return instance;
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void avviaMonitoraggioScadenze(int timeoutMinuti) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Carrello> carrelliAttivi = HandlerCarrelli.getInstance().getCarrelliAttivi();
                long now = System.currentTimeMillis();

                for (Carrello carrello : carrelliAttivi) {
                    long carrelloTimestamp = carrello.getTimestamp().getTime();
                    long elapsedTime = now - carrelloTimestamp;

                    // Controlla se il carrello è scaduto
                    if (elapsedTime >= TimeUnit.MINUTES.toMillis(timeoutMinuti)) {
                        //CARRELLO SCADUTO
                        carrelloService.svuotaCarrello(carrello);
                        HandlerCarrelli.getInstance().getCarrelliAttivi().remove(carrello);
                        System.out.println("Carrello scaduto!");
                    }
                }
            }
        }, 0, 1000 * 60); // Esegue ogni minuto
    }

    public void stopMonitoraggioScadenze() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

}
