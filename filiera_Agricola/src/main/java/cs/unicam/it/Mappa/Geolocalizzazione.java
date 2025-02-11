package cs.unicam.it.Mappa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Geolocalizzazione {

    private double latitudine;
    private double longitudine;
    @Id
    private String indirizzo;

    public Geolocalizzazione(double latitudine, double longitudine, String indirizzo) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.indirizzo = indirizzo;
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
