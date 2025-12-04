package app.reservationsystem.repository;

import app.reservationsystem.entite.Vol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolRepository extends JpaRepository<Vol, String> {
}
