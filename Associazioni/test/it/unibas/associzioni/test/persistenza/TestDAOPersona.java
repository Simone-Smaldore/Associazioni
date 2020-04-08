package it.unibas.associzioni.test.persistenza;

import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.persistenza.DAOPersonaHibernate;
import it.unibas.associazioni.persistenza.IDAOPersona;
import it.unibas.associazioni.persistenza.hibernate.DAOException;
import it.unibas.associazioni.persistenza.hibernate.DAOUtilHibernate;
import java.util.List;
import junit.framework.TestCase;

public class TestDAOPersona extends TestCase {

    public void testTrovaPerCognome() {
        IDAOPersona daoPersona = new DAOPersonaHibernate();
        Persona persona1 = new Persona("codiceProva1", "a", "Prova1xx", "a", true, 1);
        Persona persona2 = new Persona("codiceProva2", "a", "Prova1xx", "a", true, 2);
        Persona persona3 = new Persona("codiceProva3", "a", "Prova2xx", "a", true, 2);
        try {
            DAOUtilHibernate.beginTransaction();
            daoPersona.makePersistent(persona1);
            daoPersona.makePersistent(persona2);
            daoPersona.makePersistent(persona3);
            List<Persona> lista1 = daoPersona.cercaPerCognome("Prova1xx");
            List<Persona> lista2 = daoPersona.cercaPerCognome("Prova2xx");
            assertEquals(lista1.size(), 2);
            assertEquals(lista2.size(), 1);
            assertEquals(lista1.get(0).getCodiceFiscale(), "codiceProva1");
            assertEquals(lista1.get(1).getCodiceFiscale(), "codiceProva2");
            daoPersona.makeTransient(persona1);
            daoPersona.makeTransient(persona2);
            daoPersona.makeTransient(persona3);
            lista1 = daoPersona.cercaPerCognome("Prova1xx");
            lista2 = daoPersona.cercaPerCognome("Prova2xx");
            assertEquals(lista1.size(), 0);
            assertEquals(lista2.size(), 0);
            DAOUtilHibernate.commit();
        } catch (DAOException ex) {
            DAOUtilHibernate.rollback();
            fail();
        }
    }
}
