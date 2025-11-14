package biblio_boot.model;

import jakarta.persistence.*;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private Integer annee;

    @ManyToOne
    @JoinColumn(name="auteur")
    private Auteur auteur;

    @ManyToOne
    @JoinColumn(name="editeur")
    private Editeur editeur;

    @ManyToOne
    @JoinColumn(name="collection")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name="genre")
    private Genre genre;

    public Livre() {
    }

    public Livre(Integer annee, Auteur auteur, Collection collection, Editeur editeur, Genre genre, Integer id, String titre) {
        this.annee = annee;
        this.auteur = auteur;
        this.collection = collection;
        this.editeur = editeur;
        this.genre = genre;
        this.id = id;
        this.titre = titre;
    }

    public Livre(Integer annee, Auteur auteur, Collection collection, Editeur editeur, Genre genre, String titre) {
        this.annee = annee;
        this.auteur = auteur;
        this.collection = collection;
        this.editeur = editeur;
        this.genre = genre;
        this.titre = titre;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "annee=" + annee +
                ", id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur +
                ", collection=" + collection +
                ", genre=" + genre +
                '}';
    }
}
