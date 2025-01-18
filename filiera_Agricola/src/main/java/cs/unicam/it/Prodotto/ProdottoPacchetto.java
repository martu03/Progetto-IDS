package cs.unicam.it.Prodotto;

import java.util.List;

public class ProdottoPacchetto extends Prodotto {

    private List<ProdottoSingolo> products;

    public ProdottoPacchetto() {
        super();
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

    public void setProducts(List<ProdottoSingolo> products) {
        this.products = products;
    }
}