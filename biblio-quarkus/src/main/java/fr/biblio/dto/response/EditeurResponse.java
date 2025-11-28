package fr.biblio.dto.response;

import fr.biblio.model.Editeur;

public class EditeurResponse {
    private int id;
    private String nom;
	private String pays;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public static EditeurResponse convert(Editeur editeur) {
        EditeurResponse resp = new EditeurResponse();

        resp.setId(editeur.getId());
        resp.setNom(editeur.getNom());
        resp.setPays(editeur.getPays());

        return resp;
    }
}
