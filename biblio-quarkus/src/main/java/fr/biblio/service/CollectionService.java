package fr.biblio.service;



import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateCollectionRequest;
import fr.biblio.model.Collection;
import fr.biblio.repo.CollectionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CollectionService {
    private final static Logger log = LoggerFactory.getLogger(CollectionService.class);

    private final CollectionRepository repository;

    public CollectionService(CollectionRepository  repository) {
        this.repository = repository;
    }


    public List<Collection> findAll() {
        log.debug("Liste des collections");

        return this.repository.findAll().list();
    }

    public Optional<Collection> findById(int id) {
        log.debug("Récupération de la collection {}", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Collection create(CreateOrUpdateCollectionRequest request) {
        log.debug("Création du produit {}", request.getNom());

        Collection collection = new Collection();

        collection.setNom(request.getNom());

        this.repository.persist(collection);

        return collection;
    }

    @Transactional
    public Collection update(int id, CreateOrUpdateCollectionRequest request) {
        log.debug("Mise à jour de la collection {}", id);

        Collection collection = this.repository.findByIdOptional(id).orElseThrow();

        collection.setNom(request.getNom());

        this.repository.persist(collection);
        return collection  ;
    }

    @Transactional
    public boolean deleteById(int id) {
        log.debug("Suppression de la collection {}", id);

        try {
            this.repository.deleteById(id);
            return true;
        }

        catch (Exception ex) {
            log.error("Impossible de supprimer la collection {} : {}", id, ex.getMessage());
            return false;
        }
    }
}