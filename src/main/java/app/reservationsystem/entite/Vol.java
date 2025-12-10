package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Vol {

    @Id
    private String id;

    private String numero;

    @ManyToOne(optional=false) @JoinColumn(name = "origine_id")
    private Aeroport origine;

    @ManyToOne(optional=false) @JoinColumn(name = "destination_id")
    private Aeroport destination;

    private double duree;

}
