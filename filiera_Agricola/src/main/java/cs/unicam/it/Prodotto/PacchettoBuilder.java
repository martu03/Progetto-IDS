package cs.unicam.it.Prodotto;

import cs.unicam.it.Utenti.Azienda;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class PacchettoBuilder {

    private static PacchettoBuilder instance;
    private String nome;
    private int quantita;
    private String descrizione;
    private Categoria categoria;
    private Certificazione certificazione;
    private Azienda azienda;
    private List<Prodotto> prodotti = new ArrayList<>();

    private PacchettoBuilder() {}

    public static PacchettoBuilder getInstance() {
        if (instance == null) {
            instance = new PacchettoBuilder();
        }
        return instance;
    }

    public ProdottoPacchetto build() {
        ProdottoPacchetto pacchetto = new ProdottoPacchetto();

        pacchetto.setNome(nome);
        pacchetto.setQuantita(quantita);
        pacchetto.setDescrizione(descrizione);
        pacchetto.setCategoria(categoria);
        pacchetto.setCertificazione(certificazione);
        pacchetto.setProdotti(prodotti);
        return pacchetto;
    }

    public PacchettoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public PacchettoBuilder setQuantita(int quantita) {
        this.quantita = quantita;
        return this;
    }

    public PacchettoBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public PacchettoBuilder setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public PacchettoBuilder setCertificazione(Certificazione certificazione) {
        this.certificazione = certificazione;
        return this;
    }

    public PacchettoBuilder setAzienda(Azienda azienda) {
        this.azienda = azienda;
        return this;
    }

    public PacchettoBuilder setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
        return this;
    }
}
