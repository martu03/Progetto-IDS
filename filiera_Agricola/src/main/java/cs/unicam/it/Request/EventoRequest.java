package cs.unicam.it.Request;

import cs.unicam.it.Eventi.TipologiaEvento;
import cs.unicam.it.Mappa.Geolocalizzazione;

import java.util.Date;
import java.util.List;

public class EventoRequest {
    private String nome;
    private String descrizione;
    private TipologiaEvento tipologia;
    private Date data;
    private Geolocalizzazione luogoEvento;
    private List<Integer> aziendePartecipanti;
    private List<String> nomiAziendePartecipanti;

    // Getters e Setters
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

    public List<Integer> getAziendePartecipanti() {
        return aziendePartecipanti;
    }

    public void setAziendePartecipanti(List<Integer> aziendePartecipanti) {
        this.aziendePartecipanti = aziendePartecipanti;
    }
}