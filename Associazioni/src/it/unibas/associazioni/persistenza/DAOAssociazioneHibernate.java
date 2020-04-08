package it.unibas.associazioni.persistenza;

import it.unibas.associazioni.modello.Associazione;
import it.unibas.associazioni.persistenza.hibernate.DAOGenericoHibernate;

public class DAOAssociazioneHibernate extends DAOGenericoHibernate<Associazione> implements IDAOAssociazione{
    
    public DAOAssociazioneHibernate() {
        super(Associazione.class);
    }
    
}
