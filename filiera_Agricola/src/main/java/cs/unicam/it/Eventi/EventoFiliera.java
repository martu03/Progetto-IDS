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
    @OneToOne
    @JoinColumn(name = "luogo_evento_id")
    private Geolocalizzazione luogoEvento;
    @ManyToOne
    @JoinColumn(name = "handler_eventi_id")
    private HandlerEventi handlerEventi;

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

    public int getId() { return id; }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
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

    public void setHandlerEventi(HandlerEventi handlerEventi) {
        this.handlerEventi = handlerEventi;
    }

}