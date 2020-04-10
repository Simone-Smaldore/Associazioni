package it.unibas.associazioni.controllo;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
import it.unibas.associazioni.modello.Associazione;
import it.unibas.associazioni.modello.Iscrizione;
import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.persistenza.hibernate.DAOException;
import it.unibas.associazioni.persistenza.hibernate.DAOUtilHibernate;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import javax.swing.KeyStroke;

public class ControlloFinestraAssociazioni {

    private Action azioneEliminaIscrizione = new AzioneEliminaIscrizione();
    private Action azioneAggiungiIscrizione = new AzioneAggiungiIscrizione();

    public Action getAzioneEliminaIscrizione() {
        return azioneEliminaIscrizione;
    }

    public Action getAzioneAggiungiIscrizione() {
        return azioneAggiungiIscrizione;
    }

    private class AzioneAggiungiIscrizione extends AbstractAction {

        public AzioneAggiungiIscrizione() {
            this.putValue(NAME, "Iscrivi");
            this.putValue(SHORT_DESCRIPTION, "Iscrive la persona all'associazione selezionata");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl I"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_I);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar dataIscrizione = Applicazione.getInstance().getFinestraAssociazioni().getDataSelezionata();
            Associazione associazioneSelezionata = Applicazione.getInstance().getFinestraAssociazioni().getAssociazioneSelezionata();
            String errori = trovaErrori(dataIscrizione, associazioneSelezionata);
            if(!errori.isEmpty()) {
                Applicazione.getInstance().getFinestraAssociazioni().mostraErrori(errori);
                return;                
            }
            Persona persona = (Persona) Applicazione.getInstance().getModello().getBean(Costanti.PERSONA_SELEZIONATA);
            try {
                DAOUtilHibernate.beginTransaction();
                Applicazione.getInstance().getDaoPersona().makePersistent(persona);
                Iscrizione iscrizione = new Iscrizione();
                iscrizione.setDataIscrizione(dataIscrizione);
                iscrizione.setAssociazione(associazioneSelezionata);
                iscrizione.setPersona(persona);
                persona.addIscrizione(iscrizione);
                Applicazione.getInstance().getFinestraAssociazioni().aggiornaTabella();
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFinestraAssociazioni().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
            }
        }
        
        private String trovaErrori(Calendar dataIscrizione, Associazione associazioneScelta) {
            String errori = "";
            Persona persona = (Persona) Applicazione.getInstance().getModello().getBean(Costanti.PERSONA_SELEZIONATA);
            if(persona.isAssociazionePresente(associazioneScelta)) {
                errori += "Iscrizione all'associazione già effettuata\n";
            }            
            if(dataIscrizione.get(Calendar.YEAR) < associazioneScelta.getAnnoFondazione()) {
                errori += "In quella data l'associazione non era ancora stata fondata";
            }
            return errori;
        } 

    }

    private class AzioneEliminaIscrizione extends AbstractAction {

        public AzioneEliminaIscrizione() {
            this.putValue(NAME, "Elimina Iscrizione");
            this.putValue(SHORT_DESCRIPTION, "Elimina l' iscizione all' associazione selezionata");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_D);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int indice = Applicazione.getInstance().getFinestraAssociazioni().getIscrizioneSelezionata();
            if (indice == -1) {
                Applicazione.getInstance().getFinestraAssociazioni().mostraErrori("Selezionare un'iscrizione prima");
                return;
            }
            Persona persona = (Persona) Applicazione.getInstance().getModello().getBean(Costanti.PERSONA_SELEZIONATA);
            try {
                DAOUtilHibernate.beginTransaction();
                Applicazione.getInstance().getDaoPersona().makePersistent(persona);
                persona.removeIscrizioneConIndice(indice);
                Applicazione.getInstance().getFinestraAssociazioni().aggiornaTabella();
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFinestraAssociazioni().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
            }
        }

    }
}
