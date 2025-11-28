package fr.biblio.service;

import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateGenreRequest;
import fr.biblio.model.Genre;
import fr.biblio.repo.GenreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GenreService {
    
    private final static Logger log = LoggerFactory.getLogger(GenreService.class);
 
    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public Stream<Genre> findAll(){
        log.debug("Liste des genres");

        return this.repository.findAll().stream(); 
    }

    public Optional<Genre> findById(int id) {
        log.debug("Récupération du genre {}", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Genre create(CreateOrUpdateGenreRequest request) {
        log.debug("Création du genre {}", request.getLibelle());

        Genre genre = new Genre();

        genre.setLibelle(request.getLibelle());

        this.repository.persist(genre);

        return genre;
    }

    @Transactional
    public Genre update(int id, CreateOrUpdateGenreRequest request) {
        log.debug("Mise à jour du genre {}", id);

        Genre genre = this.repository.findByIdOptional(id).orElseThrow(NotFoundException::new);

        genre.setLibelle(request.getLibelle());

        this.repository.persist(genre);

        return genre;
    }

    @Transactional
    public boolean deleteById(int id) {
        log.debug("Suppression du genre {}", id);

        try {
            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            log.error("Impossible de supprimer le genre {} : {}", id, ex.getMessage());
            return false;
        }
    }

}
