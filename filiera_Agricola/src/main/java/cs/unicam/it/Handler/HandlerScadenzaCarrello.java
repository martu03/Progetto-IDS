package cs.unicam.it.Handler;


import cs.unicam.it.Prodotto.Prodotto;

import java.time.LocalDateTime;
import java.util.List;

public class HandlerScadenzaCarrello {
    private LocalDateTime scadenza;

    public void aggiornaScadenza(List<Prodotto> prodotti) {
        if (!prodotti.isEmpty()) {
            this.scadenza = LocalDateTime.now().plusMinutes(20);
        } else {
            this.scadenza = null;
        }
    }

    public boolean isScaduto() {
        return scadenza != null && LocalDateTime.now().isAfter(scadenza);
    }

}
