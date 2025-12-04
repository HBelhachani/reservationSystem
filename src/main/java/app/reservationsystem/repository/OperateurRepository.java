package app.reservationsystem.repository;

import app.reservationsystem.entite.Operateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperateurRepository extends JpaRepository<Operateur, String> {
}
