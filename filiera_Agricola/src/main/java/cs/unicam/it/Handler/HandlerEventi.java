package cs.unicam.it.Handler;

import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Repository.EventoRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HandlerEventi {

    @Autowired
    private EventoRepository eventoRepository;

    private static HandlerEventi instance;

    private HandlerEventi() {
    }

    public static HandlerEventi getInstance() {
        if (instance == null) {
            instance = new HandlerEventi();
        }
        return instance;
    }

    public void visualizzaEventi() {
        System.out.println("Eventi in programma:");
        for (EventoFiliera evento : eventoRepository.findAll()) {
            System.out.println("Nome: " + evento.getNome() + ", Data: " + evento.getData());
        }
    }

    public void aggiungiEvento(EventoFiliera evento) {
        eventoRepository.save(evento);
    }

    public boolean verificaDisponibilita(Geolocalizzazione luogo, Date data) {
        for (EventoFiliera evento : eventoRepository.findAll()) {
            if (evento.getData().equals(data) && evento.getLuogoEvento().equals(luogo))
                return false;
        }
        return true;
    }

    public void eliminaEvento(int IDEvento) {
        EventoFiliera evento = getEventoById(IDEvento);
        if (evento == null) {
            System.out.println("Evento non trovato.");
            return;
        }
        eventoRepository.delete(evento);
        System.out.println("Evento " + evento.getNome() + " eliminato.");
    }

    public EventoFiliera getEventoById(int id) {
        for (EventoFiliera evento : eventoRepository.findAll()) {
            if (evento.getId() == id) {
                return evento;
            }
        }
        return null;
    }
}
