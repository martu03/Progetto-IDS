package cs.unicam.it.Marketplace;

import cs.unicam.it.Prodotto.Prodotto;

public class GestoreMarketplace {
    private final Marketplace marketplace;

    public GestoreMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
    }

    // Aggiunge un prodotto con controlli
    public void aggiungiProdotto(Prodotto prodotto) {
        if (!marketplace.contieneProdotto(prodotto)) {
            marketplace.getInventarioProdotti().add(prodotto);
            System.out.println("Prodotto " + prodotto.getName() + " aggiunto al marketplace.");
        } else {
            System.out.println("Prodotto già presente nel marketplace.");
        }
    }

    // Rimuove un prodotto
    public void rimuoviProdotto(Prodotto prodotto) {
        if (marketplace.contieneProdotto(prodotto)) {
            marketplace.getInventarioProdotti().remove(prodotto);
            System.out.println("Prodotto " + prodotto.getName() + " rimosso dal marketplace.");
        } else {
            System.out.println("Prodotto non presente nel marketplace.");
        }
    }

    // Modifica un prodotto
    public void modificaProdotto(Prodotto prodotto, String nuovoNome, double nuovoPrezzo, int nuovaQuantita) {
        if (marketplace.contieneProdotto(prodotto)) {
            prodotto.setName(nuovoNome);
            prodotto.setPrice(nuovoPrezzo);
            prodotto.setQuantity(nuovaQuantita);
            System.out.println("Prodotto " + prodotto.getName() + " modificato con successo.");
        } else {
            System.out.println("Prodotto non presente nel marketplace.");
        }
    }
}
