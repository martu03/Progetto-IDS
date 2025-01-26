package cs.unicam.it.Carrello;

import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoPacchetto;
import cs.unicam.it.Prodotto.ProdottoSingolo;
import cs.unicam.it.Utenti.Acquirente;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private final List<Prodotto> prodotti;
    private final Acquirente acquirente;

    public Carrello(Acquirente acquirente) {
        this.prodotti = new ArrayList<>();
        this.acquirente = acquirente;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

    public void aggiungiProdotto(Prodotto prodotto, int quantita) {
        Prodotto prodottoEsistente = prodotti.stream()
                .filter(p -> p.equals(prodotto))
                .findFirst()
                .orElse(null);

        if (prodottoEsistente != null) {
            prodottoEsistente.setQuantity(prodottoEsistente.getQuantity() + quantita);
        } else {
            prodotto.setQuantity(quantita);
            prodotti.add(prodotto);
        }
    }

    public boolean rimuoviProdotto(Prodotto prodotto) {
        return prodotti.remove(prodotto);
    }

    public boolean modificaQuantitaProdotto(Prodotto prodotto, int nuovaQuantita) {
        if (nuovaQuantita < 0) {
            System.out.println("Quantità non valida.");
            return false;
        }

        for (Prodotto p : prodotti) {
            if (p.equals(prodotto)) {
                p.setQuantity(nuovaQuantita);
                return true;
            }
        }

        System.out.println("Prodotto non presente nel carrello.");
        return false;
    }

//    public double calcolaTotale() {
//        return prodotti.stream()
//                .filter(Objects::nonNull)
//                .mapToDouble(prodotto -> prodotto.getPrice() * prodotto.getQuantity())
//                .sum();
//    }

    public void svuotaCarrello() {
        this.prodotti.clear();
        //resetScadenza();
        System.out.println("Carrello svuotato");
    }

    //TODO da vedere
//    public void resetScadenza() {
//        handlerScadenzaCarrello.aggiornaScadenza(prodotti);
//    }

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
