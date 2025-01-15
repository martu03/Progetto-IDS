package cs.unicam.it;

import java.util.List;

public class ProdottoPacchetto extends Prodotto {

    private List<ProdottoSingolo> products;

    public ProdottoPacchetto(String name, double quantity, double price, Descrizione description, Categoria category, Certificazione certification, List<ProdottoSingolo> products) {
        super(name, quantity, price, description, category, certification);
        this.products = products;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public ProdottoSingolo addProduct(ProdottoSingolo singleProduct) {
        return null;
    }

    @Override
    public ProdottoSingolo removeProduct(ProdottoSingolo singleProduct) {
        return null;
    }

    @Override
    public List<ProdottoSingolo> getChild() {
        return List.of();
    }
}