package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;

import java.util.ArrayList;
import java.util.List;

public class HandlerCarrelli {

    private static HandlerCarrelli instance;
    private List<Carrello> carrelliAttivi = new ArrayList<>();

    private HandlerCarrelli() {
    }

    public static HandlerCarrelli getInstance() {
        if (instance == null) {
            instance = new HandlerCarrelli();
        }
        return instance;
    }

    public void aggiungiCarrello(Carrello carrello) {
        if (!carrelliAttivi.contains(carrello)) {
            carrelliAttivi.add(carrello);
        }
    }

    public void rimuoviCarrello(Carrello carrello) {
        carrelliAttivi.remove(carrello);
    }

    public List<Carrello> getCarrelliAttivi() {
        return carrelliAttivi;
    }

}
