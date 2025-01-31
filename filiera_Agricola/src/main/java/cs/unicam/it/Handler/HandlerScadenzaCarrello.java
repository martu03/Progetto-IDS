package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Classe che si occupa di gestire la scadenza del carrello
public class HandlerScadenzaCarrello {

    private final Map<Carrello, LocalDateTime> scadenze;

    public HandlerScadenzaCarrello() {
        this.scadenze = new HashMap<>();
    }

    public void resetScadenza(Carrello carrello) {
        if (!carrello.getProdotti().isEmpty()) {
            scadenze.put(carrello, LocalDateTime.now().plusMinutes(20));
        } else {
            scadenze.remove(carrello);
        }
    }

    public boolean isScaduto(Carrello carrello) {
        return scadenze.containsKey(carrello) && LocalDateTime.now().isAfter(scadenze.get(carrello));
    }

    public void eliminaTimer(Carrello carrello) {
        scadenze.remove(carrello);
    }
}
