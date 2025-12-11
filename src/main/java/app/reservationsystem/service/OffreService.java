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
import java.time.LocalDateTime;
import java.time.ZoneId;
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

        if(offres.isEmpty()) return null;

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

        if(offres.isEmpty()) return null;

        Offre offreMax = offres.getFirst();

        for(Offre o : offres){
            if(o.getPrix() > offreMax.getPrix()){
                offreMax = o;
            }
        }

        return offreMax;

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
        }else {
            return null;
        }

        return offreDeleted.get();

    }

    public Offre createOffre(
            String id,
            String trajetId,
            String operateurId,
            String departString,
            double prixBase,
            String aeroportDepartId,
            String aeroportDstId) {

        Date departDate;
        try {
            departDate = Date.from(LocalDateTime.parse(departString).atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format, use yyyy-MM-ddTHH:mm:ss");
        }

        if(id == null || id.isEmpty()
                || trajetId == null || trajetId.isEmpty()
                || operateurId == null || operateurId.isEmpty() || departDate.before(Date.from(Instant.now()))
                || aeroportDepartId == null || aeroportDepartId.isEmpty()
                || aeroportDstId == null || aeroportDstId.isEmpty()
                || aeroportDepartId.equals(aeroportDstId)) {
            throw new IllegalArgumentException("Invalid input for creating an Offre");
        }


        Aeroport origine = aeroportRepo.findById(aeroportDepartId)
                .orElseThrow(() -> new IllegalArgumentException("Aeroport d'origine non trouv"));
        Aeroport destination = aeroportRepo.findById(aeroportDstId)
                .orElseThrow(() -> new IllegalArgumentException("Aeroport de destination non trouvé."));
        Operateur operateur = operateurRepo.findById(operateurId)
                .orElseThrow(() -> new IllegalArgumentException("Operateur existe déja"));

        if(offreRepo.existsById(id)) throw new IllegalArgumentException("L'offre existe déjà");

        Vol vol = new Vol();
        vol.setId(trajetId);
        vol.setOrigine(origine);
        vol.setDestination(destination);

        //TODO
        // vol.setNumero()
        // vol.setDuree()
        volRepo.save(vol);

        Offre offre = new Offre();
        offre.setId(id);
        offre.setDateDepart(departDate);
        offre.setPrix(prixBase);
        offre.setVol(vol);
        offre.setOperateur(operateur);

        offreRepo.save(offre);

        return offre;
    }

    public Offre updateOffre(
            String id,
            String departString,
            Double prixBase
    ) {

        Offre existingOffre = offreRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offre not found"));

        Date departDate = null;
        if (departString != null) {
            try {
                departDate = Date.from(LocalDateTime.parse(departString)
                        .atZone(ZoneId.systemDefault()).toInstant());
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid date format, use yyyy-MM-ddTHH:mm:ss");
            }
            if (departDate.before(Date.from(Instant.now()))) {
                throw new IllegalArgumentException("Departure date cannot be in the past");
            }
        }

        if (prixBase != null) existingOffre.setPrix(prixBase);
        if (departDate != null) existingOffre.setDateDepart(departDate);

        offreRepo.save(existingOffre);

        return existingOffre;
    }


}
