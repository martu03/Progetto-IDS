package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Utenti.Acquirente;

import java.util.HashMap;
import java.util.Map;

public class HandlerCarrello {
    private final Map<Acquirente, Carrello> carrelli;
    //private final HandlerScadenzaCarrello handlerScadenzaCarrello;
    private final HandlerMarketplace handlerMarketplace;
    private final HandlerCalcolaTotale handlerCalcoloTotale;


    public HandlerCarrello(Carrello carrello, Map<Acquirente, Carrello> carrelli,
                           HandlerMarketplace handlerMarketplace,
                           //HandlerScadenzaCarrello handlerScadenzaCarrello,
                           HandlerCalcolaTotale handlerCalcoloTotale) {
        this.carrelli = new HashMap<>();
        this.handlerMarketplace = handlerMarketplace;
        //this.handlerScadenzaCarrello = handlerScadenzaCarrello;
        this.handlerCalcoloTotale = handlerCalcoloTotale;
    }

    public Carrello getCarrello(Acquirente acquirente) {
        Carrello carrello = carrelli.computeIfAbsent(acquirente, Carrello::new);
        //handlerScadenzaCarrello.aggiornaScadenza(carrello);
        return carrello;
    }

//    public boolean isScaduto(Acquirente acquirente) {
//        Carrello carrello = carrelli.get(acquirente);
//        return carrello != null && handlerScadenzaCarrello.isScaduto(carrello);
//    }

    public void svuotaCarrelloScaduto(Acquirente acquirente) {
        //if (isScaduto(acquirente)) {
            Carrello carrello = carrelli.get(acquirente);
            if (carrello != null) {
                carrello.svuotaCarrello();
                //handlerScadenzaCarrello.resetScadenza(carrello);
                System.out.println("Carrello scaduto per l'acquirente: " + acquirente.getNome());
            }
        //}
    }

    public double calcolaTotale(Acquirente acquirente) {
        Carrello carrello = carrelli.get(acquirente);
        if (carrello == null) {
            System.out.println("Carrello non trovato per l'acquirente: " + acquirente.getNome());
            return 0.0;
        }
        return handlerCalcoloTotale.calcolaTotale(carrello);
    }


}
