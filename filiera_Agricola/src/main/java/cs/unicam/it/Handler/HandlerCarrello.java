package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Utenti.Acquirente;

import java.util.HashMap;
import java.util.Map;

//handler che gestisce tutti i carrelli degli acquirenti
public class HandlerCarrello {
    private static final Map<Acquirente, Carrello> carrelli = new HashMap<>();
    private static final HandlerScadenzaCarrello handlerScadenzaCarrello = new HandlerScadenzaCarrello();
    private static final HandlerMarketplace handlerMarketplace = new HandlerMarketplace();
    private static final HandlerCalcolaTotale handlerCalcoloTotale = new HandlerCalcolaTotale();
    private static final HandlerAcquisti handlerAcquisto = new HandlerAcquisti(new PagoPa());

    public void creaCarrello(Acquirente acquirente) {
        carrelli.put(acquirente, acquirente.getCarrello());
    }

    public Carrello getCarrello(Acquirente acquirente) {
        return carrelli.get(acquirente);
    }

    public boolean isScaduto(Acquirente acquirente) {
        if(handlerScadenzaCarrello.isScaduto(carrelli.get(acquirente))) {
            svuotaCarrello(acquirente);
            return true;
        }
        handlerScadenzaCarrello.eliminaTimer(carrelli.get(acquirente));
        return false;
    }

    public void aggiungiProdotto(Acquirente acquirente, Prodotto prodotto, int quantita) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);
        carrelloAcquirente.aggiungiProdotto(prodotto, quantita);

        //aggiornare i prodotti presenti nel carrello nel marketplace
        handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), -quantita);
        handlerScadenzaCarrello.resetScadenza(carrelloAcquirente);
    }

    public void rimuoviProdotto(Acquirente acquirente, Prodotto prodotto) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);
        carrelloAcquirente.rimuoviProdotto(prodotto);

        //aggiornare i prodotti presenti nel carrello nel marketplace
        handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), +prodotto.getQuantita());
        handlerScadenzaCarrello.resetScadenza(carrelloAcquirente);
    }

    //nuova quantità corrisponde alla quantità desiderata dall'utente finale
    public void modificaQuantitaProdotto(Acquirente acquirente, Prodotto prodotto, int nuovaQuantita) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);
        Prodotto prodottoNelCarrello = carrelloAcquirente.getProdottoByID(prodotto.getId());

        if (prodottoNelCarrello == null) {
            System.out.println("Prodotto non presente nel carrello.");
            return;
        }

        int quantitaAttuale = prodottoNelCarrello.getQuantita();

        if (nuovaQuantita <= 0) {
            rimuoviProdotto(acquirente, prodotto);
            return;
        }

        if (nuovaQuantita < quantitaAttuale) {
            carrelloAcquirente.modificaQuantitaProdotto(prodotto, nuovaQuantita);
            handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), quantitaAttuale - nuovaQuantita);
        } else if (handlerMarketplace.isDisponibile(prodotto.getId(), nuovaQuantita - quantitaAttuale)) {
            carrelloAcquirente.modificaQuantitaProdotto(prodotto, nuovaQuantita);
            handlerMarketplace.modificaQuantitaMarketplace(prodotto.getId(), quantitaAttuale - nuovaQuantita);
        } else {
            System.out.println("Quantità richiesta non disponibile nel marketplace.");
        }
        handlerScadenzaCarrello.resetScadenza(carrelloAcquirente);
    }

    public void svuotaCarrello(Acquirente acquirente) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);
        carrelloAcquirente.svuotaCarrello();

        //aggiornare i prodotti presenti nel carrello nel marketplace
        handlerMarketplace.aggiungiProdottiAlMarketplace(carrelloAcquirente.getProdotti());
        handlerScadenzaCarrello.eliminaTimer(carrelloAcquirente);
    }


    public double calcolaTotale(Acquirente acquirente) {
        Carrello carrelloAcquirente = this.getCarrello(acquirente);

        //chiamo il metodo per calcolare il totale
        return handlerCalcoloTotale.calcolaTotale(carrelloAcquirente);
    }

    //metodo per acquistare i prodotti presenti nel carrello
    public void acquista (Acquirente acquirente) {
        Carrello carrello = this.getCarrello(acquirente);
        handlerAcquisto.acquistaCarrello(carrello, calcolaTotale(acquirente));
        carrello.svuotaCarrello();

    }


}
