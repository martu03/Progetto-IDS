package cs.unicam.it.Utenti;

import cs.unicam.it.Mappa.Geolocalizzazione;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Entity
@DiscriminatorValue("Trasformatore")
public class Trasformatore extends Azienda {

    public Trasformatore(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password, sede);
    }

    public Trasformatore() {
        super();
    }
}