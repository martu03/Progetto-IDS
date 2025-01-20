package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;
//import java.util.HashMap;
//import java.util.Map;

public class HandlerMarketplace {
    // TODO: Implementazione con la Lista.
    private List<Prodotto> prodottiMarketplace;

    public HandlerMarketplace() {
        this.prodottiMarketplace = new ArrayList<>();
    }

    public boolean isDisponibile(Prodotto prodotto, int quantita) {
        for (Prodotto p : prodottiMarketplace) {
            if (p.getId() == prodotto.getId() && p.getQuantity() >= quantita) {
                return true;
            }
        }
        return false;
    }

    public void aggiornaProdottiMarketplace(Prodotto prodotto, int quantita) {
        for (Prodotto p : prodottiMarketplace) {
            if (p.getId() == prodotto.getId()) {
                p.setQuantity(quantita);
                return;
            }
        }
        // Se il prodotto non esiste, lo aggiunge al marketplace
        prodotto.setQuantity(quantita);
        prodottiMarketplace.add(prodotto);
    }

    // TODO: Implementazione con la Mappa.
//    private Map<Prodotto, Integer> prodottiMarketplace;
//
//    public HandlerMarketplace() {
//        this.prodottiMarketplace = new HashMap<>();
//    }
//
//    public boolean isDisponibile(Prodotto prodotto) {
//        return prodottiMarketplace.containsKey(prodotto) && prodottiMarketplace.get(prodotto) > 0;
//    }
//
//    public boolean isDispobibile(Prodotto prodotto, int quantita) {
//        return prodottiMarketplace.containsKey(prodotto) && prodottiMarketplace.get(prodotto) >= quantita;
//    }
//
//    public void aggiornaProdottiMarketplace(Prodotto prodotto, int quantita) {
//        prodottiMarketplace.put(prodotto, quantita);
//    }

}
