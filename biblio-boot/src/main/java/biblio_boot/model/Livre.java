package biblio_boot.model;

public class Livre {

    private Integer id;
    private String titre;
    private Auteur auteur;
    private Integer annee;
    private Collection collection;

    public Livre() {
    }

    public Livre(Integer annee, Auteur auteur, Collection collection, Integer id, String titre) {
        this.annee = annee;
        this.auteur = auteur;
        this.collection = collection;
        this.id = id;
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

    @Override
    public String toString() {
        return "Livre{" +
                "annee=" + annee +
                ", id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur=" + auteur +
                ", collection=" + collection +
                '}';
    }
}
