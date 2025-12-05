package app.reservationsystem.controleur;

import app.reservationsystem.entite.Offre;
import app.reservationsystem.service.OffreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchControleur {

    private final OffreService offreService;

    public SearchControleur(OffreService offreService) {

        this.offreService = offreService;
    }

    @GetMapping("/offre/prix-min")
    public Offre readOffreMinPrix(){

        return offreService.getOffrePrixMin();
    }

    @GetMapping("/offre/prix-max")
    public Offre readOffreMaxPrix(){

        return offreService.getOffrePrixMax();
    }

    @GetMapping("/offres/prix-croissant")
    public List<Offre> readOffresTriePrixCroissant(){
        return offreService.getOffresPrixCroissant();
    }

    @GetMapping("/offres/prix-decroissant")
    public List<Offre> readOffresTriePrixDecroissant(){
        return offreService.getOffresPrixDecroissant();
    }

    @GetMapping("/offres/date-croissant")
    public List<Offre> readOffresTrieDate(){
        return offreService.getOffresOrdreDate();
    }


}
