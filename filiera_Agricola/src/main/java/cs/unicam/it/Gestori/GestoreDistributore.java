package cs.unicam.it.Gestori;

import cs.unicam.it.Utenti.Distributore;
import cs.unicam.it.Utenti.UtenteLog;

class GestoreDistributore extends GestoreRuolo {

    @Override
    public UtenteLog creaUtente(String nome, String email, String password) {
        return new Distributore(nome, email, password);
    }
}