package cs.unicam.it.Utenti;

import cs.unicam.it.Mappa.Geolocalizzazione;

// Classe per il trasformatore
// Il trasformatore non può creare pacchetti di prodotti
public class Trasformatore extends Azienda {

    public Trasformatore(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password, sede);
    }
}