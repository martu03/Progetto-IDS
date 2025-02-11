package cs.unicam.it.Handler;

import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class HandlerScadenzaProdotto {

    private Timer timer;

    public HandlerScadenzaProdotto() {
        this.timer = new Timer();
    }

    public void avviaMonitoraggioScadenze(int timeoutMinuti) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<Prodotto> prodotti = Marketplace.getInstance().getProdotti();
                long now = System.currentTimeMillis();

                for (Prodotto prodotto : prodotti) {
                    if (prodotto.getScadenza() != null) {
                        long dataScadenzaProdotto = prodotto.getScadenza().getTime();
                        long differenza = now - dataScadenzaProdotto;

                        // Controlla se il prodotto è scaduto
                        if (differenza >= 0) {
                            notificaAzienda(prodotto, "scaduto");
                            prodotto.getAzienda().rimuoviProdotto(prodotto.getId());
                        } else {
                            // Calcola i giorni rimanenti alla scadenza
                            long giorniRimanenti = TimeUnit.MILLISECONDS.toDays(Math.abs(differenza));
                            if (giorniRimanenti <= 7) {
                                applicaOfferta(prodotto);
                                notificaAzienda(prodotto, "in offerta");
                            }
                        }
                    }
                }
            }
        }, 0, 1000 * 60);
    }

    private void notificaAzienda(Prodotto prodotto, String stato) {
        System.out.println("Il prodotto " + prodotto.getNome() + "dell'Azienda "
                            + prodotto.getNome() +" è : " + stato);
    }

    private void applicaOfferta(Prodotto prodotto) {
        double offerta = (prodotto.getPrezzo() * 20)/100;
        double nuovoPrezzo = prodotto.getPrezzo() - offerta;
        prodotto.setPrezzo(nuovoPrezzo);
        System.out.println("Il prodotto " + prodotto.getNome() + " è ora in offerta con un prezzo di " + nuovoPrezzo);
    }

    public void stopMonitoraggioScadenze() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

}