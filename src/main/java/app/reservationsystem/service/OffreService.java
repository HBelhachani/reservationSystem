package app.reservationsystem.service;

import app.reservationsystem.entite.Aeroport;
import app.reservationsystem.entite.Offre;
import app.reservationsystem.entite.Operateur;
import app.reservationsystem.entite.Vol;
import app.reservationsystem.repository.AeroportRepository;
import app.reservationsystem.repository.OffreRepository;
import app.reservationsystem.repository.OperateurRepository;
import app.reservationsystem.repository.VolRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OffreService {

    private OffreRepository offreRepo;

    private OperateurRepository operateurRepo;

    private AeroportRepository aeroportRepo;

    private VolRepository volRepo;

    public OffreService(OffreRepository offreRepo, OperateurRepository operateurRepo, AeroportRepository aeroportRepo, VolRepository volRepo){
        this.offreRepo = offreRepo;
        this.operateurRepo = operateurRepo;
        this.aeroportRepo = aeroportRepo;
        this.volRepo = volRepo;
    }

    public List<Offre> getAllOffres(){
        return offreRepo.findAll();
    }

    public Offre getOffreById(String id){
        Optional<Offre> offreOptional = offreRepo.findById(id);

        if(offreOptional.isEmpty()){

            return null;
        }

        return offreOptional.get();
    }

    public Offre getOffrePrixMin(){

        List<Offre> offres = offreRepo.findAll();

        Offre offreMin = offres.getFirst();

        for(Offre o : offres){
            if(o.getPrix() < offreMin.getPrix()){
                offreMin = o;
            }
        }

         return offreMin;

    }

    public Offre getOffrePrixMax(){

        List<Offre> offres = offreRepo.findAll();

        Offre offreMin = offres.getFirst();

        for(Offre o : offres){
            if(o.getPrix() > offreMin.getPrix()){
                offreMin = o;
            }
        }

        return offreMin;

    }

    public List<Offre> getOffresPrixCroissant(){

        return getAllOffres().stream().sorted(Comparator.comparing(Offre::getPrix)).toList();
    }

    public List<Offre> getOffresPrixDecroissant(){

        return getAllOffres().stream().sorted(Comparator.comparing(Offre::getPrix).reversed()).toList();
    }

    public List<Offre> getOffresOrdreDate(){

        return getAllOffres().stream().sorted(Comparator.comparing(Offre::getDateDepart)).toList();
    }

    public Offre deleteOffre(String id){

        Optional<Offre> offreDeleted = offreRepo.findById(id);

        if(offreDeleted.isPresent()){
            offreRepo.deleteById(id);

        }

        return offreDeleted.get();

    }

    public Offre createOffre(
            String id,
            String trajetId,
            String operateurId,
            Date depart,
            double prixBase,
            String aeroportDepartId,
            String aeroportDstId) {

        // Basic validations
        if(id == null || id.isEmpty()
                || trajetId == null || trajetId.isEmpty()
                || operateurId == null || operateurId.isEmpty()
                || depart == null || depart.before(Date.from(Instant.now()))
                || aeroportDepartId == null || aeroportDepartId.isEmpty()
                || aeroportDstId == null || aeroportDstId.isEmpty()
                || aeroportDepartId.equals(aeroportDstId)) {
            throw new IllegalArgumentException("Invalid input for creating an Offre");
        }

        // Fetch related entities
        Aeroport origine = aeroportRepo.findById(aeroportDepartId)
                .orElseThrow(() -> new IllegalArgumentException("Origin airport not found"));
        Aeroport destination = aeroportRepo.findById(aeroportDstId)
                .orElseThrow(() -> new IllegalArgumentException("Destination airport not found"));
        Operateur operateur = operateurRepo.findById(operateurId)
                .orElseThrow(() -> new IllegalArgumentException("Operateur not found"));

        // Create Vol
        Vol vol = new Vol();
        vol.setId(trajetId);
        vol.setOrigine(origine);
        vol.setDestination(destination);
        // vol.setNumero(...)   // optionally set flight number
        // vol.setDuree(...)    // optionally calculate/set duration
        volRepo.save(vol);

        // Create Offre
        Offre offre = new Offre();
        offre.setId(id);
        offre.setDateDepart(depart);
        offre.setPrix(prixBase);
        offre.setVol(vol);
        offre.setOperateur(operateur);

        return offreRepo.save(offre);
    }


}
