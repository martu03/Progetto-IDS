package cs.unicam.it.Carrello;

import cs.unicam.it.Prodotto.Prodotto;
import jakarta.persistence.*;

@Entity
public class ItemCarrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY) // Relazione con Prodotto
    @JoinColumn(name = "prodotto_id", nullable = false) // Chiave esterna per Prodotto
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
