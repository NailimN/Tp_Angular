package fr.biblio.dto.response;

import fr.biblio.model.Auteur;
import fr.biblio.model.Editeur;

public class AuteurResponse {
    
	protected Integer id;
	protected String nom; 
	protected String prenom; 
	protected String nationalite;

    
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    } 

	public static AuteurResponse convert(Auteur auteur) {
        AuteurResponse resp = new AuteurResponse();

        resp.setId(auteur.getId());
        resp.setNom(auteur.getNom());
        resp.setPrenom(auteur.getPrenom());
		resp.setNationalite(auteur.getNationalite());

        return resp;
    }


}
