package cs.unicam.it.Marketplace;

import cs.unicam.it.Prodotto.Categoria;
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
            System.out.println("- " + prodotto.getNome() + ", Prezzo: " + prodotto.getPrezzo());
        }
    }

    public Prodotto getProdottoById(int id) {
        for (Prodotto prodotto : inventarioProdotti) {
            if (prodotto.getId() == id) {
                return prodotto;
            }
        }
        return null;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        inventarioProdotti.add(prodotto);
    }

    public void rimuoviProdotto(Prodotto prodotto) {
        inventarioProdotti.remove(prodotto);
    }

    // Metodo base per ottenere i prodotti
    public List<Prodotto> getInventarioProdotti() {
        return inventarioProdotti;
    }

    // Metodo base per verificare se un prodotto esiste nel marketplace
    public boolean contieneProdotto(int IDProdotto) {
        return inventarioProdotti.contains(getProdottoById(IDProdotto));
    }

    // Metodo per filtrare i prodotti in base alla categoria
    public List<Prodotto> filtraProdottiPerCategoria(Categoria categoria) {
        List<Prodotto> prodottiFiltrati = new ArrayList<>();
        for (Prodotto prodotto : inventarioProdotti) {
            if (prodotto.getCategoria().equals(categoria)) {
                prodottiFiltrati.add(prodotto);
            }
        }
        return prodottiFiltrati;
    }

}