package cs.unicam.it.Carrello;

import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.ProdottoPacchetto;
import cs.unicam.it.Prodotto.ProdottoSingolo;
import cs.unicam.it.Utenti.Acquirente;

import java.util.ArrayList;
import java.util.List;

// Carrello dell'acquirente
public class Carrello {
    private final List<Prodotto> prodotti;

    public Carrello() {
        this.prodotti = new ArrayList<>();
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    // Aggiunge un prodotto al carrello
    public void aggiungiProdotto(Prodotto prodotto, int quantita) {
        if (isProdottoPresente(prodotto)) {
            Prodotto prodottoPresente = this.getProdottoByID(prodotto.getId());
            int nuovaQuantita = prodottoPresente.getQuantita() + quantita;
            prodottoPresente.setQuantita(nuovaQuantita);
        } else {
            Prodotto prodottoDaAggiungere = prodotto.clone(prodotto);
            prodottoDaAggiungere.setQuantita(quantita);
            prodotti.add(prodottoDaAggiungere);
        }
    }

    //verifica se il prodotto è già presente nel carrello
    private boolean isProdottoPresente(Prodotto prodotto) {
        return prodotti.stream().anyMatch(p -> p.getId() == prodotto.getId());
    }

    public Prodotto getProdottoByID(int id) {
        for (Prodotto p : prodotti) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void rimuoviProdotto(Prodotto prodotto) {
        if(!isProdottoPresente(prodotto)) {
            System.out.println("Prodotto non presente nel carrello.");
        }
        prodotti.remove(prodotto);
    }

    public boolean modificaQuantitaProdotto(Prodotto prodotto, int nuovaQuantita) {
        if(!isProdottoPresente(prodotto)) {
            System.out.println("Prodotto non presente nel carrello.");
            return false;
        }

        this.getProdottoByID(prodotto.getId()).setQuantita(nuovaQuantita);
        return true;
    }

    public void svuotaCarrello() {
        this.prodotti.clear();
        System.out.println("Carrello svuotato");
    }

    public void mostraProdotti() {
        if (prodotti.isEmpty()) {
            System.out.println("Il carrello è vuoto.");
            return;
        }

        for (Prodotto prodotto : prodotti) {
            mostraDettagliProdotto(prodotto, "");
        }
    }

    private void mostraDettagliProdotto(Prodotto prodotto, String indent) {
        if (prodotto instanceof ProdottoPacchetto) {
            System.out.println(indent + "Pacchetto: " + prodotto.getNome() +
                    " - Prezzo totale: " + prodotto.getPrezzo());
            for (Prodotto p : ((ProdottoPacchetto) prodotto).getChild()) {
                mostraDettagliProdotto(p, indent + "  > ");
            }
        } else {
            System.out.println(indent + "Prodotto: " + prodotto.getNome() +
                    " - Quantità: " + prodotto.getQuantita() +
                    " - Prezzo unitario: " + prodotto.getPrezzo() +
                    " - Prezzo totale: " + prodotto.getPrezzo() * prodotto.getQuantita());
        }
    }
}
