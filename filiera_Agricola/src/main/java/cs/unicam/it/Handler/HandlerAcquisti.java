package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Esterni.PagoPa;

import java.util.List;
import java.util.Scanner;

//Questa classe si occupa di gestire l'acquisto dei prodotti presenti nel carrello
public class HandlerAcquisti {

    private static final List<Carrello> carrelli = List.of();
    private static final HandlerMarketplace handlerMarketplace = new HandlerMarketplace();
    private static final PagoPa paymentSystem = new PagoPa();
    private final HandlerCarrello handlerCarrello = new HandlerCarrello();

    public HandlerAcquisti() {
    }

    public void acquistaCarrello(Carrello carrello) {
        System.out.println("Contenuti del carrello:");
        carrello.mostraProdotti();

        if (!richiediConfermaAcquisto()) {
            System.out.println("Acquisto annullato.");
            return;
        }

        boolean pagamentoRiuscito = paymentSystem.executePayment(handlerCarrello.calcolaTotale(carrello));

        if (!pagamentoRiuscito) {
            System.out.println("Pagamento non riuscito.");
            return;
        }

        System.out.println("Acquisto completato con successo!");
        handlerCarrello.svuotaCarrello(carrello);
    }

    private boolean richiediConfermaAcquisto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Confermi l'acquisto? (s/n)");

        // Legge la risposta dell'utente
        String risposta = scanner.nextLine().trim().toLowerCase();

        return risposta.equals("s");
    }
}
