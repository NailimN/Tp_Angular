package biblio_boot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="editeur")
public class Editeur {
	
	// Attributs
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String nom;
	
	@Column(nullable = false)
	private String pays;
	
	
	// Constructeurs
	public Editeur() {}
	
	public Editeur(String nom, String pays) {
		this.nom = nom;
		this.pays = pays;
	}
	
	public Editeur(Integer id, String nom, String pays) {
		this.id = id;
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
