package cs.unicam.it.Accesso;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class GenerateSecretKey {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        byte[] keyBytes = key.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Chiave segreta: " + encodedKey);
    }
}