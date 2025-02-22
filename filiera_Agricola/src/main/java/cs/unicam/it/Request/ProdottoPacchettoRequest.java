package cs.unicam.it.Request;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Certificazione;

import java.util.List;

public class ProdottoPacchettoRequest {
    private String nome;
    private int quantita;
    private String descrizione;
    private Categoria categoria;
    private Certificazione certificazione;
    private List<ProdottoSingoloRequest> prodottiSingoli;

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

    public List<ProdottoSingoloRequest> getProdottiSingoli() {
        return prodottiSingoli;
    }

    public void setProdottiSingoli(List<ProdottoSingoloRequest> prodottiSingoli) {
        this.prodottiSingoli = prodottiSingoli;
    }

    // Metodo per calcolare il prezzo unitario del pacchetto
    public double getPrezzoUnitario() {
        double prezzoUnitario = 0;
        for (ProdottoSingoloRequest prodotto : prodottiSingoli) {
            prezzoUnitario += prodotto.getPrezzo();
        }
        return prezzoUnitario;
    }

    // Metodo per calcolare il prezzo totale di tutti i pacchetti caricati
    public double getPrezzoTotale() {
        return getPrezzoUnitario() * quantita;
    }
}