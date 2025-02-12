package cs.unicam.it.Prodotto;

import cs.unicam.it.Utenti.Azienda;

import java.util.Date;
import java.util.List;

//classe che rappresenta un prodotto generico
public abstract class Prodotto {

    private int id;
    private static int nextID = 1;
    private String nome;
    private int quantita;
    private Descrizione descrizione;
    private Categoria categoria;
    private Certificazione certificazione;
    private List<Recensione> recensioni;
    private Date scadenza;
    private Azienda azienda;
    private double prezzo;

    public Prodotto() {
        this.id = nextID++;
        recensioni = List.of();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public Descrizione getDescrizione() {
        return descrizione;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Certificazione getCertificazione() {
        return certificazione;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setDescrizione(Descrizione descrizione) {
        this.descrizione = descrizione;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCertificazione(Certificazione certificazione) {
        this.certificazione = certificazione;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public void setAzienda(Azienda azienda) { this.azienda = azienda;}

    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }



    public boolean verificaDisponibilita(int quantitaRichiesta) {
        return quantitaRichiesta <= quantita;
    }

    public void modificaQuantita(int qt) {
        quantita += qt;
    }

    public void pubblicaSuSocial() {
        System.out.println("Pubblicazione del prodotto " + nome + " sui social.");
    }

    public void aggiungiRecensione(Recensione recensione) {
        recensioni.add(recensione);
    }

    public abstract double getPrezzo();
}
