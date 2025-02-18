package cs.unicam.it.Handler;

import cs.unicam.it.Mappa.Mappa;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Controller
public class HandlerProdottiInVendita {

    private List<Prodotto> prodottiInVendita = new ArrayList<>();
    private List<Prodotto> prodottiConSocial = new ArrayList<>();

    public void visualizzaProdottiInVendita() {
        System.out.println("Prodotti in vendita:");
        for (Prodotto prodotto : prodottiInVendita) {
            System.out.println("Nome: " + prodotto.getNome() + ", Prezzo: " + prodotto.getPrezzo());
        }
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        prodottiInVendita.add(prodotto);
        Mappa.getInstance().aggiungiPunto(prodotto.getAzienda().getSede());
    }

    public void rimuoviProdotto(int IDProdotto) {
        Prodotto prodotto = getProdottoById(IDProdotto);
        prodottiInVendita.remove(prodotto);
        Marketplace.getInstance().rimuoviProdotto(IDProdotto);
        Mappa.getInstance().rimuoviPunto(prodotto.getAzienda().getSede());
    }

    public List<Prodotto> getProdottiInVendita() {
        return new ArrayList<>(prodottiInVendita);
    }

    public Prodotto getProdottoById(int IDProdotto) {
        for (Prodotto prodotto : prodottiInVendita) {
            if (prodotto.getId() == IDProdotto) {
                return prodotto;
            }
        }
        return null;
    }

    // Visualizza i prodottiInVendita con contenuto sui social
    public void visualizzaProdottiConSocial() {
        System.out.println("Prodotti con contenuto sui social:");
        for (Prodotto prodotto : prodottiConSocial) {
            System.out.println("Nome: " + prodotto.getNome() + ", Prezzo: " + prodotto.getPrezzo());
        }
    }

    // Aggiunge un prodotto alla lista dei prodottiInVendita con contenuto sui social
    public void aggiungiProdottoConSocial(Prodotto prodotto) {
        if (!prodottiConSocial.contains(prodotto)) {
            prodottiConSocial.add(prodotto);
        }
    }

    // Verifica se un prodotto è già stato pubblicato sui social
    public boolean isProdottoPubblicato(int IDProdotto) {
        return prodottiConSocial.stream().anyMatch(p -> p.getId() == IDProdotto);
    }

}
