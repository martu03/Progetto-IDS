package cs.unicam.it.Prodotto;

import java.util.List;

public class ProdottoSingolo extends Prodotto {

    public ProdottoSingolo(){
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
}