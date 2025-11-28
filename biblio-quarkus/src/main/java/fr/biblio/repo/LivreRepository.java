package fr.biblio.repo;

import fr.biblio.model.Livre;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LivreRepository implements PanacheRepositoryBase <Livre, Integer> {

}
