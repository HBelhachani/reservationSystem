package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Vol {

    @Id
    private String id;

    private String numero;

    private Date depart;
    private Date arrivee;
}
