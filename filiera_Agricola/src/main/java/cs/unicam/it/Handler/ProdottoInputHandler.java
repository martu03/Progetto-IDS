package cs.unicam.it.Handler;

import cs.unicam.it.Prodotto.Categoria;
import cs.unicam.it.Prodotto.Certificazione;
import org.springframework.stereotype.Component;

public abstract class ProdottoInputHandler extends InputHandler {

    public Categoria chiediCategoria() {
        System.out.println("Inserisci la categoria:");
        Categoria[] categorie = Categoria.values();
        for (int i = 0; i < categorie.length; i++) {
            System.out.println((i + 1) + ". " + categorie[i].name());
        }
        int scelta = scanner.nextInt();
        scanner.nextLine();
        if (scelta > 0 && scelta <= categorie.length) {
            switch (scelta) {
                case 1:
                    return Categoria.valueOf(categorie[0].name());
                case 2:
                    return Categoria.valueOf(categorie[1].name());
                case 3:
                    return Categoria.valueOf(categorie[2].name());
                case 4:
                    return Categoria.valueOf(categorie[3].name());
                case 5:
                    return Categoria.valueOf(categorie[4].name());
                case 6:
                    return Categoria.valueOf(categorie[5].name());
                case 7:
                    return Categoria.valueOf(categorie[6].name());
                case 8:
                    return Categoria.valueOf(categorie[7].name());
                case 9:
                    return Categoria.valueOf(categorie[8].name());
                default:
                    System.out.println("Scelta non valida. Riprova.");
                    return chiediCategoria();
            }
        } else {
            System.out.println("Scelta non valida. Riprova.");
            return chiediCategoria();
        }
    }

    public Certificazione chiediCertificazione() {
        System.out.println("Inserisci certificazione, scegliendo tra queste disponibili:");
        Certificazione[] certificazioni = Certificazione.values();
        for (int i = 0; i < certificazioni.length; i++) {
            System.out.println((i + 1) + ". " + certificazioni[i].name());
        }
        int scelta = scanner.nextInt();
        scanner.nextLine();
        if (scelta > 0 && scelta <= certificazioni.length) {
            return switch (scelta) {
                case 1 -> Certificazione.valueOf(certificazioni[0].name());
                case 2 -> Certificazione.valueOf(certificazioni[1].name());
                case 3 -> Certificazione.valueOf(certificazioni[2].name());
                case 4 -> Certificazione.valueOf(certificazioni[3].name());
                default -> {
                    System.out.println("Scelta non valida. Riprova.");
                    yield chiediCertificazione();
                }
            };
        } else {
            System.out.println("Scelta non valida. Riprova.");
            return chiediCertificazione();
        }
    }

    public int chiediQuantita() {
        System.out.print("Inserisci la quantità: ");
        return scanner.nextInt();
    }

    @Override
    public abstract void gestisciInput();
}