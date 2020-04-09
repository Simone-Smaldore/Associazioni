package it.unibas.associazioni.modello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperatoreStatistica {
    
    public List<Statistica> calcolaStatistica(List<Persona> persone) {
        Map<String, Statistica> mappaStatistiche = new HashMap<>();
        for (Persona persona : persone) {
            if(mappaStatistiche.get(persona.getRegione()) == null) {
                Statistica statistica = new Statistica(persona.getRegione(), 0, 0, 0);     
                mappaStatistiche.put(persona.getRegione(), statistica);
            }
            if(persona.isMaschio()) {
                mappaStatistiche.get(persona.getRegione()).incrementaUomini();
            } else {
                mappaStatistiche.get(persona.getRegione()).incrementaDonne();                
            }
            int iscrizioni = persona.getIscrizioni().size();
            mappaStatistiche.get(persona.getRegione()).incrementaIscrizioni(iscrizioni);
        }
        List<Statistica> statistiche = new ArrayList<>(mappaStatistiche.values());
        Collections.sort(statistiche);
        return statistiche;
    }
}
