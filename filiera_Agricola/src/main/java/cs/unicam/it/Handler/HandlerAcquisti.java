package cs.unicam.it.Handler;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Carrello.ItemCarrello;
import cs.unicam.it.Esterni.PagoPa;
import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

public class HandlerAcquisti {

    private static HandlerAcquisti instance;

    private HandlerAcquisti() {
    }

    public static HandlerAcquisti getInstance() {
        if (instance == null) {
            instance = new HandlerAcquisti();
        }
        return instance;
    }

    public boolean confermaAcquisto(Carrello carrello) {
        // Calcola il totale dell'ordine
        double totale = HandlerCalcolaTotale.getInstance().calcolaTotale(carrello);

        if (!richiediConfermaAcquisto()) {
            System.out.println("Acquisto annullato.");
            return false;
        }

        // Effettua il pagamento tramite Pagopa
        if (PagoPa.getInstance().effettuaPagamento(carrello, totale)) {
            // Svuota il carrello dopo la conferma
            carrello.svuota();
            System.out.println("Acquisto completato con successo.");
            return true;
        } else {
            System.out.println("Errore durante il pagamento.");
            return false;
        }
    }

    private boolean richiediConfermaAcquisto() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Confermi l'acquisto? (s/n)");

        // Legge la risposta dell'utente
        String risposta = scanner.nextLine().trim().toLowerCase();

        return risposta.equals("s");
    }

}
