package cs.unicam.it.Esterni;

import cs.unicam.it.Carrello.Carrello;
import org.springframework.stereotype.Component;

@Component
public class PagoPa {

    private static PagoPa instance;

    private PagoPa() {
    }

    public static PagoPa getInstance() {
        if (instance == null) {
            instance = new PagoPa();
        }
        return instance;
    }

    public boolean effettuaPagamento(Carrello carrello, double totale) {
        System.out.println("Pagamento in corso... Totale: " + totale);
        return true;
    }

}
