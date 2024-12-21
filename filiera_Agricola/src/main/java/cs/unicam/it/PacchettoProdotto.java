package cs.unicam.it;

import java.util.ArrayList;
import java.util.List;

class PacchettoProdotto implements ComponenteProdotto {
    private String nome;
    private List<ComponenteProdotto> componenti;

    public PacchettoProdotto(String nome) {
        this.nome = nome;
        this.componenti = new ArrayList<>();
    }

    public void aggiungiComponente(ComponenteProdotto componente) {
        componenti.add(componente);
    }

    public void rimuoviComponente(ComponenteProdotto componente) {
        componenti.remove(componente);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("Pacchetto: " + nome);
        for (ComponenteProdotto componente : componenti) {
            componente.mostraDettagli();
        }
    }

    public List<ComponenteProdotto> getProdotti() {
        return componenti;
    }

    @Override
    public double getPrezzo() {
        return componenti.stream().mapToDouble(ComponenteProdotto::getPrezzo).sum();
    }

    @Override
    public String toString() {
        return "PacchettoProdotto{" +
                "nome='" + nome + '\'' +
                ", prodotti=" + componenti +
                '}';
    }
}
