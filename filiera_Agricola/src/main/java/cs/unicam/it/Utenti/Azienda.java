package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerCreazioneProdottoSingolo;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

//Classe astratta per la definizione degli attori che possono vendere prodotti
public abstract class Azienda extends UtenteLog {

    // Prodotti in vendita dall'azienda
    private List<Prodotto> products;
    private static HandlerCreazioneProdottoSingolo handlerCreazioneProdottoSingolo;

    public Azienda(String nome, String email, String password) {
        super(nome, email, password);
        this.products = null;
    }

    // Crea un prodotto singolo
    public void CreaProdotto() {
        Prodotto prodottoInCreazione = handlerCreazioneProdottoSingolo.avviaCreazione();
        products.add(prodottoInCreazione);
    }

    // Restituisce la lista di prodotti
    public List<Prodotto> getProducts(){
        return products;
    }

    // Elimina un prodotto dalla lista
    public void EliminaProdotto(Prodotto product){
        // Ripercussioni sul marketplace
    }

    // Modifica la quantità di un prodotto
    public void ModificaQuantità(Prodotto product, int qtà){
        // Verifica se la quantità rispetta la disponibilità del prodotto nel marketplace
        // Se va bene, modifica la quantità del prodotto
    }

}
