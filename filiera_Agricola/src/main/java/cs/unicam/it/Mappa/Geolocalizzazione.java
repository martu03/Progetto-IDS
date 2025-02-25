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

    public Geolocalizzazione(double latitudine, double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Geolocalizzazione() {
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
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

}
