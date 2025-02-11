package cs.unicam.it.Carrello;

import cs.unicam.it.Prodotto.Prodotto;
import jakarta.persistence.*;

@Entity
@Table(name = "ItemCarrello")
public class ItemCarrello {

    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;
    private int quantita;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
