package cs.unicam.it.Prodotto;

import org.springframework.stereotype.Component;

@Component
public class ProdottoSingolo extends Prodotto {

    private double prezzo;

    public ProdottoSingolo() {
        super();
        this.prezzo = 0;
    }

    @Override
    public double getPrezzo() {
        return prezzo * getQuantita();
    }
}