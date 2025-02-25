package cs.unicam.it.Eventi;

import cs.unicam.it.Handler.HandlerEventi;
import cs.unicam.it.Mappa.Geolocalizzazione;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class EventoFiliera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descrizione;
    @ElementCollection
    private List<String> aziendePartecipanti;
    @Enumerated(EnumType.STRING)
    private TipologiaEvento tipologia;
    private Date data;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "luogo_evento_id")
    private Geolocalizzazione luogoEvento;

    public EventoFiliera(String nome, String descrizione, TipologiaEvento tipologia, Date data,
                         Geolocalizzazione luogoEvento, List<String> aziendePartecipanti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.data = data;
        this.luogoEvento = luogoEvento;
        this.aziendePartecipanti = aziendePartecipanti;
    }

    public EventoFiliera() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<String> getAziendePartecipanti() {
        return aziendePartecipanti;
    }

    public void setAziendePartecipanti(List<String> aziendePartecipanti) {
        this.aziendePartecipanti = aziendePartecipanti;
    }

    public TipologiaEvento getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipologiaEvento tipologia) {
        this.tipologia = tipologia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Geolocalizzazione getLuogoEvento() {
        return luogoEvento;
    }

    public void setLuogoEvento(Geolocalizzazione luogoEvento) {
        this.luogoEvento = luogoEvento;
    }
}