package fr.biblio.repo;

import fr.biblio.model.Genre;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenreRepository implements PanacheRepositoryBase<Genre, Integer> {

}
