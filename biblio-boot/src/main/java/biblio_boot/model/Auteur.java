package biblio_boot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="Auteur")
public class Auteur {
	
	@Column(nullable = false)
	protected String nom; 
	
	@Column(nullable = false)
	protected String prenom; 
	
	@Column(nullable = false)
	protected String nationalite;
	
	public Auteur() {}
	
	public Auteur(String nom, String prenom, String nationalite) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

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

}


