package cs.unicam.it.Prodotto;

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
    private double price;
    private Certificazione certificazione;
    private List<Recensione> recensioni;
    private Date scadenza;
    private boolean validato;

    public Prodotto() {
        this.id = nextID++;
        recensioni = List.of();
        validato = false;
    }

    public Prodotto(Prodotto prodottoClonazione) {
        this.id = prodottoClonazione.getId();
        this.nome = prodottoClonazione.getNome();
        this.quantita = prodottoClonazione.getQuantita();
        this.descrizione = prodottoClonazione.getDescrizione();
        this.categoria = prodottoClonazione.getCategoria();
        this.certificazione = prodottoClonazione.getCertificazione();
        this.recensioni = prodottoClonazione.getRecensioni();
        this.scadenza = prodottoClonazione.getScadenza();
        this.validato = prodottoClonazione.isValidato();
    }

    public int getId() {return id;}

    public String getNome() {
        return nome;
    }

    public int getQuantita() {
        return quantita;
    }

    public abstract double getPrezzo();

    public Descrizione getDescrizione() {
        return descrizione;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Certificazione getCertificazione() {
        return certificazione;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public boolean isValidato() { return validato; }



    public void setId(int id) {this.id = id;}

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

    public void setPrice(double price) {this.price = price;}

    public void setCertificazione(Certificazione certificazione) {
        this.certificazione = certificazione;
    }

    public void setRecensioni(List<Recensione> reviews) {
        this.recensioni = reviews;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public void setValidato(boolean validato) {this.validato = validato; }

    public abstract Prodotto clone(Prodotto prodottoClonazione);

    @Override
    public String toString() {
        return "Prodotto {" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", quantity=" + quantita +
                ", description=" + (descrizione != null ? descrizione.getDettaglio() : "null") +
                ", category=" + (categoria != null ? categoria.name() : "null") +
                ", price=" + price +
                ", certification=" + (certificazione != null ? certificazione.toString() : "null") +
                ", reviews=" + (recensioni != null ? recensioni.toString() : "null") +
                ", scadenza=" + (scadenza != null ? scadenza.toString() : "null") +
                " }";
    }
}
