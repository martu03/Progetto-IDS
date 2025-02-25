package cs.unicam.it.Utenti;

import cs.unicam.it.Eventi.EventoBuilder;
import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Eventi.TipologiaEvento;
import cs.unicam.it.Mappa.Geolocalizzazione;
import cs.unicam.it.Handler.HandlerEventi;
import cs.unicam.it.Request.EventoRequest;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Animatore extends UtenteLog {

    @ElementCollection
    private List<Integer> idEventiCreati;

    public Animatore(String nome, String email, String password) {
        super(nome, email, password, Ruolo.ANIMATORE);
    }

    public Animatore() {
        super();
    }

//    public EventoFiliera creaEventoBase(EventoRequest eventoRequest) {
//        String nome = eventoRequest.getNome();
//        Date dataEvento = eventoRequest.getData();
//        String descrizione = eventoRequest.getDescrizione();
//        Geolocalizzazione luogoEvento = eventoRequest.getLuogoEvento();
//        TipologiaEvento tipologiaEvento = eventoRequest.getTipologia();
//        List<Integer> partecipanti = eventoRequest.getAziendePartecipanti();
//
//        if (!HandlerEventi.getInstance().verificaDisponibilita(luogoEvento, dataEvento)) {
//            System.out.println("Evento non creato. Esiste già un evento in quella data e luogo.");
//            return null;
//        }
//
//        EventoBuilder builder = EventoBuilder.getInstance();
//        return builder.setNome(nome)
//                .setDescrizione(descrizione)
//                .setTipologiaEvento(tipologiaEvento)
//                .setDataEvento(dataEvento)
//                .setLuogoEvento(luogoEvento)
//                .setAziendePartecipanti(partecipanti)
//                .build();
//    }

    // Metodo per eliminare un evento
    public void eliminaEvento(int IDEvento) {
        HandlerEventi.getInstance().eliminaEvento(IDEvento);
    }

    public void pubblicaEvento(int IDEvento) {
        EventoFiliera evento = HandlerEventi.getInstance().getEventoById(IDEvento);
        System.out.println("Evento " + evento.getNome() + " pubblicato sui social.");
    }

    public void notificaAziendePartecipanti(EventoFiliera evento) {
        List<String> partecipanti = evento.getAziendePartecipanti(); // Ottieni la lista dei partecipanti

        if (partecipanti == null || partecipanti.isEmpty()) {
            System.out.println("Nessuna azienda partecipante all'evento " + evento.getNome() + ".");
            return;
        }

        // Costruisci l'elenco delle aziende partecipanti
        StringBuilder elencoAziende = new StringBuilder();
        for (int i = 0; i < partecipanti.size(); i++) {
            elencoAziende.append(partecipanti.get(i));
            if (i < partecipanti.size() - 1) {
                elencoAziende.append(",\n"); // Aggiungi virgola e a capo tra gli elementi
            }
        }

        // Stampa il messaggio finale
        System.out.println("Le aziende partecipanti:\n" + elencoAziende.toString());
        System.out.println("sono state notificate dell'evento \"" + evento.getNome()
                + "\" che si terrà in data " + evento.getData().toString() + ".");
    }


}
