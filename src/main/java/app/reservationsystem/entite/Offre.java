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
    private Date date;

    @ManyToOne(optional = false)
    private Vol vol;

    @ManyToOne(optional = false)
    private Operateur operateur;

}
