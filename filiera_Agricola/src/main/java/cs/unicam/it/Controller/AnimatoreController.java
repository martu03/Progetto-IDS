package cs.unicam.it.Controller;

import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Handler.HandlerEventi;
import cs.unicam.it.Repository.EventoRepository;
import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Request.EventoRequest;
import cs.unicam.it.Utenti.Animatore;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/animatori")
public class AnimatoreController {

    @Autowired
    private UtenteLogRepository utenteLogRepository;
    @Autowired
    private HandlerEventi handlerEventi;
    @Autowired
    private EventoRepository eventoRepository;

    @PostMapping("/crea-evento")
    public ResponseEntity<EventoFiliera> creaEvento(@RequestBody EventoRequest eventoRequest) {
        EventoFiliera evento = new EventoFiliera();

        evento.setNome(eventoRequest.getNome());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setTipologia(eventoRequest.getTipologia());
        evento.setData(eventoRequest.getData());
        evento.setLuogoEvento(eventoRequest.getLuogoEvento());

        // Ottieni i nomi delle aziende partecipanti
        List<String> nomiAziende = eventoRequest.getAziendePartecipanti().stream()
                .map(id -> utenteLogRepository.findById(id).orElseThrow(() -> new RuntimeException("Azienda non trovata")).getNome())
                .collect(Collectors.toList());

        evento.setAziendePartecipanti(nomiAziende);

        handlerEventi.aggiungiEvento(evento);

        return ResponseEntity.status(HttpStatus.CREATED).body(evento);
    }

    @DeleteMapping("/eventi/{id}")
    public ResponseEntity<String> eliminaEvento(@PathVariable int id) {
        handlerEventi.eliminaEvento(id);
        return ResponseEntity.ok("Evento eliminato con successo.");
    }

    @PostMapping("/pubblica-evento/{id}")
    public ResponseEntity<String> pubblicaEvento(@PathVariable int id) {
        Animatore animatore = new Animatore();
        animatore.pubblicaEvento(id);
        return ResponseEntity.ok("Evento pubblicato con successo.");
    }

    @PostMapping("/notifica-aziende/{id}")
    public ResponseEntity<String> notificaAziendePartecipanti(@PathVariable int id) {
        EventoFiliera evento = handlerEventi.getEventoById(id);
        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento non trovato.");
        }
        Animatore animatore = new Animatore();
        animatore.notificaAziendePartecipanti(evento);
        return ResponseEntity.ok("Aziende partecipanti notificate con successo.");
    }
}