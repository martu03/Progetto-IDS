package cs.unicam.it.Request;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Certificazione;

import java.util.Date;

public class ProdottoSingoloRequest {
    private String nome;
    private int quantita;
    private String descrizione;
    private Categoria categoria;
    private Certificazione certificazione;
    private Date scadenza;
    private double prezzo;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Certificazione getCertificazione() {
        return certificazione;
    }

    public void setCertificazione(Certificazione certificazione) {
        this.certificazione = certificazione;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}