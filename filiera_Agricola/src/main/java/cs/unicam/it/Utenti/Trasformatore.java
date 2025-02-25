package cs.unicam.it.Utenti;

import com.fasterxml.jackson.annotation.JsonTypeName;
import cs.unicam.it.Mappa.Geolocalizzazione;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

@Entity
@JsonTypeName("trasformatore")
public class Trasformatore extends Azienda {

    public Trasformatore(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password, sede);
        super.setRuolo(Ruolo.TRASFORMATORE);
    }

    public Trasformatore() {
        super();
    }
}