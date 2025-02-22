package cs.unicam.it.Handler;

import cs.unicam.it.Eventi.TipologiaEvento;
import cs.unicam.it.Mappa.Geolocalizzazione;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventoInputHandler extends InputHandler {

    private static EventoInputHandler instance;

    private EventoInputHandler() {
    }

    public static EventoInputHandler getInstance() {
        if (instance == null) {
            instance = new EventoInputHandler();
        }
        return instance;
    }

    public Date chiediData() {
        System.out.print("Inserisci la data (YYYY-MM-DD): ");
        String dataString = scanner.nextLine().trim();
        SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatoData.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Formato data non valido. Riprova.");
            return chiediData();
        }
    }

    public Geolocalizzazione chiediGeolocalizzazione() {
        System.out.print("Inserisci la latitudine: ");
        double latitudine = scanner.nextDouble();
        System.out.print("Inserisci la longitudine: ");
        double longitudine = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Inserisci l'indirizzo: ");
        String indirizzo = scanner.nextLine().trim();
        return new Geolocalizzazione(latitudine, longitudine);
    }

    public TipologiaEvento chiediTipologia() {
        System.out.println("Inserisci la tipologia:");
        TipologiaEvento[] tipologie = TipologiaEvento.values();
        for (int i = 0; i < tipologie.length; i++) {
            System.out.println((i + 1) + ". " + tipologie[i].name());
        }
        int scelta = scanner.nextInt();
        scanner.nextLine();
        return switch (scelta) {
            case 1 -> TipologiaEvento.valueOf(tipologie[0].name());
            case 2 -> TipologiaEvento.valueOf(tipologie[1].name());
            case 3 -> TipologiaEvento.valueOf(tipologie[2].name());
            case 4 -> TipologiaEvento.valueOf(tipologie[3].name());
            default -> {
                System.out.println("Scelta non valida. Riprova.");
                yield chiediTipologia();
            }
        };
    }

    public List<String> chiediPartecipanti() {
        List<String> partecipanti = new ArrayList<>();
        boolean continua = true;
        while (continua) {
            System.out.print("Inserisci il nome del partecipante (o digita 'exit' per terminare): ");
            String nome = scanner.nextLine().trim();
            if (nome.equalsIgnoreCase("exit")) {
                continua = false;
            } else {
                partecipanti.add(nome);
            }
        }
        return partecipanti;
    }

    @Override
    public void gestisciInput() {
        System.out.println("Gestione input per evento");
    }
}