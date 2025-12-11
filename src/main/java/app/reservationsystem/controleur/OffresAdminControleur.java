package app.reservationsystem.controleur;

import app.reservationsystem.entite.Offre;
import app.reservationsystem.service.OffreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/offres")
public class OffresAdminControleur {

    private final OffreService offreService;

    public OffresAdminControleur(OffreService offreService) {
        this.offreService = offreService;
    }

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
            @RequestParam String depart,
            @RequestParam double prixBase,
            @RequestParam String aeroportDepartId,
            @RequestParam String aeroportDstId
    ) {

        return ResponseEntity.ok(
                offreService.createOffre(id, trajetId, operateurId, depart, prixBase, aeroportDepartId, aeroportDstId)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Offre> updateOffre(
            @RequestParam String id,
            @RequestParam(required = false) String depart,
            @RequestParam(required = false) Double prixBase
    ){
        return ResponseEntity.ok(
                offreService.updateOffre(id, depart, prixBase)
        );
    }


}
