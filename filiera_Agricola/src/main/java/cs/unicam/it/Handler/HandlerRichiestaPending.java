package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class HandlerRichiestaPending {

    private List<Prodotto> prodottiPending = new ArrayList<>();

    public void aggiungiProdotto(Prodotto prodotto) {
        prodottiPending.add(prodotto);
        System.out.println("Prodotto aggiunto alla lista pending: " + prodotto);
    }
}
