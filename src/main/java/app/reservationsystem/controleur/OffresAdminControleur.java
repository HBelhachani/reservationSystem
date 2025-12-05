package app.reservationsystem.controleur;

import app.reservationsystem.entite.Offre;
import app.reservationsystem.service.OffreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/offres")
public class OffresAdminControleur {

    private final OffreService offreService;

    public OffresAdminControleur(OffreService offreService) {
        this.offreService = offreService;
    }

    //CRUD
    @GetMapping("/all")
    public List<Offre> read() {
        return offreService.getAllOffres();
    }
}
