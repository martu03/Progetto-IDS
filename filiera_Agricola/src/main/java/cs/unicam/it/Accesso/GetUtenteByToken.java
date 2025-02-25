package cs.unicam.it.Accesso;

import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUtenteByToken {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UtenteLogRepository utenteLogRepository;

    // Metodo ausiliario per estrarre il token dall'header di autorizzazione
    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    // Metodo privato per recuperare l'acquirente corrente dal token
    public UtenteLog getCurrentUtente(String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        if (token == null || token.isEmpty()) {
            System.out.println("Token mancante o invalido.");
            return null; // Token mancante o invalido
        }

        String email = jwtUtil.extractEmail(token);
        if (email == null || email.isEmpty()) {
            System.out.println("Email non trovata nel token.");
            return null; // Token non valido
        }

        Optional<UtenteLog> optionalUser = utenteLogRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            System.out.println("Utente non trovato con l'email: " + email);
            return null; // Utente non trovato
        }

        return optionalUser.get(); // L'utente non è un acquirente
    }


}
