package cs.unicam.it.Handler;

import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Mappa.Geolocalizzazione;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HandlerEventi {

    private List<EventoFiliera> eventi = new ArrayList<>();

    public List<EventoFiliera> getEventi() {
        return new ArrayList<>(eventi);
    }

    public void aggiungiEvento(EventoFiliera evento) {
        eventi.add(evento);
        System.out.println("Evento " + evento.getNome() + " creato.");
    }

    public boolean verificaDisponibilita(Geolocalizzazione luogo, Date data) {
        for (EventoFiliera evento : eventi) {
            if (evento.getData().equals(data) && evento.getLuogoEvento().equals(luogo))
                return false;
        }
        return true;
    }

    public void eliminaEvento(int IDEvento) {
        EventoFiliera evento = getEventoById(IDEvento);
        if(evento == null) {
            System.out.println("Evento non trovato.");
            return;
        }
        eventi.remove(evento);
        System.out.println("Evento " + evento.getNome() + " eliminato.");
    }

    public EventoFiliera getEventoById(int id) {
        for (EventoFiliera evento : eventi) {
            if (evento.getId() == id) {
                return evento;
            }
        }
        return null;
    }
}
