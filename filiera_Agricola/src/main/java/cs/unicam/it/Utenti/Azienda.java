package cs.unicam.it.Utenti;

import cs.unicam.it.Handler.HandlerCreazioneProdottoSingolo;
import cs.unicam.it.Handler.HandlerMarketplace;
import cs.unicam.it.Handler.HandlerScadenzaProdotto;
import cs.unicam.it.Observer.Observer;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

//Classe astratta per la definizione degli attori che possono vendere prodotti
public abstract class Azienda extends UtenteLog implements Observer {

    // Prodotti in vendita dall'azienda
    private List<Prodotto> prodottiInVendita;
    // private Geolocalizzazione geolocalizzazione;
    private static HandlerCreazioneProdottoSingolo handlerCreazioneProdottoSingolo;
    private static HandlerMarketplace handlerMarketplace;
    private static final HandlerScadenzaProdotto handlerScadenzaProdotto = new HandlerScadenzaProdotto();

    public Azienda(String nome, String email, String password) {
        super(nome, email, password);
        this.prodottiInVendita = null;
    }

    // Crea un prodotto singolo
    public void creaProdottoSingolo() {
        System.out.println("Creazione prodotto singolo");
        Prodotto prodottoCreato = handlerCreazioneProdottoSingolo.avviaCreazione();
        prodottiInVendita.add(prodottoCreato);

        handlerScadenzaProdotto.aggiungiOsservatore(prodottoCreato, this);
    }

    // Restituisce la lista di prodotti
    public List<Prodotto> getProdottiInVendita(){
        return prodottiInVendita;
    }

    public void setProdottiInVendita(Prodotto prodotto){
        prodottiInVendita.add(prodotto);
    }

    // Elimina un prodotto dalla lista
    public void eliminaProdotto(Prodotto prodotto){
        prodottiInVendita.remove(prodotto);
        handlerMarketplace.rimuoviProdottoDalMarketplace(prodotto.getId());

        handlerScadenzaProdotto.rimuoviOsservatore(prodotto, this);
    }

    //Modifico la disponibilità di un prodotto già in vendita
    public void modificaQuantita(Prodotto prodotto, int qta){
        int newQuantita = prodotto.getQuantita() + qta;
        if (newQuantita >= 0) {
            prodotto.setQuantita(newQuantita);
        }else{
            prodotto.setQuantita(0);
        }
    }

    @Override
    public void update(Prodotto prodotto) {
        System.out.println("Azienda " + getNome() + " notificata della scadenza del prodotto: " + prodotto.getNome());
        eliminaProdotto(prodotto);
    }

}
