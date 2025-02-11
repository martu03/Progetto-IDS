package cs.unicam.it.Eventi;

import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Prodotto.Descrizione;
import cs.unicam.it.Utenti.Azienda;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EventiFiliera")
public class EventoFiliera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private static int nextID = 1;
    private String nome;
    @Embedded
    private Descrizione descrizione;
    @ElementCollection
    private List<String> aziendePartecipanti;
    @Enumerated(EnumType.STRING)
    private TipologiaEvento tipologia;
    @Temporal(TemporalType.DATE)
    private Date data;
    @Embedded
    private Geolocalizzazione luogoEvento;

    public EventoFiliera(String nome, Descrizione descrizione, TipologiaEvento tipologia, Date data,
                         Geolocalizzazione luogoEvento, List<String> aziendePartecipanti) {
        this.id = nextID++;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.data = data;
        this.luogoEvento = luogoEvento;
        this.aziendePartecipanti = aziendePartecipanti;
    }

    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public Descrizione getDescrizione() {
        return descrizione;
    }

    public List<String> getPartecipanti() {
        return aziendePartecipanti;
    }

    public TipologiaEvento getTipologia() {
        return tipologia;
    }

    public Date getData() {
        return data;
    }

    public Geolocalizzazione getLuogoEvento() {
        return luogoEvento;
    }

    public void aggiungiAziendaPartecipante(String azienda) {
        aziendePartecipanti.add(azienda);
    }

}