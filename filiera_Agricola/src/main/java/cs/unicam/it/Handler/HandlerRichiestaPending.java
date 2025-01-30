package cs.unicam.it.Handler;


import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Utenti.Curatore;


// Classe che gestisce le richieste pendenti per l'inserimento dei prodotti nella lista del curatore
public class HandlerRichiestaPending {
    private final Curatore curatore;

    public HandlerRichiestaPending(Curatore curatore) {
        this.curatore = curatore;
    }

    // Metodo per aggiungere un prodotto alla lista del curatore (in attesa di approvazione)
    public void inviaRichiestaPerValidazione(Prodotto prodottoInCreazione) {
        curatore.aggiungiProdotto(prodottoInCreazione);
    }
}
