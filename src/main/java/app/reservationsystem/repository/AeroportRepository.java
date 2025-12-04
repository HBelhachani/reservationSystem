package app.reservationsystem.repository;


import app.reservationsystem.entite.Aeroport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AeroportRepository extends JpaRepository<Aeroport, String> {
}
