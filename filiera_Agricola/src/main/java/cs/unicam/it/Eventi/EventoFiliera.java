package cs.unicam.it.Eventi;

import cs.unicam.it.Geolocalizzazione.Geolocalizzazione;

import java.util.Date;
import java.util.List;

public class EventoFiliera {

    private String nome;
    private String descrizione;
    private List<String> partecipanti;
    private TipologiaEvento tipologia;
    private Date data;
    private Geolocalizzazione geolocalizzazione;

    public EventoFiliera() {
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

    public List<String> getPartecipanti() {
        return partecipanti;
    }

    public void setPartecipanti(List<String> partecipanti) {
        this.partecipanti = partecipanti;
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

    public Geolocalizzazione getGeolocalizzazione() {
        return geolocalizzazione;
    }

    public void setGeolocalizzazione(Geolocalizzazione geolocalizzazione) {
        this.geolocalizzazione = geolocalizzazione;
    }
}