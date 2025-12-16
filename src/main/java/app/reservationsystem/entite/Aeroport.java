package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aeroport {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String ville;

    private String code;

}
