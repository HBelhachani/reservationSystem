package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aeroport {

    @Id
    private String id;

    private String ville;

    private String code;

}
