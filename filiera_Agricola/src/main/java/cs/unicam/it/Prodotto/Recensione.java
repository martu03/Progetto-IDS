package cs.unicam.it.Prodotto;

import cs.unicam.it.Utenti.Acquirente;
import jakarta.persistence.*;

@Entity
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titolo;
    private String descrizione;
    private int voto;
    @ManyToOne
    private Acquirente acquirente;
    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    private Prodotto prodotto;

    public Recensione() {
    }

    public Recensione(String titolo, String descrizione, int voto, Acquirente acquirente) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.voto = voto;
        this.acquirente = acquirente;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getVoto() {
        return voto;
    }

    public Acquirente getAcquirente() {
        return acquirente;
    }

}
