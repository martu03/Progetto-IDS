package cs.unicam.it.Handler;


import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;

public class HandlerMarketplace {
    private Marketplace marketplace;

    //vedo se il prodotto è presente nel marketplace, se si aumento qta, altrimenti lo aggiungo
    public void aggiungiProdottiAlMarketplace(List<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            if (!marketplace.contieneProdotto(prodotto.getId())) {
                marketplace.aggiungiProdotto(prodotto);
                System.out.println("Prodotto aggiunto al marketplace");
            } else {
                System.out.println("Prodotto già presente nel marketplace");
                modificaQuantitaMarketplace(prodotto.getId(), prodotto.getQuantita());
            }
        }
    }

    //modifica la quantità di un prodotto presente nel marketplace
    public void modificaQuantitaMarketplace(int IDProdotto, int nuovaQuantita) {
        Prodotto prodotto = marketplace.getProdottoById(IDProdotto);
        if (marketplace.contieneProdotto(IDProdotto)) {
            prodotto.setQuantita(nuovaQuantita);
            System.out.println("Quantità prodotto modificata con successo.");
        } else {
            System.out.println("Prodotto non presente nel marketplace.");
        }
    }


    public boolean rimuoviProdottoDalMarketplace(int id) {
        Prodotto prodottoDaRimuovere = marketplace.getProdottoById(id);

        if (prodottoDaRimuovere != null) {
            marketplace.rimuoviProdotto(prodottoDaRimuovere);
            System.out.println("Prodotto rimosso dal marketplace: " + prodottoDaRimuovere.getNome());
            return true;
        } else {
            System.out.println("Prodotto con ID " + id + " non trovato nel marketplace.");
            return false;
        }
    }

    public void visualizzaProdottiMarketplace() {
        if(marketplace.getInventarioProdotti().isEmpty()) {
            System.out.println("Nessun prodotto presente nel marketplace.");
            return;
        } else {
            marketplace.mostraProdotti();
        }
    }

    public boolean isDisponibile(int id, int quantita) {
        Prodotto prodotto = marketplace.getProdottoById(id);
        if (prodotto != null) {
            return prodotto.getQuantita() >= quantita;
        }
        return false;
    }

}