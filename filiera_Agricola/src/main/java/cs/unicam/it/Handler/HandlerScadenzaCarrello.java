package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Prodotto;

import java.time.LocalDateTime;
import java.util.List;

public class HandlerScadenzaCarrello {
    private LocalDateTime scadenza;

    public void aggiornaScadenza(List<Prodotto> prodotti) {
        if (!prodotti.isEmpty()) {
            this.scadenza = LocalDateTime.now().plusMinutes(10);
        } else {
            this.scadenza = null;
        }
    }

    public void verificaScadenzaCarrello() {
        if (isScaduto()) {
            carrello.svuotaCarrello();
            System.out.println("Carrello scaduto");
        }
    }

    public boolean isScaduto() {
        return scadenza != null && LocalDateTime.now().isAfter(scadenza);
    }

}
