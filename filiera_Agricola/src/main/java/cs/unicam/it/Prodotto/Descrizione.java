package cs.unicam.it.Prodotto;

//classe che contiene le info del prodotto generale (sia prodotto singolo che pacchetto)
public class Descrizione {

    private String dettaglio;

    public Descrizione(String dettaglio) {
        this.dettaglio = dettaglio;
    }

    public String getDettaglio() {
        return dettaglio;
    }

}
