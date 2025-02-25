package cs.unicam.it.Service;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Carrello.ItemCarrello;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Prodotto.Stato;
import cs.unicam.it.Repository.ProdottoRepository;
import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Utenti.Acquirente;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquirenteService {

    @Autowired
    private CarrelloService carrelloService;
    @Autowired
    private UtenteLogRepository utenteLogRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;

    public void aggiungiProdottoAlCarrello(int IdAcquirente, int prodottoId, int quantita) {
        UtenteLog acquirente = utenteLogRepository.findById(IdAcquirente)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        // Verifica se il prodotto esiste e se la quantità richiesta è disponibile
        Prodotto prodotto = prodottoRepository.findById(prodottoId)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        if (prodotto.getQuantita() < quantita) {
            throw new RuntimeException("Quantità richiesta non disponibile per il prodotto: " + prodotto.getNome());
        }

        if (prodotto.getStato() == Stato.IN_ATTESA) {
            throw new RuntimeException("Il prodotto : " + prodotto.getNome() + " non è ancora stato validato!!!");
        }

        if (!(acquirente instanceof Acquirente acquirente1)) {
            throw new RuntimeException("Utente non è un acquirente");
        }
        carrelloService.aggiungiProdottoAlCarrello(acquirente1.getCarrello(), prodottoId, quantita);
    }

    public void rimuoviProdottoDalCarrello(int IdAcquirente, int prodottoId) {
        System.out.println("Inizio metodo rimuoviProdottoDalCarrello. IdAcquirente: " + IdAcquirente + ", ProdottoId: " + prodottoId);

        UtenteLog acquirente = utenteLogRepository.findById(IdAcquirente)
                .orElseThrow(() -> {
                    System.out.println("Acquirente non trovato con Id: " + IdAcquirente);
                    return new RuntimeException("Acquirente non trovato");
                });
        System.out.println("Acquirente trovato: " + acquirente);

        if (!(acquirente instanceof Acquirente acquirente1)) {
            System.out.println("Utente non è un acquirente: " + acquirente);
            throw new RuntimeException("Utente non è un acquirente");
        }
        System.out.println("Utente è un acquirente: " + acquirente1);

        ItemCarrello item = acquirente1.getCarrello().getProdottiCarrello().stream()
                .filter(i -> i.getProdotto().getId() == prodottoId)
                .findFirst()
                .orElseThrow(() -> {
                    System.out.println("Prodotto non trovato nel carrello con Id: " + prodottoId);
                    return new RuntimeException("Prodotto non trovato nel carrello");
                });
        System.out.println("Prodotto trovato nel carrello: " + item.getProdotto());

        carrelloService.rimuoviProdottoDalCarrello(acquirente1.getCarrello(), prodottoId);
        System.out.println("Prodotto rimosso dal carrello: " + prodottoId);

        carrelloService.ripristinaQuantitaDisponibile(item.getProdotto(), item.getQuantita());
        System.out.println("Quantità ripristinata per il prodotto: " + item.getProdotto().getId());
    }

    public void svuotaCarrello(int IdAcquirente) {
        UtenteLog acquirente = utenteLogRepository.findById(IdAcquirente)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        if (!(acquirente instanceof Acquirente acquirente1)) {
            throw new RuntimeException("Utente non è un acquirente");
        }
        for (ItemCarrello item : acquirente1.getCarrello().getProdottiCarrello()) {
            carrelloService.ripristinaQuantitaDisponibile(item.getProdotto(), item.getQuantita());
        }
        carrelloService.svuotaCarrello(acquirente1.getCarrello());
    }

    public void confermaAcquisto(int acquirenteId) {
        UtenteLog acquirente = utenteLogRepository.findById(acquirenteId)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        if (!(acquirente instanceof Acquirente acquirente1)) {
            throw new RuntimeException("Utente non è un acquirente");
        }
        Carrello carrello = acquirente1.getCarrello();
        if (carrello.getProdottiCarrello().isEmpty()) {
            throw new RuntimeException("Il carrello è vuoto. Non è possibile confermare l'acquisto.");
        }

        // Svuota il carrello dopo la conferma
        carrelloService.svuotaCarrello(carrello);

        System.out.println("Acquisto confermato per l'acquirente: " + acquirente.getNome());
    }

    public void eliminaAcquirente(int id) {
        UtenteLog acquirente = utenteLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acquirente non trovato"));

        if (!(acquirente instanceof Acquirente acquirente1)) {
            throw new RuntimeException("Utente non è un acquirente");
        }
        // Svuota e rimuove il carrello associato
        Carrello carrello = acquirente1.getCarrello();
        if (carrello != null) {
            carrelloService.svuotaCarrello(carrello);
            carrello.setAcquirente(null); // Rompe la relazione inversa
        }

        // Elimina l'acquirente
        utenteLogRepository.delete(acquirente);
        System.out.println("Acquirente eliminato con successo: " + acquirente.getNome());
    }
}