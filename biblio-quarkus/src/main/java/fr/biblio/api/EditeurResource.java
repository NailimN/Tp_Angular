package fr.biblio.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateEditeurRequest;
import fr.biblio.dto.response.EditeurResponse;
import fr.biblio.model.Editeur;
import fr.biblio.service.EditeurService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public class EditeurResource {
    private final static Logger log = LoggerFactory.getLogger(EditeurResource.class);

    @Inject
    private EditeurService service;

    @GET
    @PermitAll
    public List<EditeurResponse> findAll() {
        log.debug("Recherche de la liste des editeurs");

        return this.service.findAll().stream()
            .map(EditeurResponse::convert)
            .toList()
        ;
    }

    @Path("/{id}")
    @GET
    @RolesAllowed({ "admin", "user" })
    public Response findById(@PathParam("id") int id) {
        log.debug("Recherche du editeur {}", id);

        Optional<Editeur> optEditeur = this.service.findById(id);

        if (optEditeur.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(EditeurResponse.convert(optEditeur.get())).build();
    }

    @POST
    @RolesAllowed("admin")
    public Response create(@Valid CreateOrUpdateEditeurRequest request) {
        log.debug("Le nom du editeur est : {}", request.getNom());
        log.debug("Le pays du editeur est : {}", request.getPays());

        Editeur editeur = this.service.create(request);

        return Response.status(Status.CREATED)
            .entity(Map.of("id", editeur.getId()))
            .build()
        ;
    }

    @Path("/{id}")
    @PUT
    @RolesAllowed("admin")
    public Response update(@PathParam("id") int id, CreateOrUpdateEditeurRequest request) {
        log.debug("Le nom du editeur est : {}", request.getNom());
        log.debug("Le pays du editeur est : {}", request.getPays());

        this.service.update(id, request);

        return Response.ok(id).build();
    }

    @Path("/{id}")
    @DELETE
    @RolesAllowed("admin")
    public Response deleteById(@PathParam("id") int id) {
        log.debug("Suppression du editeur {}", id);

        this.service.deleteById(id);

        return Response.ok(id).build();
    }
}
