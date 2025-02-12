package cs.unicam.it.Utenti;

import cs.unicam.it.Mappa.Geolocalizzazione;
import org.springframework.stereotype.Component;

@Component
public class Produttore extends Azienda {

    public Produttore(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password, sede);
    }

    public Produttore() {
        super();
    }
}