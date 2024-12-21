package cs.unicam.it;

class PacchettoBuilder {
    private PacchettoProdotto pacchetto;

    public PacchettoBuilder(String nomePacchetto) {
        this.pacchetto = new PacchettoProdotto(nomePacchetto);
    }

    public PacchettoBuilder aggiungiProdotto(ComponenteProdotto prodotto) {
        pacchetto.aggiungiComponente(prodotto);
        return this;
    }

    public PacchettoProdotto build() {
        return pacchetto;
    }
}
