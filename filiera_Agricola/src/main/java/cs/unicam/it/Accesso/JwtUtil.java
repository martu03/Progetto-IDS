package cs.unicam.it.Accesso;

import cs.unicam.it.Utenti.Ruolo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.expiration}")
    private String expirationString;

    private long expiration;
   
    private SecretKey secretKey; // Usa SecretKey invece di una stringa

    @PostConstruct
    public void init() {
        try {
            this.expiration = Long.parseLong(expirationString.trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid expiration value provided: " + expirationString);
            throw new IllegalArgumentException("Invalid expiration value: " + expirationString, e);
        }

        // Genera una chiave sicura per HS256
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Crea un token per un utente autenticato
    public String generateToken(String username, Ruolo ruolo) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", ruolo.name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secretKey, SignatureAlgorithm.HS256) // Usa la chiave sicura
                .compact();
    }

    // Verifica se il token è valido
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.err.println("Errore durante la validazione del token: " + e.getMessage());
            return false;
        }
    }

    // Estrae l'username dal token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // Usa la chiave sicura
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Metodo per estrarre l'email dal token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);
    }

    public Ruolo extractRole(String token) {
        String roleString = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles", String.class); // Estrai il ruolo come stringa
        return Ruolo.valueOf(roleString); // Converti la stringa in enum
    }
}