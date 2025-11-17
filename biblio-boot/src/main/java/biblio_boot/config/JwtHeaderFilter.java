package biblio_boot.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String key = "fe47c8aff001d882ed20179bd6052bc72d756a17157e9728d4c5dd1d41fbd835";
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        if (header != null) {
            String token = header.substring(7); // On enlève "Bearer " pour garder que le jeton



            // On vérifie le jeton, et si tout est OK, on récupère l'utilisateur associé à ce jeton
            Optional<String> optUsername = JwtUtils.validateAndGetSubjet(token);

            if (optUsername.isPresent()) {
                // On refabrique une liste de rôles pour l'utilisateur
                List<GrantedAuthority> autorities = new ArrayList<>();

                autorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                // Créer, pour Spring Security, un nouvel User, avec le nom d'utilisateur, pas de mdp, et la liste des autorités
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(optUsername.get(), null, autorities);

                // Injecter notre nouvel authentication dans le contexte de Spring Security
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // Important pour chainer sur le filtre suivant
        filterChain.doFilter(request, response);
    }
}
