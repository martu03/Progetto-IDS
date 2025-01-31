package cs.unicam.it.Gestori;

import cs.unicam.it.Utenti.Trasformatore;
import cs.unicam.it.Utenti.UtenteLog;

class GestoreTrasformatore extends GestoreRuolo {

    @Override
    public UtenteLog creaUtente(String nome, String email, String password) {
        return new Trasformatore(nome, email, password);
    }
}