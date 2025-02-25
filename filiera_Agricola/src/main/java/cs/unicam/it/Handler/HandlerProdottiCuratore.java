package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.Stato;
import cs.unicam.it.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HandlerProdottiCuratore {

    @Autowired
    private ProdottoRepository prodottoRepository;

    private static HandlerProdottiCuratore instance;

    private HandlerProdottiCuratore() {
    }

    public static HandlerProdottiCuratore getInstance() {
        if (instance == null) {
            instance = new HandlerProdottiCuratore();
        }
        return instance;
    }

    public void visualizzaProdottiDaValidare() {
        System.out.println("Prodotti da verificare:");
        for (Prodotto prodotto : prodottoRepository.findProdottoByStato(Stato.IN_ATTESA)) {
            System.out.println("Nome: " + prodotto.getNome() + ", Certificazione: " + prodotto.getCertificazione());
        }
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        prodottoRepository.save(prodotto);
    }

    public List<Prodotto> getProdottiDaValidare() {
        return new ArrayList<>(prodottoRepository.findProdottoByStato(Stato.IN_ATTESA));
    }

    public void svuotaLista() {
        prodottoRepository.deleteAll(prodottoRepository.findProdottoByStato(Stato.IN_ATTESA));
    }

}
