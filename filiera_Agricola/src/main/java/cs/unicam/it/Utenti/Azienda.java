package cs.unicam.it.Utenti;

import cs.unicam.it.Gestori.GestorePiattaforma;
import cs.unicam.it.Handler.*;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Prodotto.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public abstract class Azienda extends UtenteLog {

    private Geolocalizzazione sede;
    protected HandlerProdottiInVendita handlerProdottiInVendita;

    public Azienda(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password);
        this.sede = sede;
        this.handlerProdottiInVendita = new HandlerProdottiInVendita();
    }

    public Azienda() {
        super();
        this.handlerProdottiInVendita = new HandlerProdottiInVendita();
    }

    public Geolocalizzazione getSede() {
        return sede;
    }

    public void visualizzaProdottiInVendita() {
        handlerProdottiInVendita.visualizzaProdottiInVendita();
    }

    public void creaProdotto() {
        ProdottoSingoloInputHandler inputHandler = ProdottoSingoloInputHandler.getInstance();
        ProdottoSingolo prodotto = creaProdottoBase(inputHandler); // Crea il prodotto base

        handlerProdottiInVendita.aggiungiProdotto(prodotto);
        HandlerProdottiCuratore.getInstance().aggiungiProdotto(prodotto);
        System.out.println("Prodotto " + prodotto.getNome() + " creato con successo.");
    }

    private ProdottoSingolo creaProdottoBase(ProdottoSingoloInputHandler inputHandler) {
        String nome = inputHandler.chiediNome(); // Chiedi il nome del prodotto
        int quantita = inputHandler.chiediQuantita(); // Chiedi la quantità
        double prezzo = inputHandler.chiediPrezzo(); // Chiedi il prezzo
        Descrizione descrizione = inputHandler.chiediDescrizione(); // Chiedi la descrizione
        Categoria categoria = inputHandler.chiediCategoria(); // Chiedi la categoria
        Certificazione certificazione = inputHandler.chiediCertificazione(); // Chiedi la certificazione
        Date scadenza = inputHandler.chiediScadenza(); // Chiedi la data di scadenza

        return buildProdottoSingolo(nome, quantita, prezzo, descrizione, categoria, certificazione, scadenza);
    }

    private ProdottoSingolo buildProdottoSingolo(String nome, int quantita, double prezzo, Descrizione descrizione, Categoria categoria, Certificazione certificazione, Date scadenza) {
        ProdottoSingoloBuilder builder = ProdottoSingoloBuilder.getInstance();
        return builder.setNome(nome)
                .setQuantita(quantita)
                .setDescrizione(descrizione)
                .setCategoria(categoria)
                .setPrezzo(prezzo)
                .setCertificazione(certificazione)
                .setScadenza(scadenza)
                .setAzienda(this)
                .build();
    }

    public void rimuoviProdotto(int prodottoId) {
        handlerProdottiInVendita.rimuoviProdotto(prodottoId);
        System.out.println("Il prodotto " + prodottoId + " è stato rimosso dal marketplace.");
    }

    public void modificaQuantita(int IDProdotto, int nuovaQuantita) {
        Prodotto prodotto = handlerProdottiInVendita.getProdottoById(IDProdotto);
        if(prodotto == null) {
            System.out.println("Prodotto non trovato nella lista dei prodotti creati.");
            return;
        }

        if(nuovaQuantita < 0 || nuovaQuantita == 0) {
            rimuoviProdotto(IDProdotto);
            System.out.println("Quantita' uguale a 0 -> Prodotto rimosso dal marketplace.");
            return;
        }
        prodotto.setQuantita(nuovaQuantita);
        System.out.println("Quantità del prodotto modificata.");
    }

    public void eliminaAccount() {
        System.out.println("Richiesta di eliminazione account da parte dell'azienda: " + this.getNome());
        GestorePiattaforma.getInstance().rimuoviUtente(this);
    }

    public void pubblicaSuSocial(int IDProdotto) {
        Prodotto prodotto = handlerProdottiInVendita.getProdottoById(IDProdotto);

        // Controllo se il prodotto esiste
        if (prodotto == null) {
            System.out.println("Prodotto non trovato.");
            return;
        }

        // Controllo se il prodotto è già stato pubblicato sui social
        if (handlerProdottiInVendita.isProdottoPubblicato(IDProdotto)) {
            System.out.println("Il prodotto è già stato pubblicato sui social.");
            return;
        }

        // Pubblica il prodotto sui social
        handlerProdottiInVendita.aggiungiProdottoConSocial(prodotto);
        System.out.println("Il prodotto " + prodotto.getNome() + " è stato pubblicato sui social.");
    }

    public void visualizzaProdottiConSocial() {
        handlerProdottiInVendita.visualizzaProdottiConSocial();
    }

}