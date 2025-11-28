package fr.biblio.dto.response;

import fr.biblio.model.Genre;

public class GenreResponse {
    private int id;
    private String libelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public static GenreResponse convert(Genre genre) {
        GenreResponse resp = new GenreResponse();

        resp.setId(genre.getId());
        resp.setLibelle(genre.getLibelle());

        return resp;
    }
}
