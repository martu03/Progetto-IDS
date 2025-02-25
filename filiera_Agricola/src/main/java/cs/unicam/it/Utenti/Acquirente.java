package cs.unicam.it.Utenti;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Handler.HandlerAcquisti;
import cs.unicam.it.Handler.HandlerCarrelli;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.Recensione;
import jakarta.persistence.*;

@Entity
public class Acquirente extends UtenteLog {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CARRELLO_ID", referencedColumnName = "ID", unique = true)
    private Carrello carrello;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Indirizzo")
    private Geolocalizzazione indirizzo;

    public Acquirente(String nome, String email, String password, Geolocalizzazione indirizzo) {
        super(nome, email, password, Ruolo.ACQUIRENTE);
        this.indirizzo = indirizzo;
        this.carrello = new Carrello();
    }

    public Acquirente() {
        super();
        this.carrello = new Carrello();
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public Geolocalizzazione getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Geolocalizzazione indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setCarrello(Carrello carrello) {
        this.carrello = carrello;
    }

//    public void aggiungiProdottoAlCarrello(Prodotto prodotto, int quantita) {
//        carrello.aggiungiProdotto(prodotto, quantita);
//    }
//
//    public void rimuoviProdottoDalCarrello(int IDProdotto) {
//        carrello.rimuoviProdotto(IDProdotto);
//    }
//
//    public void modificaQuantitaProdotto(int IDProdotto, int quantita) {
//        carrello.modificaQuantita(IDProdotto, quantita);
//    }
//
//    public void confermaAcquisto() {
//        if (HandlerAcquisti.getInstance().confermaAcquisto(carrello)) {
//            HandlerCarrelli.getInstance().rimuoviCarrello(carrello);
//        }
//    }
//
//    public void aggiungiRecensioneAProdotto(int IDProdotto, String titolo, String descrizione, int voto) {
//        Recensione recensione = new Recensione(titolo, descrizione, voto, this);
//        Marketplace.getInstance().getProdottoById(IDProdotto).aggiungiRecensione(recensione);
//    }
//
//    public void eliminaAccount() {
//        System.out.println("Richiesta di eliminazione account da parte dell'acquirente: " + this.getNome());
//        GestorePiattaforma.getInstance().rimuoviUtente(this);
//    }

}
