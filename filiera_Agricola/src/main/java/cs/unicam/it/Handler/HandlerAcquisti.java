package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;

import java.util.List;
import java.util.Scanner;

//Questa classe si occupa di gestire l'acquisto dei prodotti presenti nel carrello
public class HandlerAcquisti {
    private static final List<Carrello> carrelli = List.of();
    private static final HandlerMarketplace handlerMarketplace = new HandlerMarketplace();
    private final PagoPa paymentSystem;


    public HandlerAcquisti(PagoPa paymentSystem) {
        this.paymentSystem = paymentSystem;
    }

    public boolean acquistaCarrello(Carrello carrello, double totale) {
        System.out.println("Contenuti del carrello:");
        carrello.mostraProdotti();

        if (!richiediConfermaAcquisto()) {
            System.out.println("Acquisto annullato.");
            return false;
        }

        boolean pagamentoRiuscito = paymentSystem.executePayment(totale);

        if (!pagamentoRiuscito) {
            System.out.println("Pagamento non riuscito.");
            return false;
        }

        System.out.println("Acquisto completato con successo!");
        return true;
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
}
