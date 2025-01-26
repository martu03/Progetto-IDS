package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;

// Classe che si occupa di calcolare il totale del carrello
public class HandlerCalcolaTotale {

    public double calcolaTotale(Carrello carrello) {
        return carrello.getProdotti().stream()
                .mapToDouble(prodotto -> prodotto.getPrice() * prodotto.getQuantity())
                .sum();
    }
}
