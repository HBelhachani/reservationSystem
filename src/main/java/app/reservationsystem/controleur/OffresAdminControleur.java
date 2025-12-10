package app.reservationsystem.controleur;

import app.reservationsystem.entite.Offre;
import app.reservationsystem.service.OffreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Offre> deleteOffre(@PathVariable String id){

        return ResponseEntity.ok(
                offreService.deleteOffre(id)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Offre> createOffre(
            @RequestParam String id,
            @RequestParam String trajetId,
            @RequestParam String operateurId,
            @RequestParam String depart,   // <-- change from Date to String
            @RequestParam double prixBase,
            @RequestParam String aeroportDepartId,
            @RequestParam String aeroportDstId
    ) {
        // Parse string to Date
        Date departDate;
        try {
            departDate = Date.from(LocalDateTime.parse(depart).atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format, use yyyy-MM-ddTHH:mm:ss");
        }

        return ResponseEntity.ok(
                offreService.createOffre(id, trajetId, operateurId, departDate, prixBase, aeroportDepartId, aeroportDstId)
        );
    }


}
