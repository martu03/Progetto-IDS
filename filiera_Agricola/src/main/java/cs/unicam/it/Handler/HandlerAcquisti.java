package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Marketplace.GestoreMarketplace;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.Scanner;

//Questa classe si occupa di gestire l'acquisto dei prodotti presenti nel carrello
public class HandlerAcquisti {
    private final Carrello carrello;
    private final GestoreMarketplace handlerMarketplace;
    private final PagoPa paymentSystem;


    public HandlerAcquisti(Carrello carrello, GestoreMarketplace handlerMarketplace, PagoPa paymentSystem) {
        this.carrello = carrello;
        this.handlerMarketplace = handlerMarketplace;
        this.paymentSystem = paymentSystem;
    }

    public void acquistaCarrello() {
        System.out.println("Contenuti del carrello:");
        carrello.mostraProdotti();

        if (!richiediConfermaAcquisto()) {
            System.out.println("Acquisto annullato.");
            return;
        }

        double totale = carrello.calcolaTotale();
        boolean pagamentoRiuscito = paymentSystem.executePayment(totale);

        if (!pagamentoRiuscito) {
            System.out.println("Pagamento non riuscito.");
            return;
        }

        aggiornaMarketplace();
        carrello.svuotaCarrello();

        System.out.println("Acquisto completato con successo!");
    }

    private boolean richiediConfermaAcquisto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Confermi l'acquisto? (s/n)");

        // Legge la risposta dell'utente
        String risposta = scanner.nextLine().trim().toLowerCase();

        if (risposta.equals("s")) {
            return true;
        } else {
            return false;
        }
    }

    private void aggiornaMarketplace() {
        for (Prodotto prodotto : carrello.getProdotti()) {
            handlerMarketplace.aggiungiProdotto(prodotto);
            //TODO: come gestiamo la quantità dei prodotti?
        }
    }
}
