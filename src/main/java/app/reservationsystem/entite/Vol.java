package app.reservationsystem.entite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Aeroport getOrigine() {
        return origine;
    }

    public void setOrigine(Aeroport origine) {
        this.origine = origine;
    }

    public Aeroport getDestination() {
        return destination;
    }

    public void setDestination(Aeroport destination) {
        this.destination = destination;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }
}
