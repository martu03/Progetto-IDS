package cs.unicam.it.Accesso;

import cs.unicam.it.Utenti.Ruolo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService; // Inject il bean UserDetailsService


    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {

        //va a prenddere il token
        String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // va ancora a prendere il token
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
//            username = jwtUtil.validateToken(jwt) ? jwtUtil.extractUsername(jwt) : null;
            username = jwtUtil.extractUsername(jwt);
        }

        //Se l'utente è già autenticato, il filtro non deve ripetere il processo di autenticazione.
        //Se l'utente non è autenticato, il filtro procede a impostare l'autenticazione.
        // Se il token è valido e l'autenticazione non è già impostata, autentica l'utente
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails =  userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt)) {
                Ruolo ruolo = jwtUtil.extractRole(jwt);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}