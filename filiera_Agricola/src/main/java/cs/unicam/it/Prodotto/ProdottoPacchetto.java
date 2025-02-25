package cs.unicam.it.Prodotto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class ProdottoPacchetto extends Prodotto {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Prodotto> prodotti;

    public ProdottoPacchetto() {
        super();
        this.prodotti = new ArrayList<>();
    }

    @Override
    public double getPrezzoTotale() {
        return this.getPrezzoUnitario() * getQuantita();
    }

    @Override
    public double getPrezzoUnitario() {
        double totale = 0;
        for (Prodotto prodotto : prodotti) {
            totale += prodotto.getPrezzoTotale();
        }
        return totale;
    }

    @Override
    public Date getScadenza() {
        return calcolaScadenzaMinima();
    }

    private Date calcolaScadenzaMinima() {
        Date scadenza = null;
        for (Prodotto prodotto : prodotti) {
            if (scadenza == null || prodotto.getScadenza().before(scadenza)) {
                scadenza = prodotto.getScadenza();
            }
        }
        return scadenza;
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