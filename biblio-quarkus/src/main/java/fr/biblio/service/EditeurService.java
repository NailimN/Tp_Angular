package fr.biblio.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateEditeurRequest;
import fr.biblio.model.Editeur;
import fr.biblio.repo.EditeurRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EditeurService {
    private final static Logger log = LoggerFactory.getLogger(EditeurService.class);

    private final EditeurRepository repository;

    public EditeurService(EditeurRepository repository) {
        this.repository = repository;
    }


    public List<Editeur> findAll() {
        log.debug("Liste des produits");

        return this.repository.findAll().list();
    }

    public Optional<Editeur> findById(int id) {
        log.debug("Récupération du produit {}", id);

        return this.repository.findByIdOptional(id);
    }

    @Transactional
    public Editeur create(CreateOrUpdateEditeurRequest request) {
        log.debug("Création du produit {}", request.getNom());

        Editeur produit = new Editeur();

        produit.setNom(request.getNom());
        produit.setPays(request.getPays());

        this.repository.persist(produit);

        return produit;
    }

    @Transactional
    public Editeur update(int id, CreateOrUpdateEditeurRequest request) {
        log.debug("Mise à jour du produit {}", id);

        Editeur produit = this.repository.findByIdOptional(id).orElseThrow();

        produit.setNom(request.getNom());
        produit.setPays(request.getPays());

        this.repository.persist(produit);

        return produit;
    }

    @Transactional
    public void deleteById(int id) {
        log.debug("Suppression du produit {}", id);

        this.repository.deleteById(id);
    }
}
