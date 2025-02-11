package cs.unicam.it.Prodotto;

import cs.unicam.it.Utenti.Acquirente;

public class Recensione {

    private String titolo;
    private String descrizione;
    private int voto;
    private Acquirente acquirente;

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
