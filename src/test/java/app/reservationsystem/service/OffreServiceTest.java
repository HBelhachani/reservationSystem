package app.reservationsystem.service;

import app.reservationsystem.entite.Offre;
import app.reservationsystem.repository.AeroportRepository;
import app.reservationsystem.repository.OffreRepository;
import app.reservationsystem.repository.OperateurRepository;
import app.reservationsystem.repository.VolRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OffreServiceTest {


    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private OffreService offreService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Tester si la méthode retourne l'offre correspondant a celle qui est moins chère")
    void testGetOffrePrixMinTrouve() throws JsonProcessingException {

        String json = """
                        {
                            "vol": {
                                "destination": {
                                    "code": "YYZ",
                                    "id": "2",
                                    "ville": "Toronto"
                                },
                                "duree": 80.0,
                                "id": "102",
                                "numero": "AC401",
                                "origine": {
                                    "code": "YUL",
                                    "id": "1",
                                    "ville": "Montréal"
                                }
                            },
                            "dateDepart": "2025-12-10T13:15:00.000Z",
                            "prix": 120.0,
                            "id": "1003",
                            "operateur": {
                                "code": "AC",
                                "id": "1",
                                "nom": "Air Canada"
                            }
                        }
                
                """;

        Offre offre = objectMapper.readValue(json, Offre.class);

        assertEquals(offre, offreService.getOffrePrixMin());

    }

    @Test
    @DisplayName("Tester trouver Offre prix min avec aucune offre dans la base de donnée")
    void testGetOffrePrixMinNonTrouve() throws JsonProcessingException {

        offreRepository.deleteAll();

        assertEquals(null, offreService.getOffrePrixMin());

    }

    @Test
    @DisplayName("Tester si la méthode retourne l'offre correspondant a celle qui est la plus chère")
    void testGetOffrePrixMaxTrouve() throws JsonProcessingException {

        String json = """
                        {
                             "vol": {
                                 "destination": {
                                     "code": "LHR",
                                     "id": "6",
                                     "ville": "London"
                                 },
                                 "duree": 420.0,
                                 "id": "106",
                                 "numero": "EK500",
                                 "origine": {
                                     "code": "DXB",
                                     "id": "7",
                                     "ville": "Dubai"
                                 }
                             },
                             "dateDepart": "2025-12-20T17:00:00.000Z",
                             "prix": 1200.0,
                             "id": "1006",
                             "operateur": {
                                 "code": "EK",
                                 "id": "4",
                                 "nom": "Emirates"
                             }
                         }
                
                """;

        Offre offre = objectMapper.readValue(json, Offre.class);

        assertEquals(offre, offreService.getOffrePrixMax());

    }

    @Test
    @DisplayName("Tester trouver Offre prix max avec aucune offre dans la base de donnée")
    void testGetOffrePrixMaxNonTrouve() throws JsonProcessingException {

        offreRepository.deleteAll();

        assertEquals(null, offreService.getOffrePrixMax());

    }


    @Test
    @DisplayName("Tester creer offre tous les paramètres valides")
    void testCreerOffreParamValides() throws JsonProcessingException {

        String json = """
                
                {
                    "vol": {
                        "destination": {
                            "code": "CDG",
                            "id": "3",
                            "ville": "Paris"
                        },
                        "duree": 0.0,
                        "id": "110",
                        "numero": null,
                        "origine": {
                            "code": "YUL",
                            "id": "1",
                            "ville": "Montréal"
                        }
                    },
                    "dateDepart": "2026-01-15T17:30:00.000Z",
                    "prix": 950.0,
                    "id": "2001",
                    "operateur": {
                        "code": "AC",
                        "id": "1",
                        "nom": "Air Canada"
                    }
                }
                """;

        Offre offre = objectMapper.readValue(json, Offre.class);

        assertEquals(offre, offreService.createOffre("2001", "110", "1", "2026-01-15T12:30:00", 950, "1", "3"));

    }

    @Test
    @DisplayName("Tester créer offre avec date antérieure à la date actuelle")
    public void testerCreerOffreDateUlterieureMaintenant(){

        assertThrows(IllegalArgumentException.class, () -> {
            offreService.createOffre("2001", "110", "1", "2023-01-15T12:30:00", 950, "1", "3");
        });

    }

    @Test
    @DisplayName("Tester créer offre avec id ")
    public void testerCreerOffreIdExistant(){

        assertThrows(IllegalArgumentException.class, () -> {
            offreService.createOffre("1001", "110", "1", "2026-01-15T12:30:00", 950, "1", "3");
        });

    }

    @Test
    @DisplayName("Tester delete offre id existant")
    public void testerDeleteOffreIdExistant(){

        assertNull(offreService.deleteOffre("1001"));
    }

    @Test
    @DisplayName("Tester delete offre id introuvable")
    public void testerDeleteOffreIdIntrouvable(){

        assertNull(offreService.deleteOffre("151241"));
    }

}