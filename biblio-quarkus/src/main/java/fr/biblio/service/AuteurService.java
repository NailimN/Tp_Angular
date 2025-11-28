package fr.biblio.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateAuteurRequest;
import fr.biblio.model.Auteur;
import fr.biblio.repo.AuteurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AuteurService {

    private final static Logger log = LoggerFactory.getLogger(AuteurService.class);

private final AuteurRepository repository;

    public AuteurService(AuteurRepository repository) {
        this.repository = repository;
    }

    public Stream<Auteur> findAll() {
        log.debug("Trombinoscope sans photos des Auteurs");

        return this.repository.findAll().stream();

    }

     public Optional<Auteur> findById(int id) {
        log.debug("Recuperations des Auteurs", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Auteur create(CreateOrUpdateAuteurRequest request) {
        log.debug("Création de l'auteur {}", request.getNom());

        Auteur auteur = new Auteur();

        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        this.repository.persist(auteur);

        return auteur;
    }

    @Transactional
    public Auteur update(int id, CreateOrUpdateAuteurRequest request) {
        log.debug("Mise à jour de l'auteur {}", id);

        Auteur auteur = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        this.repository.persist(auteur);

        return auteur;
    }

    @Transactional
    public boolean deleteById(int id) {
        log.debug("Suppression de l'auteur {}", id);

        try {
            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            log.error("Impossible de supprimer l'auteur {} : {}", id, ex.getMessage());
            return false;
        }
    }


    
}
