package app.reservationsystem.repository;

import app.reservationsystem.entite.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre, String> {
}
