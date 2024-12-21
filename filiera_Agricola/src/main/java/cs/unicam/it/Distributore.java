package cs.unicam.it;

import java.util.List;

class Distributore extends BaseAttore {
    public Distributore(String nome, String email) {
        super(nome, email);
    }

    public void creaPacchetto(String nomePacchetto, List<ComponenteProdotto> prodottiPacchetto) {
        PacchettoProdotto pacchettoProdotto = new PacchettoProdotto(nomePacchetto);
        prodottiPacchetto.forEach(pacchettoProdotto::aggiungiComponente);

        System.out.println("Pacchetto " + nomePacchetto + " creato con i seguenti prodotti:");
        prodottiPacchetto.forEach(p -> System.out.println("- " + p.getNome()));
    }
}