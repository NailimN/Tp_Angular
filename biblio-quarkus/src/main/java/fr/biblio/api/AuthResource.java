package fr.biblio.api;

import fr.biblio.dto.request.AuthRequest;
import fr.biblio.dto.response.AuthResponse;
import io.quarkus.security.credential.PasswordCredential;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.request.UsernamePasswordAuthenticationRequest;
import io.quarkus.security.jpa.runtime.JpaIdentityProvider;
import io.smallrye.jwt.build.Jwt;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/auth")
public class AuthRessource {

    private final JpaIdentityProvider jpaIdentityProvider;
    private final EntityManager entityManager;

    public AuthRessource(JpaIdentityProvider jpaIdentityProvider, EntityManager entityManager) {
        this.jpaIdentityProvider = jpaIdentityProvider;
        this.entityManager = entityManager;
    }

    @POST
    public AuthResponse auth(@Valid AuthRequest request) {
        
        UsernamePasswordAuthenticationRequest authenticationRequest = new UsernamePasswordAuthenticationRequest(
                request.username(),
                new PasswordCredential(request.password().toCharArray())
        );

        SecurityIdentity identity = this.jpaIdentityProvider.authenticate(this.entityManager, authenticationRequest);

        String jwt = Jwt.issuer("quest-quarkus-issuer")
            .upn(request.username()) // User Principal Name
            .groups(identity.getRoles()) // Le ou les rôles
            .sign() // On signe le jeton avec la clé privée
        ;

        return new AuthResponse(jwt);
    }
}
