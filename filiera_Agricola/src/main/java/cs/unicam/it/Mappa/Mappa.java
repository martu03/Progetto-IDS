package cs.unicam.it.Mappa;

import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Prodotto.ProdottoSingolo;

import java.util.ArrayList;
import java.util.List;

public class Mappa {

    private static Mappa instance;
    private List<Geolocalizzazione> puntiSullaMappa = new ArrayList<>();

    private Mappa() {
    }

    public static Mappa getInstance() {
        if (instance == null) {
            instance = new Mappa();
        }
        return instance;
    }

    public void aggiungiPunto(Geolocalizzazione punto) {
        puntiSullaMappa.add(punto);
    }

    public void rimuoviPunto(Geolocalizzazione punto) {
        puntiSullaMappa.remove(punto);
    }

    public void visualizzaMappa() {
        System.out.println("Eventi sulla mappa:");
        for (Geolocalizzazione punti: puntiSullaMappa) {
            System.out.println("Punto sulla mappa: " + punti.getLatitudine() + ", " + punti.getLongitudine());
        }

    }
}
