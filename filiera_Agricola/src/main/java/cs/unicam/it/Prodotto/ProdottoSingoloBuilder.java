package cs.unicam.it.Prodotto;

import cs.unicam.it.Utenti.Azienda;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProdottoSingoloBuilder {

    private static ProdottoSingoloBuilder instance;
    private String nome;
    private int quantita;
    private Descrizione descrizione;
    private Categoria categoria;
    private double prezzo;
    private Certificazione certificazione;
    private Date scadenza;
    private Azienda azienda;

    private ProdottoSingoloBuilder() {}

    public static ProdottoSingoloBuilder getInstance() {
        if (instance == null) {
            instance = new ProdottoSingoloBuilder();
        }
        return instance;
    }

    public ProdottoSingolo build() {
        ProdottoSingolo prodotto = new ProdottoSingolo();

        prodotto.setNome(nome);
        prodotto.setQuantita(quantita);
        prodotto.setDescrizione(descrizione);
        prodotto.setCategoria(categoria);
        prodotto.setCertificazione(certificazione);
        prodotto.setScadenza(scadenza);
        prodotto.setAzienda(azienda);
        prodotto.setPrezzo(prezzo);
        return prodotto;
    }

    public ProdottoSingoloBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProdottoSingoloBuilder setQuantita(int quantita) {
        this.quantita = quantita;
        return this;
    }

    public ProdottoSingoloBuilder setDescrizione(Descrizione descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public ProdottoSingoloBuilder setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProdottoSingoloBuilder setPrezzo(double prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    public ProdottoSingoloBuilder setCertificazione(Certificazione certificazione) {
        this.certificazione = certificazione;
        return this;
    }

    public ProdottoSingoloBuilder setScadenza(Date scadenza) {
        this.scadenza = scadenza;
        return this;
    }

    public ProdottoSingoloBuilder setAzienda(Azienda azienda) {
        this.azienda = azienda;
        return this;
    }
}
