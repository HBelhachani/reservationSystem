package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Offre {

    @Id
    private String id;

    private double prix;
    private Date dateDepart;

    @ManyToOne(optional = false)
    private Vol vol;

    @ManyToOne(optional = false)
    private Operateur operateur;

    public Offre(Vol vol, Date dateDepart, double prix){
        this.vol = vol;
        this.dateDepart = dateDepart;
        this.prix = prix;
    }

    public Offre(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    public Operateur getOperateur() {
        return operateur;
    }

    public void setOperateur(Operateur operateur) {
        this.operateur = operateur;
    }
}
