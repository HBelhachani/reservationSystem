package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Operateur {

    @Id
    private String id;

    private String nom;

    private String code;

}
