package cs.unicam.it.Prodotto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ProdottoPacchetto extends Prodotto {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Prodotto> prodotti;
    private double prezzo;

    public ProdottoPacchetto() {
        super();
        this.prodotti = new ArrayList<>();
    }

    @Override
    public double getPrezzo() {
        double totale = 0;
        for (Prodotto prodotto : prodotti) {
            totale += prodotto.getPrezzo() * prodotto.getQuantita();
        }
        return totale;
    }

    @Override
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getPrezzoTotale() {
        return getPrezzo() * getQuantita();
    }

    public void setPrezzoTotale(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public void addProdotto(Prodotto prodotto) {
        this.prodotti.add(prodotto);
    }

    public List<Prodotto> getChild() {
        return prodotti;
    }
}