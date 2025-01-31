package cs.unicam.it.Handler;

import cs.unicam.it.Observer.Observer;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandlerScadenzaProdotto {

    private final Map<Prodotto, List<Observer>> osservatoriProdotto = new HashMap<>();

    public void aggiungiOsservatore(Prodotto prodotto, Observer osservatore) {
        osservatoriProdotto.computeIfAbsent(prodotto, k -> new ArrayList<>()).add(osservatore);
    }

    public void rimuoviOsservatore(Prodotto prodotto, Observer osservatore) {
        List<Observer> osservatori = osservatoriProdotto.get(prodotto);
        if (osservatori != null) {
            osservatori.remove(osservatore);
            if (osservatori.isEmpty()) {
                osservatoriProdotto.remove(prodotto);
            }
        }
    }

    public void notificaScadenza(Prodotto prodotto) {
        List<Observer> osservatori = osservatoriProdotto.get(prodotto);
        if (osservatori != null) {
            for (Observer osservatore : osservatori) {
                osservatore.update(prodotto);
            }
        }
    }

    public void verificaScadenze(Prodotto prodotto) {
        if (prodotto.getScadenza().isBefore(java.time.LocalDate.now())) {
            notificaScadenza(prodotto);
        }
    }

}