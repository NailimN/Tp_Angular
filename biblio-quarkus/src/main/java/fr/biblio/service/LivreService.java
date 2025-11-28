package fr.biblio.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.model.Livre;
import fr.biblio.repo.LivreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LivreService {

    private final static Logger log = LoggerFactory.getLogger(LivreService.class);
    
    private final LivreRepository repository;

    public LivreService(LivreRepository repository) {
        this.repository = repository;
    }

    public List<Livre> findAll() {
        log.debug("Liste des livres");

        return this.repository.findAll().list();
    }


    public Livre findById(int id) {
        log.debug("Récupération du livre {}", id);

        return this.repository.findById(id);
    }

    @Transactional
    public Livre create(Livre livre) {
        log.debug("Création du livre {}", livre.getTitre());

        this.repository.persist(livre);

        return livre;
    }

    @Transactional
    public Livre update(int id, Livre livre) {
        log.debug("Mise à jour du livre {}", id);

        Livre existingLivre = this.repository.findById(id);
        if (existingLivre == null) {
            throw new IllegalArgumentException("Livre not found");
        }

        existingLivre.setTitre(livre.getTitre());
        existingLivre.setResume(livre.getResume());
        existingLivre.setAnnee(livre.getAnnee());
        existingLivre.setAuteur(livre.getAuteur());
        existingLivre.setEditeur(livre.getEditeur());
        existingLivre.setCollection(livre.getCollection());
        existingLivre.setGenre(livre.getGenre());

        this.repository.persist(existingLivre);

        return existingLivre;
    }

    @Transactional
    public boolean deleteById(int id) {
        log.debug("Suppression du livre {}", id);

        return this.repository.deleteById(id);
    }

    
}
