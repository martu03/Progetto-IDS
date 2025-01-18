package cs.unicam.it.Utenti;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Certificazione;
import cs.unicam.it.Prodotto.Descrizione;
import cs.unicam.it.Prodotto.Prodotto;

import java.util.List;

class Distributore extends Azienda {

    public Distributore(String nome, String email, String password) {
        super(nome, email, password);
    }

    public Prodotto CreaProdotto(String name, double quantity, double price,
                                 Descrizione description, Categoria category,
                                 Certificazione certification, List<Prodotto> prodottiPacchetto) {
        return null;
    }

    //public void creaPacchetto(String nomePacchetto, List<Prodotto> prodottiPacchetto) {
        //ProdottoPacchetto prodottoPacchetto = new ProdottoPacchetto(nomePacchetto);
        //prodottiPacchetto.forEach(prodottoPacchetto::aggiungiComponente);

        //System.out.println("Pacchetto " + nomePacchetto + " creato con i seguenti prodotti:");
        //prodottiPacchetto.forEach(p -> System.out.println("- " + p.getNome()));
    //}

}