package it.unibas.associazioni.persistenza;

import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.persistenza.hibernate.DAOException;
import it.unibas.associazioni.persistenza.hibernate.IDAOGenerico;
import java.util.List;

public interface IDAOPersona extends IDAOGenerico<Persona> {
    
    public List<Persona> cercaPerCognome(String cognome)throws DAOException;
}
