package cs.unicam.it.Handler;


import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Prodotto.Prodotto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class HandlerScadenzaCarrello {
    private final Map<Carrello, LocalDateTime> scadenze;

    public HandlerScadenzaCarrello() {
        this.scadenze = new HashMap<>();
    }

    public void aggiornaScadenza(Carrello carrello) {
        if (!carrello.getProdotti().isEmpty()) {
            scadenze.put(carrello, LocalDateTime.now().plusMinutes(20));
        } else {
            scadenze.remove(carrello);
        }
    }

    public boolean isScaduto(Carrello carrello) {
        return scadenze.containsKey(carrello) && LocalDateTime.now().isAfter(scadenze.get(carrello));
    }

    public void resetScadenza(Carrello carrello) {
        scadenze.remove(carrello);
    }
}
