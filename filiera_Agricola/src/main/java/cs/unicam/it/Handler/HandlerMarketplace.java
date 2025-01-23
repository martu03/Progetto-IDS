package cs.unicam.it.Handler;


import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class HandlerMarketplace {
    private Marketplace marketplace;
    private List<Prodotto> prodottiMarketplace;

    public void aggiungiProdottiAlMarketplace(List<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            if (prodottiMarketplace.stream().noneMatch(p -> p.getId()==(prodotto.getId()))) {
                prodottiMarketplace.add(prodotto);
                System.out.println("Prodotto aggiunto al marketplace: " + prodotto);
            } else {
                System.out.println("Prodotto già presente nel marketplace: " + prodotto.getId());
            }
        }
        this.prodottiMarketplace = new ArrayList<>();
    }


    public boolean rimuoviProdottoDalMarketplace(String id) {
        Prodotto prodottoDaRimuovere = null;

        // Cerca il prodotto con un ciclo for
        for (Prodotto prodotto : prodottiMarketplace) {
            if (String.valueOf(prodotto.getId()).equals(id)) { // Confronta id convertito in stringa
                prodottoDaRimuovere = prodotto;
                break;
            }
        }

        if (prodottoDaRimuovere != null) {
            prodottiMarketplace.remove(prodottoDaRimuovere);
            System.out.println("Prodotto rimosso dal marketplace: " + prodottoDaRimuovere.getName());
            return true;
        } else {
            System.out.println("Prodotto con ID " + id + " non trovato nel marketplace.");
            return false;
        }
    }



    public void visualizzaProdottiMarketplace() {
        if (prodottiMarketplace.isEmpty()) {
            System.out.println("Nessun prodotto disponibile nel marketplace.");
        } else {
            System.out.println("Lista prodotti del marketplace:");
            prodottiMarketplace.forEach(System.out::println);
        }
    }

    public boolean isDisponibile(Prodotto prodotto, int quantitaRichiesta) {
        Prodotto prodottoEsistente = prodottiMarketplace.stream()
                .filter(p -> p.getId() == prodotto.getId())
                .findFirst()
                .orElse(null);

        if (prodottoEsistente != null && prodottoEsistente.getQuantity() >= quantitaRichiesta) {
            return true; // Prodotto disponibile
        }
        return false; // Prodotto non disponibile o quantità insufficiente
    }

    public List<Prodotto> getProdottiMarketplace() {
        return prodottiMarketplace;
    }
}