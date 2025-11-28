package fr.biblio.service;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.model.Genre;
import fr.biblio.repo.GenreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GenreService {
    
    private final static Logger log = LoggerFactory.getLogger(GenreService.class);
 
    private final GenreRepository repository;

    public Stream<Genre> findALl(){
        log.debug("Liste des genres");

        return this.repository.findAll().stream(); 
    }

    public Optional<Genre> findById(int id) {
        log.debug("Récupération de la matière {}", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Matiere create(CreateOrUpdateMatiereRequest request) {
        log.debug("Création de la matière {}", request.getLibelle());

        Matiere matiere = new Matiere();

        matiere.setLibelle(request.getLibelle());

        this.repository.persist(matiere);

        return matiere;
    }

    @Transactional
    public Matiere update(int id, CreateOrUpdateMatiereRequest request) {
        log.debug("Mise à jour de la matière {}", id);

        Matiere matiere = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        matiere.setLibelle(request.getLibelle());

        this.repository.persist(matiere);

        return matiere;
    }

    @Transactional
    public boolean deleteById(int id) {
        log.debug("Suppression de la matière {}", id);

        try {
            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            log.error("Impossible de supprimer la matière {} : {}", id, ex.getMessage());
            return false;
        }
    }

}
