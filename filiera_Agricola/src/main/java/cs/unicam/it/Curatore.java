package cs.unicam.it;

class Curatore {
    private String nome;
    private String email;

    public Curatore(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void verificaProdotto(ProdottoSingolo prodotto) {
        if (prodotto.hasCertificato()) {
            System.out.println("Prodotto " + prodotto.getNome() + " verificato e approvato.");
        } else {
            System.out.println("Prodotto " + prodotto.getNome() + " non approvato (certificato mancante).");
        }
    }
}

