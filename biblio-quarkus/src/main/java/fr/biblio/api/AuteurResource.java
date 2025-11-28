package fr.biblio.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.biblio.dto.request.CreateOrUpdateAuteurRequest;
import fr.biblio.dto.response.AuteurResponse;
import fr.biblio.model.Auteur;
import fr.biblio.service.AuteurService;
import io.quarkus.security.Authenticated;
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

@Path("/auteur")
@Authenticated
public class AuteurResource {
    private final static Logger log = LoggerFactory.getLogger(AuteurService.class);

    @Inject
    private AuteurService service;


    @GET
    public List<AuteurResponse> findAll() {
        log.debug("Trombinoscope sans photos des Auteurs");

            return this.service.findAll().stream()
            .map(AuteurResponse::convert)
            .toList();

    }



    @Path("/{id}")
    @GET
     public Response findById(@PathParam("id") int id) {
        log.debug("Recuperations des Auteurs", id);

    Optional<Auteur> optAuteur = this.service.findById(id);

        if (optAuteur.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }

        return Response.ok(AuteurResponse.convert(optAuteur.get())).build();
    }

@POST
    public Response create(@Valid CreateOrUpdateAuteurRequest request) {
        log.debug("Création de l'auteur {}", request.getNom());

        Auteur auteur = this.service.create(request);

      return Response.status(Status.CREATED)
            .entity(Map.of("id", auteur.getId()))
            .build();
    }

    @Path("/{id}")
    @PUT
    public Response update(@PathParam("id") int id, CreateOrUpdateAuteurRequest request) {
        log.debug("Mise à jour de l'auteur {}", id);

        this.service.update(id, request);

        return Response.ok(id).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteById(@PathParam("id") int id) {
        log.debug("Suppression de l'auteur {}", id);

        this.service.deleteById(id);

        return Response.ok(id).build();
    }
    
}
