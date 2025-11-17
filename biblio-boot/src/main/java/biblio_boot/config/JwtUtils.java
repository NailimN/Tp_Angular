package biblio_boot.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

public class JwtUtils {
    private final static String JWT_KEY = "fe47c8aff001d882ed20179bd6052bc72d756a17157e9728d4c5dd1d41fbd835";
    private final static long JWT_EXPIRATION = 300_000;

    private JwtUtils() { }

    public static String generate(Authentication auth) {
        Date now = new Date();
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        // Si la connexion est OK, on génère un jeton JWT
        return Jwts.builder()
                .subject(auth.getName()) // Souvent, c'est le username ici
                .issuedAt(now) // Date de fabrication
                .expiration(new Date(now.getTime() + JWT_EXPIRATION)) // Durée de validité
                .signWith(secretKey)
                .compact() // Le jeton JWT sous forme de String
                ;
    }

    public static Optional<String> validateAndGetSubjet(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_KEY.getBytes());

        try {
            // Si la connexion est OK, on génère un jeton JWT
            String username = Jwts.parser()
                    .verifyWith(secretKey) // On donne la clé pour valider le jeton
                    .build()
                    .parseSignedClaims(token) // On donne le jeton à valider
                    .getPayload() // Le contenu du jeton
                    .getSubject() // Le nom d'utilisateur
                    ;

            return Optional.ofNullable(username);
        }

        catch (Exception ex) {
            return Optional.empty();
        }
    }
}
