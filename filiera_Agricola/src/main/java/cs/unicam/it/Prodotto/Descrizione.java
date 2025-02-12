package cs.unicam.it.Prodotto;

import org.springframework.stereotype.Component;

@Component
public class Descrizione {

    private String dettaglio;

    public Descrizione() {
    }

    public Descrizione(String dettaglio) {
        this.dettaglio = dettaglio;
    }

    public String getDettaglio() {
        return dettaglio;
    }

}
