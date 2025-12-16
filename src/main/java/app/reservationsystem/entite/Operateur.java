package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Operateur {

    @Id
    private String id;

    private String nom;

    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
