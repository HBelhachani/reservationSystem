package app.reservationsystem.service;

import app.reservationsystem.entite.Offre;
import app.reservationsystem.repository.OffreRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class OffreService {

    private OffreRepository offreRepo;

    public OffreService(OffreRepository offreRepo){
        this.offreRepo = offreRepo;
    }

    public List<Offre> getAllOffres(){
        return offreRepo.findAll();
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

        return getAllOffres().stream().sorted(Comparator.comparing(Offre::getDate)).toList();
    }
}
