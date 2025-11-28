package fr.biblio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.SubscribeRequest;
import fr.biblio.model.Utilisateur;
import fr.biblio.repo.UtilisateurRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UtilisateurService {
    private final static Logger log = LoggerFactory.getLogger(UtilisateurService.class);

    private final UtilisateurRepository repository;

    public UtilisateurService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Utilisateur subscribe(SubscribeRequest request) {
        log.debug("Ajout de l'utilisateur {}", request.username());

        Utilisateur utilisateur = new Utilisateur();
        String hashedPassword = BcryptUtil.bcryptHash(request.password());

        utilisateur.setUsername(request.username());
        utilisateur.setPassword(hashedPassword);
        utilisateur.setRole("user");

        this.repository.persist(utilisateur);

        return utilisateur;
    }
}
