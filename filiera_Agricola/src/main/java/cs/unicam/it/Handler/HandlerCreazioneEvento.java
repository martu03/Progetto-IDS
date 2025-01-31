package cs.unicam.it.Handler;

import cs.unicam.it.Eventi.EventoFiliera;
import cs.unicam.it.Geolocalizzazione.Geolocalizzazione;
import cs.unicam.it.Utenti.Animatore;

import java.util.Date;
import java.util.List;

public class HandlerCreazioneEvento {

    private Animatore animatore;
    private EventoFiliera eventoInCreazione;
    private EventoInputHandler inputHandler;
    public HandlerCreazioneEvento() {
        inputHandler = new EventoInputHandler();
    }

    public EventoFiliera avviaCreazione() {
        form();
        mostraResoconto();
        if (verificaDisponibilita() && confermaCreazione()) {
            return eventoInCreazione;
        }
        return null;
    }

    public void form() {
        System.out.println("=== Creazione Evento ===");

        // Creazione di un evento vuoto
        eventoInCreazione = new EventoFiliera();

        // Utilizzo dei metodi di input per raccogliere le informazioni
        String nomeEvento = inputHandler.chiediNome();
        eventoInCreazione.setNome(nomeEvento);

        Geolocalizzazione luogoEvento = inputHandler.chiediGeolocalizzazione();
        eventoInCreazione.setGeolocalizzazione(luogoEvento);

        Date dataEvento = inputHandler.chiediData();
        eventoInCreazione.setData(dataEvento);

        String descrizioneEvento = inputHandler.chiediDescrizione();
        eventoInCreazione.setDescrizione(descrizioneEvento);

        List<String> partecipantiEvento = inputHandler.chiediPartecipanti();
        eventoInCreazione.setPartecipanti(partecipantiEvento);
    }

    public void mostraResoconto() {
        System.out.println("Resoconto creazione evento: ");
        System.out.println(eventoInCreazione.toString());
    }

    public boolean confermaCreazione() {
        System.out.println("Vuoi confermare la creazione dell'evento? (S/N)");
        String conferma = inputHandler.scanner.nextLine().trim().toUpperCase();
        if (conferma.equals("S")) {
            return true;
        } else {
            System.out.println("Creazione dell'evento annullata.");
            return false;
        }
    }

    private boolean verificaDisponibilita() {
        for (EventoFiliera evento : animatore.getEventiCreati()) {
            if (evento.getGeolocalizzazione().equals(eventoInCreazione.getGeolocalizzazione()) &&
                    evento.getData().equals(eventoInCreazione.getData())) {
                System.out.println("Errore: Esiste già un evento in questo luogo e in questa data.");
                return false;
            }
        }
        return true;
    }

}