package cs.unicam.it.Carrello;

import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.stereotype.Component;

@Component
public class ItemCarrello {

    private Prodotto prodotto;
    private int quantita;

    public ItemCarrello() {
    }

    public ItemCarrello(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

}
