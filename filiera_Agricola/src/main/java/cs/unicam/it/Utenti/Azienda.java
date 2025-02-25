package cs.unicam.it.Utenti;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import cs.unicam.it.Handler.*;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Marketplace.Marketplace;
import cs.unicam.it.Prodotto.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "azienda_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Produttore.class, name = "produttore"),
        @JsonSubTypes.Type(value = Trasformatore.class, name = "trasformatore"),
        @JsonSubTypes.Type(value = Distributore.class, name = "distributore")
})
public abstract class Azienda extends UtenteLog {

    @OneToOne(cascade = CascadeType.ALL)
    private Geolocalizzazione sede;
    @ElementCollection
    private List<Integer> idProdottiCreati;
    @ElementCollection
    private List<Integer> idProdottiPubblicati;

    public Azienda(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password, Ruolo.AZIENDA);
        this.sede = sede;
        idProdottiCreati = List.of();
        idProdottiPubblicati = List.of();
    }

    public Azienda() {
        super();
    }

    public Geolocalizzazione getSede() {
        return sede;
    }

    public void creaProdotto() {
        ProdottoSingoloInputHandler inputHandler = ProdottoSingoloInputHandler.getInstance();
        ProdottoSingolo prodotto = creaProdottoBase(inputHandler); // Crea il prodotto base

        idProdottiCreati.add(prodotto.getId());
        HandlerProdottiCuratore.getInstance().aggiungiProdotto(prodotto);
        System.out.println("Prodotto " + prodotto.getNome() + " creato con successo.");
    }

    private ProdottoSingolo creaProdottoBase(ProdottoSingoloInputHandler inputHandler) {
        String nome = inputHandler.chiediNome(); // Chiedi il nome del prodotto
        int quantita = inputHandler.chiediQuantita(); // Chiedi la quantità
        double prezzo = inputHandler.chiediPrezzo(); // Chiedi il prezzo
        String descrizione = inputHandler.chiediDescrizione(); // Chiedi la descrizione
        Categoria categoria = inputHandler.chiediCategoria(); // Chiedi la categoria
        Certificazione certificazione = inputHandler.chiediCertificazione(); // Chiedi la certificazione
        Date scadenza = inputHandler.chiediScadenza(); // Chiedi la data di scadenza

        return buildProdottoSingolo(nome, quantita, prezzo, descrizione, categoria, certificazione, scadenza);
    }

    private ProdottoSingolo buildProdottoSingolo(String nome, int quantita, double prezzo, String descrizione, Categoria categoria, Certificazione certificazione, Date scadenza) {
        ProdottoSingoloBuilder builder = ProdottoSingoloBuilder.getInstance();
        return builder.setNome(nome)
                .setQuantita(quantita)
                .setDescrizione(descrizione)
                .setCategoria(categoria)
                .setCertificazione(certificazione)
                .setScadenza(scadenza)
                .build();
    }

    public void rimuoviProdotto(int prodottoId) {
        if (!idProdottiCreati.contains(prodottoId)) {
            System.out.println("Prodotto non trovato nella lista dei prodotti creati.");
            return;
        }
        idProdottiCreati.remove(prodottoId);
        Marketplace.getInstance().rimuoviProdotto(prodottoId);
        System.out.println("Il prodotto " + prodottoId + " è stato rimosso dal marketplace.");
    }

    public void modificaQuantita(int IDProdotto, int nuovaQuantita) {
        if (!idProdottiCreati.contains(IDProdotto)) {
            System.out.println("Prodotto non trovato nella lista dei prodotti creati.");
            return;
        }
        Prodotto prodotto = Marketplace.getInstance().getProdottoById(IDProdotto);
        if (prodotto == null) {
            System.out.println("Prodotto non trovato nella lista dei prodotti creati.");
            return;
        }

        if (nuovaQuantita < 0 || nuovaQuantita == 0) {
            rimuoviProdotto(IDProdotto);
            System.out.println("Quantita' uguale a 0 -> Prodotto rimosso dal marketplace.");
            return;
        }
        prodotto.setQuantita(nuovaQuantita);
        System.out.println("Quantità del prodotto modificata.");
    }

//    public void eliminaAccount() {
//        System.out.println("Richiesta di eliminazione account da parte dell'azienda: " + this.getNome());
//        GestorePiattaforma.getInstance().rimuoviUtente(this);
//    }

    public void pubblicaSuSocial(int IDProdotto) {
        if (!idProdottiCreati.contains(IDProdotto)) {
            System.out.println("Prodotto non trovato nella lista dei prodotti creati.");
            return;
        }
        Prodotto prodotto = Marketplace.getInstance().getProdottoById(IDProdotto);

        // Controllo se il prodotto esiste
        if (prodotto == null) {
            System.out.println("Prodotto non trovato.");
            return;
        }

        // Controllo se il prodotto è già stato pubblicato sui social
        if (idProdottiPubblicati.contains(IDProdotto)) {
            System.out.println("Il prodotto è già stato pubblicato sui social.");
            return;
        }

        // Pubblica il prodotto sui social
        idProdottiPubblicati.add(prodotto.getId());
        System.out.println("Il prodotto " + prodotto.getNome() + " è stato pubblicato sui social.");
    }

    public List<Integer> getIdProdottiCreati() {
        return idProdottiCreati;
    }

    public void setIdProdottiCreati(List<Integer> idProdottiInVendita) {
        this.idProdottiCreati = idProdottiInVendita;
    }

    public List<Integer> getIdProdottiPubblicati() {
        return idProdottiPubblicati;
    }
}