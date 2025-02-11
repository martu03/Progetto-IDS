package cs.unicam.it.Handler;


import cs.unicam.it.Prodotto.Prodotto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class HandlerProdottiCuratore {

    private static HandlerProdottiCuratore instance;
    private List<Prodotto> prodottiDaValidare;

    private HandlerProdottiCuratore() {
        this.prodottiDaValidare = new ArrayList<>();
    }

    public static HandlerProdottiCuratore getInstance() {
        if (instance == null) {
            instance = new HandlerProdottiCuratore();
        }
        return instance;
    }

    public void visualizzaProdotti() {
        System.out.println("Prodotti da verificare:");
        for (Prodotto prodotto : prodottiDaValidare) {
            System.out.println("Nome: " + prodotto.getNome() + ", Certificazione: " + prodotto.getCertificazione());
        }
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        prodottiDaValidare.add(prodotto);
    }

    public List<Prodotto> getProdottiDaValidare() {
        return new ArrayList<>(prodottiDaValidare);
    }

    public void svuotaLista() {
        prodottiDaValidare.clear();
    }

}
