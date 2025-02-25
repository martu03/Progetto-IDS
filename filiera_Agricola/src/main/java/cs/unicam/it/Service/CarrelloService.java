package cs.unicam.it.Service;

import cs.unicam.it.Carrello.Carrello;
import cs.unicam.it.Carrello.ItemCarrello;
import cs.unicam.it.Prodotto.Prodotto;
import cs.unicam.it.Repository.ItemCarrelloRepository;
import cs.unicam.it.Repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class CarrelloService {

    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private ItemCarrelloRepository itemCarrelloRepository;

    public void aggiungiProdottoAlCarrello(Carrello carrello, int prodottoId, int quantita) {
        Prodotto prodotto = prodottoRepository.findById(prodottoId).orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

        ItemCarrello item = carrello.getProdottiCarrello().stream()
                .filter(i -> i.getProdotto().equals(prodotto))
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantita(item.getQuantita() + quantita);
        } else {
            ItemCarrello newItem = new ItemCarrello(prodotto, quantita);
            carrello.getProdottiCarrello().add(newItem);
            itemCarrelloRepository.save(newItem); // Salva l'oggetto ItemCarrello nella repository
        }
        prodotto.modificaQuantita(-quantita);
        prodottoRepository.save(prodotto); // Salva il prodotto aggiornato
    }

    public void rimuoviProdottoDalCarrello(Carrello carrello, int prodottoId) {
        System.out.println("Inizio metodo rimuoviProdottoDalCarrello. ProdottoId: " + prodottoId);
        ItemCarrello itemDaRimuovere = null;

        // Trova l'item da rimuovere
        for (ItemCarrello item : carrello.getProdottiCarrello()) {
            if (item.getProdotto().getId() == prodottoId) {
                itemDaRimuovere = item;
                break;
            }
        }

        if (itemDaRimuovere != null) {
            System.out.println("Prodotto trovato nel carrello: " + itemDaRimuovere.getProdotto().getId());
            // Rimuovi l'item dal carrello
            carrello.getProdottiCarrello().remove(itemDaRimuovere);
            itemCarrelloRepository.delete(itemDaRimuovere);
            System.out.println("Prodotto rimosso dal carrello: " + itemDaRimuovere.getProdotto().getId());
        } else {
            System.out.println("Prodotto non trovato nel carrello con Id: " + prodottoId);
            throw new RuntimeException("Prodotto non trovato nel carrello.");
        }
    }

    public void svuotaCarrello(Carrello carrello) {
        Iterator<ItemCarrello> iterator = carrello.getProdottiCarrello().iterator();
        while (iterator.hasNext()) {
            ItemCarrello item = iterator.next();
            Prodotto prodotto = item.getProdotto();
            iterator.remove();
            itemCarrelloRepository.delete(item);
        }
        carrello.getProdottiCarrello().clear();
    }

    public void ripristinaQuantitaDisponibile(Prodotto prodotto, int quantitaRimossa) {
        prodotto.setQuantita(prodotto.getQuantita() + quantitaRimossa);
        prodottoRepository.save(prodotto);
    }
}
