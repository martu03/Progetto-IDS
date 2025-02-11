package cs.unicam.it.Utenti;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Gestori.GestorePiattaforma;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Handler.HandlerAcquisti;
import cs.unicam.it.Handler.HandlerCarrelli;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.Recensione;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Acquirente extends UtenteLog {

    @OneToOne
    private Carrello carrello;
    @Embedded
    private Geolocalizzazione indirizzoSpedizione;

    public Acquirente(String nome, String email, String password, Geolocalizzazione indirizzoSpedizione) {
        super(nome, email, password);
        this.indirizzoSpedizione = indirizzoSpedizione;
        this.carrello = new Carrello();
    }

    public Carrello getCarrello() {
        return carrello;
    }

    public Geolocalizzazione getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void aggiungiProdottoAlCarrello(int IDProdotto, int quantita) {
        if (carrello.getProdottiCarrello().isEmpty()) {
            HandlerCarrelli.getInstance().aggiungiCarrello(carrello);
        }
        carrello.aggiungiProdotto(IDProdotto, quantita);
    }

    public void rimuoviProdottoDalCarrello(int IDProdotto) {
        carrello.rimuoviProdotto(IDProdotto);
    }

    public void modificaQuantitaProdotto(int IDProdotto, int quantita) {
        carrello.modificaQuantita(IDProdotto, quantita);
    }

    public void confermaAcquisto() {
        if (HandlerAcquisti.getInstance().confermaAcquisto(carrello)) {
            HandlerCarrelli.getInstance().rimuoviCarrello(carrello);
        }
    }

    public void aggiungiRecensioneAProdotto(int IDProdotto, String titolo, String descrizione, int voto) {
        Recensione recensione = new Recensione(titolo, descrizione, voto, this);
        Marketplace.getInstance().getProdottoById(IDProdotto).aggiungiRecensione(recensione);
    }

    public void eliminaAccount() {
        System.out.println("Richiesta di eliminazione account da parte dell'acquirente: " + this.getNome());
        GestorePiattaforma.getInstance().rimuoviUtente(this);
    }

}
