package fr.biblio.dto.response;

import fr.biblio.model.Collection;  

public class CollectionResponse {

    private Integer id;
    private String nom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static CollectionResponse convert(Collection c) {
        CollectionResponse resp = new CollectionResponse();
        resp.setId(c.getId());
        resp.setNom(c.getNom());
        return resp;
    }
}
