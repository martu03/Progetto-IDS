package cs.unicam.it.Handler;


import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Utenti.Curatore;


// Classe che gestisce le richieste pendenti per l'inserimento dei prodotti nella lista del curatore
public class HandlerRichiestaPending {
    private Curatore curatore;

    public HandlerRichiestaPending(Curatore curatore) {
        this.curatore = curatore;
    }

    public void inviaRichiestaPerApprovazione(Prodotto pacchettoInCreazione) {
    }

    // Metodo per aggiungere un prodotto alla lista del curatore (in attesa di approvazione)
    public void aggiungiProdotto(Prodotto prodotto) {
        if (curatore.getListaProdotti().stream().noneMatch(p -> p.getId() == prodotto.getId())) {
            curatore.getListaProdotti().add(prodotto);
            System.out.println("Prodotto aggiunto: " + prodotto.getName());
        } else {
            System.out.println("Prodotto con ID " + prodotto.getId() + " già presente.");
        }
    }
}
