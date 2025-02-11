package cs.unicam.it.Prodotto;

import jakarta.persistence.Entity;

@Entity
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