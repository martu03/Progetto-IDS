package cs.unicam.it.Accesso;
import cs.unicam.it.Repository.UtenteLogRepository;
import cs.unicam.it.Utenti.UtenteLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtenteLogRepository utenteLogRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtenteLog utente = utenteLogRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + email));

        // Restituisci un oggetto UserDetails con i ruoli corretti
        return new org.springframework.security.core.userdetails.User(
                utente.getEmail(),
                utente.getPassword(),
                utente.getAuthorities() // Assicurati che i ruoli siano configurati correttamente
        );
    }
}