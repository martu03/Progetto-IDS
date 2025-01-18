package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerCreazioneProdottoSingolo;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

public abstract class Azienda extends UtenteLog {

    //prodotti in vendita dall'azienda
    private List<Prodotto> products;
    private static HandlerCreazioneProdottoSingolo handlerCreazioneProdottoSingolo;

    public Azienda(String nome, String email, String password) {
        super(nome, email, password);
        this.products = null;
    }

    public void CreaProdotto() {
        Prodotto prodottoInCreazione = handlerCreazioneProdottoSingolo.avviaCreazioneProdotto();
        products.add(prodottoInCreazione);
    }

    public List<Prodotto> getProducts(){
        return products;
    }

    public void EliminaProdotto(Prodotto product){
        products.remove(product);
        //ripercussione sul marketplace
    }

    public void ModificaQuantità(Prodotto product, int qtà){
        //verifica se la quantità rispetta la disponibilità del prodotto nel marketplace
        //se va bene, modifica la quantità del prodotto
    }
}
