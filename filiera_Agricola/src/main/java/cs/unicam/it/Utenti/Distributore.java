package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerCreazionePacchetto;
import cs.unicam.it.Handler.PacchettoInputHandler;
import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Certificazione;
import cs.unicam.it.Prodotto.Descrizione;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

// Classe per il distributore
//il distributore può creare pacchetti di prodotti
public class Distributore extends Azienda implements ICreaPacchetto {
    private static HandlerCreazionePacchetto handlerCreazionePacchetto = new HandlerCreazionePacchetto();

    public Distributore(String nome, String email, String password) {
        super(nome, email, password);
    }

    public void creaPacchetto() {
        PacchettoInputHandler pacchettoInputHandler = new PacchettoInputHandler();
        System.out.println("Creazione pacchetto da parte del Distributore...");
        Prodotto pacchetto = handlerCreazionePacchetto.avviaCreazione();
    }

    // Altri metodi relativi al distributore (se necessari)
}