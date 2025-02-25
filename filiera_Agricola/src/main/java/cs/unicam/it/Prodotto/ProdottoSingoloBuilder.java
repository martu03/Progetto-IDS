package cs.unicam.it.Prodotto;

import java.util.Date;

public class ProdottoSingoloBuilder {

    private static ProdottoSingoloBuilder instance;
    private String nome;
    private int quantita;
    private String descrizione;
    private Categoria categoria;
    private double prezzoUnitario;
    private double prezzoTotale;
    private Certificazione certificazione;
    private Date scadenza;
    private Stato stato;

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
        prodotto.setPrezzoUnitario(prezzoUnitario);
        prodotto.setPrezzoTotale(prezzoTotale);
        prodotto.setStato(stato);
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

    public ProdottoSingoloBuilder setDescrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public ProdottoSingoloBuilder setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProdottoSingoloBuilder setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
        return this;
    }

    public ProdottoSingoloBuilder setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
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

    public ProdottoSingoloBuilder setStato(Stato stato) {
        this.stato = stato;
        return this;
    }
}