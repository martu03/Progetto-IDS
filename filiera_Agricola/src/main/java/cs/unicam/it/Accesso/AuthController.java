package cs.unicam.it.Accesso;

import cs.unicam.it.Repository.GestorePiattaformaRepository;
import cs.unicam.it.Utenti.*;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtenteLogRepository utenteLogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private GestorePiattaformaRepository gestorePiattaformaRepository;
    @GetMapping("/test")
    public ResponseEntity<List<UtenteLog>> test() {
        return ResponseEntity.ok(utenteLogRepository.findAll());
    }

    @PostMapping("/setup/gestore")
    public ResponseEntity<String> setupGestore(@RequestBody SetupGestoreRequest request) {
        // Controlla se il gestore piattaforma è già stato configurato
        if (gestorePiattaformaRepository.count() > 0) {
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
        gestorePiattaformaRepository.save(gestore);

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

        // Verifica se l'email è già registrata
        if (utenteLogRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email già registrata.");
        }

        Ruolo role = Ruolo.fromAuthority(request.getRole());
        if (role == null) {
            return ResponseEntity.badRequest().body("Ruolo non valido.");
        }

        if (Ruolo.GESTORE.equals(Ruolo.valueOf(request.getRole()))) {
            return ResponseEntity.badRequest().body("Il ruolo GESTORE non può essere registrato tramite questa procedura.");
        }

        // Crea l'utente in base al ruolo
        UtenteLog user = createUser(request, role);
        if (user == null) {
            return ResponseEntity.badRequest().body("Tipo di utente non valido.");
        }

        GestorePiattaforma gestore = gestorePiattaformaRepository.findAll().get(0);
        gestore.aggiungiUtenteInAttesa(user);
        utenteLogRepository.save(user); // Salva l'utente nel database

        return ResponseEntity.ok("Utente registrato in attesa di approvazione.");
    }

    private UtenteLog createUser(RegistrationRequest request, Ruolo ruolo) {
        switch (ruolo) {
            case ACQUIRENTE:
                return new Acquirente(request.getNome(), request.getEmail(), request.getPassword(), request.getIndirizzo());
            case ANIMATORE:
                return new Animatore(request.getNome(), request.getEmail(), request.getPassword());
            case CURATORE:
                return new Curatore(request.getNome(), request.getEmail(), request.getPassword());
            case DISTRIBUTORE:
                // Implementa la creazione di DISTRIBUTORE
                break;
            case PRODUTTORE:
                // Implementa la creazione di PRODUTTORE
                break;
            case TRASFORMATORE:
                // Implementa la creazione di TRASFORMATORE
                break;
            case AZIENDA:
                // Implementa la creazione di AZIENDA
                break;
            default:
                return null;
        }
        return null;
    }

    private String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
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

            // Verifica la password
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password errata");
            }

            // Genera il token JWT
            String token = jwtUtil.generateToken(user.getEmail(), user.getRuolo() );

            // Restituisci il token
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (Exception e) {
            System.out.println("Errore durante l'autenticazione: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali non valide");
        }
    }


}




