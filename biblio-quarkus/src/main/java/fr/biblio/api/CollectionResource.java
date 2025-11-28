package fr.biblio.api;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateCollectionRequest;
import fr.biblio.dto.response.CollectionResponse;
import fr.biblio.model.Collection;
import fr.biblio.service.CollectionService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/collection")
public class CollectionResource {

    private static final Logger log = LoggerFactory.getLogger(CollectionResource.class);

    private final CollectionService service;

    public CollectionResource(CollectionService service) {
        this.service = service;
    }

    @GET
    public List<CollectionResponse> findAll() {
        log.debug("Lister les collections");
        return service.findAll().stream().map(CollectionResponse::convert).toList();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") int id) {
        log.debug("Rechercher collection {}", id);
        Optional<Collection> opt = service.findById(id);

        if (opt.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(CollectionResponse.convert(opt.get())).build();
    }

    @POST
    public int create(@Valid CreateOrUpdateCollectionRequest req) {
        log.debug("Créer collection {}", req.getNom());
        return service.create(req).getId();
    }

    @PUT
    @Path("/{id}")
    public int update(@PathParam("id") int id, @Valid CreateOrUpdateCollectionRequest req) {
        log.debug("Modifier collection {}", id);
        service.update(id, req);
        return id;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        log.debug("Supprimer collection {}", id);
        service.deleteById(id);
    }
}
