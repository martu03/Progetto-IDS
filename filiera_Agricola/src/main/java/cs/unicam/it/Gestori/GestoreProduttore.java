package cs.unicam.it.Gestori;

import cs.unicam.it.Utenti.Produttore;
import cs.unicam.it.Utenti.UtenteLog;

class GestoreProduttore extends GestoreRuolo {

    @Override
    public UtenteLog creaUtente(String nome, String email, String password) {
        return new Produttore(nome, email, password);
    }
}