package cs.unicam.it.Utenti;

import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Handler.HandlerCreazioneEvento;

import java.util.List;

public class Animatore extends UtenteLog {

    private List<EventoFiliera> eventiCreati;

    private static HandlerCreazioneEvento handlerCreazioneEvento = new HandlerCreazioneEvento();

    public Animatore(String nome, String email, String password) {
        super(nome, email, password);
    }

    public void creaEvento() {
        EventoFiliera evento = handlerCreazioneEvento.avviaCreazione();

        if (evento != null) {
            eventiCreati.add(evento);
        }
    }

    public void eliminaEvento(EventoFiliera evento) {
        eventiCreati.remove(evento);
        System.out.println("Evento eliminato con successo");
    }

    public List<EventoFiliera> getEventiCreati() {
        return eventiCreati;
    }

}
