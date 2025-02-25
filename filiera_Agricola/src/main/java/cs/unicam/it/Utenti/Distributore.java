package cs.unicam.it.Utenti;

import com.fasterxml.jackson.annotation.JsonTypeName;
import cs.unicam.it.Handler.HandlerProdottiCuratore;
import cs.unicam.it.Handler.PacchettoInputHandler;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Prodotto.*;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Entity
@JsonTypeName("distributore")
public class Distributore extends Azienda implements ICreaPacchetto {

    public Distributore(String nome, String email, String password, Geolocalizzazione sede) {
        super(nome, email, password, sede);
        super.setRuolo(Ruolo.DISTRIBUTORE);
    }

    public Distributore() {
        super();
    }



    public void creaPacchetto() {
        PacchettoInputHandler inputHandler = PacchettoInputHandler.getInstance();
        ProdottoPacchetto pacchetto = creaPacchettoBase(inputHandler);
        pacchetto.setPrezzo(pacchetto.getPrezzo());
        pacchetto.setScadenza(calcolaScadenzaMinima(pacchetto));
        getIdProdottiInVendita().add(pacchetto.getId());
        HandlerProdottiCuratore.getInstance().aggiungiProdotto(pacchetto);
    }

    private ProdottoPacchetto creaPacchettoBase(PacchettoInputHandler inputHandler){
        String nome = inputHandler.chiediNome(); // Chiedi il nome del prodotto
        int quantita = inputHandler.chiediQuantita(); // Chiedi la quantità
        String descrizione = inputHandler.chiediDescrizione(); // Chiedi la descrizione
        Categoria categoria = inputHandler.chiediCategoria(); // Chiedi la categoria
        Certificazione certificazione = inputHandler.chiediCertificazione(); // Chiedi la certificazione
        List<Prodotto> prodotti = inputHandler.chiediListaProdotti();// Chiedi la lista di prodotti

        // Verifica che ci siano almeno 2 prodotti nel pacchetto
        if (prodotti == null || prodotti.size() < 2) {
            System.out.println("Errore: Un pacchetto deve contenere almeno 2 prodotti.");
            return null;
        }

        PacchettoBuilder builder = PacchettoBuilder.getInstance();
        return builder.setNome(nome)
                .setQuantita(quantita)
                .setDescrizione(descrizione)
                .setCategoria(categoria)
                .setCertificazione(certificazione)
                .setAzienda(this)
                .setProdotti(prodotti)
                .build();
    }

    private Date calcolaScadenzaMinima(ProdottoPacchetto pacchetto){
        Date scadenza = null;
        for (Prodotto prodotto : pacchetto.getChild()) {
            if (scadenza == null || prodotto.getScadenza().before(scadenza)) {
                scadenza = prodotto.getScadenza();
            }
        }
        return scadenza;
    }

}