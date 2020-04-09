package it.unibas.associazioni.controllo;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
import it.unibas.associazioni.modello.Associazione;
import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.persistenza.hibernate.DAOException;
import it.unibas.associazioni.persistenza.hibernate.DAOUtilHibernate;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import javax.swing.KeyStroke;

public class ControlloPannelloPrincipale {

    private Action azioneCerca = new AzioneCerca();
    private Action azioneMostraAssociazioni = new AzioneMostraAssociazioni();
    private Action azioneMostraAggiungiPersona = new AzioneMostraAggiungiPersona();
    private Action azioneApriFinestraModifica = new AzioneApriFinestraModifica();

    public Action getAzioneCerca() {
        return azioneCerca;
    }

    public Action getAzioneMostraAssociazioni() {
        return azioneMostraAssociazioni;
    }

    public Action getAzioneMostraAggiungiPersona() {
        return azioneMostraAggiungiPersona;
    }

    public Action getAzioneApriFinestraModifica() {
        return azioneApriFinestraModifica;
    }

    private class AzioneApriFinestraModifica extends AbstractAction {

        public AzioneApriFinestraModifica() {
            this.putValue(NAME, "Modifica");
            this.putValue(SHORT_DESCRIPTION, "Apre una finestra per la modifica della persona");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Y"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_Y);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int indice = Applicazione.getInstance().getPannelloPrincipale().getPersonaSelezionata();
            if (indice == -1) {
                Applicazione.getInstance().getFrame().mostraErrori("Selezionare una persona prima");
                return;
            }
            List<Persona> persone = (List<Persona>) Applicazione.getInstance().getModello().getBean(Costanti.PERSONE_CERCATE);
            Persona personaScelta = persone.get(indice);
            Applicazione.getInstance().getModello().putBean(Costanti.PERSONA_SELEZIONATA, personaScelta);
            Applicazione.getInstance().getFinestraModificaPersona().visualizza();
        }

    }

    private class AzioneMostraAggiungiPersona extends AbstractAction {

        public AzioneMostraAggiungiPersona() {
            this.putValue(NAME, "Aggiungi persona");
            this.putValue(SHORT_DESCRIPTION, "Apre una finestra per aggiungere una nuova persona");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_O);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Applicazione.getInstance().getFinestraAggiungiPersona().visualizza();
        }

    }

    private class AzioneMostraAssociazioni extends AbstractAction {

        public AzioneMostraAssociazioni() {
            this.putValue(NAME, "Mostra Associazioni");
            this.putValue(SHORT_DESCRIPTION, "Mostra le associazioni a cui è iscritta la persona selezionata");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl M"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_M);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int indice = Applicazione.getInstance().getPannelloPrincipale().getPersonaSelezionata();
            if (indice == -1) {
                Applicazione.getInstance().getFrame().mostraErrori("Selezionare una persona prima");
                return;
            }
            List<Persona> persone = (List<Persona>) Applicazione.getInstance().getModello().getBean(Costanti.PERSONE_CERCATE);
            Persona personaSelezionata = persone.get(indice);
            Applicazione.getInstance().getModello().putBean(Costanti.PERSONA_SELEZIONATA, personaSelezionata);
            try {
                DAOUtilHibernate.beginTransaction();
                Applicazione.getInstance().getDaoPersona().makePersistent(personaSelezionata);
                List<Associazione> associazioni = Applicazione.getInstance().getDaoAssociazione().findAll();
                Applicazione.getInstance().getModello().putBean(Costanti.ASSOCIAZIONI, associazioni);
                Applicazione.getInstance().getFinestraAssociazioni().visualizza();
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFrame().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
            }
        }

    }

    private class AzioneCerca extends AbstractAction {

        public AzioneCerca() {
            this.putValue(NAME, "Cerca");
            this.putValue(SHORT_DESCRIPTION, "Cerca persone con il cognome inserito");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl C"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_C);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String cognome = Applicazione.getInstance().getPannelloPrincipale().getCampoCognome();
            if (cognome.isEmpty()) {
                Applicazione.getInstance().getFrame().mostraErrori("Inserire un cognome prima");
                return;
            }
            try {
                DAOUtilHibernate.beginTransaction();
                List<Persona> persone = Applicazione.getInstance().getDaoPersona().cercaPerCognome(cognome);
                Applicazione.getInstance().getModello().putBean(Costanti.PERSONE_CERCATE, persone);
                Applicazione.getInstance().getPannelloPrincipale().aggiornaTabella();
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFrame().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
            }

        }

    }
}
