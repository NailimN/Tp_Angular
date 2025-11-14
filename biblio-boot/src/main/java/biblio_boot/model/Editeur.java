package biblio_boot.model;

public class Editeur {
	
	// Attributs
	private String nom;
	private String pays;
	
	
	// Constructeurs
	public Editeur(String nom, String pays) {
		this.nom = nom;
		this.pays = pays;
	}


	// Getters et Setters
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


	// toString
	@Override
	public String toString() {
		return "Editeur [nom=" + nom + ", pays=" + pays + "]";
	}
}
