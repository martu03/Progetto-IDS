package cs.unicam.it.Mappa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Geolocalizzazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double latitudine;
    private double longitudine;
    private String indirizzo;

    public Geolocalizzazione(double latitudine, double longitudine, String indirizzo) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.indirizzo = indirizzo;
    }

    public Geolocalizzazione() {
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
