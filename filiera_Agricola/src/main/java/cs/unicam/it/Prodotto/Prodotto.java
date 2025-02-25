package cs.unicam.it.Prodotto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity // Indica che questa classe è una tabella nel database
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Strategia di ereditarietà
@DiscriminatorColumn(name = "tipo_prodotto", discriminatorType = DiscriminatorType.STRING)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
// Colonna discriminante per distinguere i tipi di prodotto
public abstract class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private int quantita;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Certificazione certificazione;
    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recensione> recensioni;
    private Date scadenza;
    private double prezzoUnitario;
    private double prezzoTotale;
    private Stato stato;

    public Prodotto() {
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

    public String getDescrizione() {
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

    public double getPrezzoTotale() { return prezzoTotale; }

    public double getPrezzoUnitario(){ return prezzoUnitario; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setDescrizione(String descrizione) {
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

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

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

    public void rimuoviRecensione(Recensione recensione) {
        recensioni.remove(recensione);
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Stato getStato() {
        return stato;
    }
}