package app.reservationsystem.service;

import app.reservationsystem.entite.Offre;
import app.reservationsystem.repository.OffreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreService {

    private final OffreRepository offreRepo;

    public OffreService(OffreRepository offreRepo) {
        this.offreRepo = offreRepo;
    }

    public List<Offre> getAllOffres() {
        return offreRepo.findAll();
    }
}
