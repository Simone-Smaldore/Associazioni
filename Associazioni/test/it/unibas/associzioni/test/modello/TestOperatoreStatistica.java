package it.unibas.associzioni.test.modello;

import it.unibas.associazioni.modello.Iscrizione;
import it.unibas.associazioni.modello.OperatoreStatistica;
import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.modello.Statistica;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;


public class TestOperatoreStatistica extends TestCase{
    
    public void testGeneraStatistica() {
        OperatoreStatistica operatore = new OperatoreStatistica();
        Persona persona1 = new Persona("a", "a", "a", "Basilicata", true, 1);
        Iscrizione iscrizione1 = new Iscrizione();
        Iscrizione iscrizione2 = new Iscrizione();
        List<Iscrizione> iscrizioni1 = new ArrayList<Iscrizione>();
        iscrizioni1.add(iscrizione1);
        iscrizioni1.add(iscrizione2);
        persona1.setIscrizioni(iscrizioni1);
        Persona persona2 = new Persona("a", "a", "a", "Basilicata", false, 1);
        persona2.setIscrizioni(new ArrayList<Iscrizione>());
        Persona persona3 = new Persona("a", "a", "a", "Basilicata", false, 1);
        List<Iscrizione> iscrizioni2 = new ArrayList<Iscrizione>();
        Iscrizione iscrizione3 = new Iscrizione();
        iscrizioni2.add(iscrizione3);
        persona3.setIscrizioni(iscrizioni2);
        Persona persona4 = new Persona("a", "a", "a", "Puglia", true, 1);
        persona4.setIscrizioni(iscrizioni1);
        List<Persona> persone = new ArrayList<>();
        persone.add(persona1);
        persone.add(persona2);
        persone.add(persona3);
        persone.add(persona4);
        List<Statistica> statistiche = operatore.calcolaStatistica(persone);
        assertEquals(statistiche.size(), 2);
        assertEquals(statistiche.get(0).getRegione(), "Basilicata");
        assertEquals(statistiche.get(0).getUomini(), 1);
        assertEquals(statistiche.get(0).getDonne(), 2);
        assertEquals(statistiche.get(0).getMediaRegionaleIscrizioni(), 1.0, 0.1);
        assertEquals(statistiche.get(1).getRegione(), "Puglia");
        assertEquals(statistiche.get(1).getUomini(), 1);
        assertEquals(statistiche.get(1).getDonne(), 0);
        assertEquals(statistiche.get(1).getMediaRegionaleIscrizioni(), 2.0, 0.1);
    }
}
