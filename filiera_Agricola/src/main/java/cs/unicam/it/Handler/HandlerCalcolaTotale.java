package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Carrello.ItemCarrello;

// Classe che si occupa di calcolare il totale del carrello
public class HandlerCalcolaTotale {

    private static HandlerCalcolaTotale instance;

    private HandlerCalcolaTotale() {
    }

    public static HandlerCalcolaTotale getInstance() {
        if (instance == null) {
            instance = new HandlerCalcolaTotale();
        }
        return instance;
    }

    public double calcolaTotale(Carrello carrello) {
        double totale = 0.0;
        for (ItemCarrello item : carrello.getProdottiCarrello()) {
            totale += item.getProdotto().getPrezzo();
        }
        return totale;
    }

}
