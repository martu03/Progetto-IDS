package cs.unicam.it.Marketplace;

import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;

// Classe per il marketplace
public class Marketplace {

    // Lista dei prodotti approvati
    private List<Prodotto> inventarioProdotti;

    public Marketplace() {
        this.inventarioProdotti = new ArrayList<>();
    }

    // Metodo per mostrare i prodotti nel marketplace
    public void mostraProdotti() {
        System.out.println("Prodotti nel marketplace:");
        for (Prodotto prodotto : inventarioProdotti) {
            System.out.println("- " + prodotto.getName() + ", Prezzo: " + prodotto.getPrice());
        }
    }

    // Metodo base per ottenere i prodotti
    public List<Prodotto> getInventarioProdotti() {
        return inventarioProdotti;
    }

    // Metodo base per verificare se un prodotto esiste nel marketplace
    public boolean contieneProdotto(Prodotto prodotto) {
        return inventarioProdotti.contains(prodotto);
    }
}