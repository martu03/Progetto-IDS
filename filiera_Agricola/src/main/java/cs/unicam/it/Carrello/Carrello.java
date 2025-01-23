package cs.unicam.it.Carrello;

import cs.unicam.it.Handler.HandlerScadenzaCarrello;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoPacchetto;
import cs.unicam.it.Prodotto.ProdottoSingolo;
import cs.unicam.it.Utenti.Acquirente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrello {
    private List<Prodotto> prodotti;
    // TODO: L'acquirente si collega al carrello o il contrario?
    private final Acquirente acquirente;
    private final HandlerScadenzaCarrello handlerScadenzaCarrello;

    public Carrello(Acquirente acquirente) {
        this.prodotti = new ArrayList<>();
        this.acquirente = acquirente;
        this.handlerScadenzaCarrello = new HandlerScadenzaCarrello();
        resetScadenza();
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

    public double calcolaTotale() {
        return prodotti.stream()
                .filter(Objects::nonNull)
                .mapToDouble(prodotto -> prodotto.getPrice() * prodotto.getQuantity())
                .sum();
    }

    public void svuotaCarrello() {
        this.prodotti.clear();
        resetScadenza();
        System.out.println("Carrello svuotato");
    }

    public void resetScadenza() {
        handlerScadenzaCarrello.aggiornaScadenza(prodotti);
    }

    public void mostraProdotti() {
        if (prodotti.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }

        for (Prodotto prodotto : prodotti) {
            if (prodotto instanceof ProdottoPacchetto) {
                System.out.println("Pacchetto: " + prodotto.getName() +
                        " - Prezzo totale: " + prodotto.getPrice());
                for (ProdottoSingolo p : ((ProdottoPacchetto) prodotto).getChild()) {
                    System.out.println("  > Prodotto: " + p.getName() +
                            " - Prezzo unitario: " + p.getPrice());
                }
            } else {
                System.out.println("Prodotto: " + prodotto.getName() +
                        " - Quantità: " + prodotto.getQuantity() +
                        " - Prezzo unitario: " + prodotto.getPrice() +
                        " - Prezzo totale: " + prodotto.getPrice() * prodotto.getQuantity());
            }
        }
    }
}
