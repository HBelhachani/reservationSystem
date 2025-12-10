package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
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

}
