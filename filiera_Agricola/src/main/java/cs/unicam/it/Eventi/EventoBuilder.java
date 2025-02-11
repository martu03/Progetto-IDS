package cs.unicam.it.Eventi;

import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Prodotto.Descrizione;

import java.util.Date;
import java.util.List;

public class EventoBuilder {

    private static EventoBuilder instance;
    private String nome;
    private Descrizione descrizione;
    private TipologiaEvento tipologiaEvento;
    private Date dataEvento;
    private Geolocalizzazione luogoEvento;
    private List<String> aziendePartecipanti;

    private EventoBuilder() {
    }

    public static EventoBuilder getInstance() {
        if (instance == null) {
            instance = new EventoBuilder();
        }
        return instance;
    }

    public EventoFiliera build() {
        EventoFiliera evento = new EventoFiliera(nome, descrizione, tipologiaEvento,
                                                dataEvento, luogoEvento, aziendePartecipanti );

        return evento;
    }

    public EventoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public EventoBuilder setDescrizione(Descrizione descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public EventoBuilder setTipologiaEvento(TipologiaEvento tipologiaEvento) {
        this.tipologiaEvento = tipologiaEvento;
        return this;
    }

    public EventoBuilder setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
        return this;
    }

    public EventoBuilder setLuogoEvento(Geolocalizzazione luogoEvento) {
        this.luogoEvento = luogoEvento;
        return this;
    }

    public EventoBuilder setAziendePartecipanti(List<String> aziendePartecipanti) {
        this.aziendePartecipanti = aziendePartecipanti;
        return this;
    }


}