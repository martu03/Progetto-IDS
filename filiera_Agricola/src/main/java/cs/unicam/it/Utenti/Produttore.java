package cs.unicam.it.Utenti;

import cs.unicam.it.Mappa.Geolocalizzazione;

public class Produttore extends Azienda {

    public Produttore(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password, sede);
    }
}