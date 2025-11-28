package fr.biblio.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateGenreRequest;
import fr.biblio.dto.response.GenreResponse;
import fr.biblio.model.Genre;
import fr.biblio.service.GenreService;
import io.quarkus.security.Authenticated;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/genre")
@Authenticated
public class GenreResource {
    private final static Logger log = LoggerFactory.getLogger(GenreResource.class);

    private final GenreService service;

    public GenreResource(GenreService service) {
        this.service = service;
    }

    @GET
    public List<GenreResponse> findAll() {
        log.debug("Lister les genres");

        return this.service.findAll().map(GenreResponse::convert).toList();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") int id) {
        log.debug("Rechercher le genre {}", id);

        Optional<Genre> optMatiere = this.service.findById(id);

        if (optMatiere.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(GenreResponse.convert(optMatiere.get())).build();
    }

    @POST
    public int create(@Valid CreateOrUpdateGenreRequest request) {
        log.debug("Créer le genre {}", request.getLibelle());

        return this.service.create(request).getId();
    }

    @Path("/{id}")
    @PUT
    public int update(@PathParam("id") int id, @Valid CreateOrUpdateGenreRequest request) {
        log.debug("Mettre à jour le genre {}", id);

        this.service.update(id, request);

        return id;
    }

    @Path("/{id}")
    @DELETE
    public boolean deleteById(@PathParam("id") int id) {
        log.debug("Supprimer le genre {}", id);

        return this.service.deleteById(id);
    }

}
