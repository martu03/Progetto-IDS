package cs.unicam.it.Controller;

import cs.unicam.it.Accesso.AuthResponse;
import cs.unicam.it.Accesso.JwtUtil;
import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Request.LoginRequest;
import cs.unicam.it.Request.RegistrationRequest;
import cs.unicam.it.Request.SetupGestoreRequest;
import cs.unicam.it.Utenti.*;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtenteLogRepository utenteLogRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/setup/gestore")
    public ResponseEntity<String> setupGestore(@RequestBody SetupGestoreRequest request) {
        // Controlla se il gestore piattaforma è già stato configurato
        if (!utenteLogRepository.findGestori().isEmpty()) {
            return ResponseEntity.badRequest().body("Il gestore piattaforma è già stato configurato.");
        }
        // Crea il gestore piattaforma
        GestorePiattaforma gestore = new GestorePiattaforma(
                request.getNome(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Ruolo.GESTORE
        );

        // Salva il gestore nel repository (facoltativo)
        utenteLogRepository.save(gestore);

        return ResponseEntity.ok("Gestore piattaforma configurato con successo.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest request) {

        // Validazione dei dati di input
        if (request == null ||
                StringUtils.isEmpty(request.getNome()) ||
                StringUtils.isEmpty(request.getEmail()) ||
                StringUtils.isEmpty(request.getPassword()) ||
                StringUtils.isEmpty(request.getRole())) {
            return ResponseEntity.badRequest().body("Campi mancanti o invalidi.");
        }

        if (utenteLogRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email già registrata.");
        }

        Ruolo role = Ruolo.fromAuthority(request.getRole());
        if (role == null) {
            return ResponseEntity.badRequest().body("Ruolo non valido.");
        }

        if ((Ruolo.CURATORE.equals(role) && !utenteLogRepository.findCuratori().isEmpty()) ||
                (Ruolo.ANIMATORE.equals(role) && !utenteLogRepository.findAnimatori().isEmpty()) ||
                Ruolo.GESTORE.equals(role)) {
            return ResponseEntity.badRequest().body("Ruolo non valido o già configurato.");
        }

        UtenteLog user = createUser(request, role);
        if (user == null) {
            return ResponseEntity.badRequest().body("Tipo di utente non valido.");
        }

        user.setApprovato(false);
        utenteLogRepository.save(user); // Salva l'utente nel database
        System.out.println("Password codificata: " + user.getPassword());

        return ResponseEntity.ok("Utente registrato in attesa di approvazione.");
    }

    private UtenteLog createUser(RegistrationRequest request, Ruolo ruolo) {
        return switch (ruolo) {
            case CURATORE ->
                    new Curatore(request.getNome(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            case ANIMATORE ->
                    new Animatore(request.getNome(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
            case ACQUIRENTE ->
                    new Acquirente(request.getNome(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getIndirizzo());
            case PRODUTTORE ->
                    new Produttore(request.getNome(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getSede());
            case TRASFORMATORE ->
                    new Trasformatore(request.getNome(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getSede());
            case DISTRIBUTORE ->
                    new Distributore(request.getNome(), request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getSede());
            default -> null;
        };
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Tentativo di login per: " + loginRequest.getEmail());
        try {
            // Cerca l'utente nel database in base all'email
            Optional<UtenteLog> userOptional = utenteLogRepository.findByEmail(loginRequest.getEmail());
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non trovato");
            }
            UtenteLog user = userOptional.get();

            // Verifica se l'utente è approvato
            if (!user.isApprovato()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utente non approvato");
            }

            // Verifica la password
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password errata");
            }

            // Genera il token JWT
            String token = jwtUtil.generateToken(user.getEmail(), user.getRuolo());

            // Restituisci il token
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            System.out.println("Errore durante l'autenticazione: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali non valide");
        }
    }


}




