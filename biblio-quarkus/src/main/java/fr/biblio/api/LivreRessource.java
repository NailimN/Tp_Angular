package fr.biblio.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.model.Livre;
import fr.biblio.service.LivreService;
import io.quarkus.security.Authenticated;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/livre")
@Authenticated
public class LivreRessource {
    private final static Logger log = LoggerFactory.getLogger(LivreRessource.class);

    private final LivreService service;

    public LivreRessource(LivreService service) {
        this.service = service;
    }

    @GET
    public List<Livre> getAllLivres(){
        log.info("GET /api/livre - getAllLivre() called");
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Livre getLivreById(@PathParam("id") int id) {
        log.info("GET /api/livre/{} - getLivreById() called", id);
        return service.findById(id);
    }

    @POST
    public Livre createLivre(Livre livre) {
        log.info("POST /api/livre - createLivre() called");
        return service.create(livre);
    }

    @PUT
    @Path("/{id}")
    public Livre updateLivre(@PathParam("id") int id, Livre livre) {
        log.info("PUT /api/livre/{} - updateLivre() called", id);
        return service.update(id, livre);
    }

    @DELETE
    @Path("/{id}")
    public void deleteLivre(@PathParam("id") int id) {
        log.info("DELETE /api/livre/{} - deleteLivre() called", id);
        service.deleteById(id);
    }
}
