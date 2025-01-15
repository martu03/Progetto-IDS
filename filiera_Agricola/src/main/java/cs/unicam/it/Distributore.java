package cs.unicam.it;

import java.util.List;

class Distributore extends BaseAttore {
    public Distributore(String nome, String email) {
        super(nome, email);
    }

    public void creaPacchetto(String nomePacchetto, List<ComponenteProdotto> prodottiPacchetto) {
        ProdottoPacchetto prodottoPacchetto = new ProdottoPacchetto(nomePacchetto);
        prodottiPacchetto.forEach(prodottoPacchetto::aggiungiComponente);

        System.out.println("Pacchetto " + nomePacchetto + " creato con i seguenti prodotti:");
        prodottiPacchetto.forEach(p -> System.out.println("- " + p.getNome()));
    }
}