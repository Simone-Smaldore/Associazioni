package it.unibas.associazioni.persistenza;

import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.persistenza.hibernate.DAOException;
import it.unibas.associazioni.persistenza.hibernate.DAOGenericoHibernate;
import it.unibas.associazioni.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class DAOPersonaHibernate extends DAOGenericoHibernate<Persona> implements IDAOPersona{

    public DAOPersonaHibernate() {
        super(Persona.class);
    }

    @Override
    public List<Persona> cercaPerCognome(String cognome) throws DAOException {
        Criteria criterio = getSession().createCriteria(Persona.class);
        criterio.add(Restrictions.eq("cognome", cognome).ignoreCase());
        criterio.addOrder(Order.asc("eta"));
        return criterio.list();
    }

    @Override
    public List<Persona> cercaPerCodiceFiscale(String codiceFiscale) throws DAOException {
        Criteria criterio = getSession().createCriteria(Persona.class);
        criterio.add(Restrictions.eq("codiceFiscale", codiceFiscale).ignoreCase());
        return criterio.list();        
    }
    
}
