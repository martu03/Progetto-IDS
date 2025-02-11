package cs.unicam.it.Prodotto;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProdottoPacchetto extends Prodotto {

    private double prezzo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Prodotto> prodotti;

    public ProdottoPacchetto() {
        super();
        this.prezzo = 0;
    }

    //metodo per calcolare il prezzo totale del pacchetto
    @Override
    public double getPrezzo() {
        double totale = 0;
        for (Prodotto prodotto : prodotti) {
            totale += prodotto.getPrezzo();
        }
        return totale;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    //metodo per aggiungere prodotti al pacchetto
    public void setProdotti(List<Prodotto> prodotti) {
        for (Prodotto prodotto : prodotti) {
            prodotti.add(prodotto);
        }
    }

    public List<Prodotto> getChild() {
        return prodotti;
    }
}