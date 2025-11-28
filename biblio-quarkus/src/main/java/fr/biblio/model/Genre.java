package fr.biblio.model;
import jakarta.persistence.*;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String libelle;

    public Genre(String libelle) {
        this.libelle = libelle;
    }

    public Genre(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Genre() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return "Genre{" + "libelle='" + libelle + '\'' + '}';
    }
}
