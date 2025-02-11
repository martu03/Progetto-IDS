package cs.unicam.it.Handler;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProdottoSingoloInputHandler extends ProdottoInputHandler {

    private static ProdottoSingoloInputHandler instance = null;

    private ProdottoSingoloInputHandler() {
        super();
    }

    public static ProdottoSingoloInputHandler getInstance() {
        if (instance == null) {
            instance = new ProdottoSingoloInputHandler();
        }
        return instance;
    }

    public Date chiediScadenza() {
        System.out.print("Inserisci la data di scadenza (gg/mm/aaaa): ");
        String data = scanner.nextLine();
        return new Date(data);
    }

    public double chiediPrezzo() {
        System.out.print("Inserisci il prezzo: ");
        return scanner.nextDouble();
    }

    @Override
    public void gestisciInput() {
        System.out.println("Gestione input per prodotto singolo");
    }
}