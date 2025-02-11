package cs.unicam.it.Marketplace;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Marketplace {

    private static Marketplace instance;
    private List<Prodotto> inventarioMarketplace;

    private Marketplace() {
        this.inventarioMarketplace = new ArrayList<>();
    }

    public static Marketplace getInstance() {
        if (instance == null) {
            instance = new Marketplace();
        }
        return instance;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        inventarioMarketplace.add(prodotto);
        System.out.println("Prodotto " + prodotto.getNome() + " aggiunto al marketplace.");
    }

    public void rimuoviProdotto(int id) {
        inventarioMarketplace.remove(getProdottoById(id));
    }

    public List<Prodotto> filtraPerCategoria(Categoria categoria) {
        return inventarioMarketplace.stream()
                .filter(prodotto -> prodotto.getCategoria() == categoria)
                .collect(Collectors.toList());
    }

    public List<Prodotto> getProdotti() {
        return inventarioMarketplace;
    }

    public Prodotto getProdottoById(int id) {
        return inventarioMarketplace.get(id);
    }

    public void visualizzaProdottiMarketplace() {
        inventarioMarketplace.forEach(prodotto ->
                System.out.println("Nome prodotto: " + prodotto.getNome() + " - Prezzo: " + prodotto.getPrezzo()));
    }

}