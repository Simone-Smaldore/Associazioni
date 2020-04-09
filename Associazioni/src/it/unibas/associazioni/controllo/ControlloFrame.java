package it.unibas.associazioni.controllo;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
import it.unibas.associazioni.modello.OperatoreStatistica;
import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.modello.Statistica;
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

public class ControlloFrame {

    private Action azioneEsci = new AzioneEsci();
    private Action azioneCalcolaStatistiche = new AzioneCalcolaStatistiche();

    public Action getAzioneCalcolaStatistiche() {
        return azioneCalcolaStatistiche;
    }

    public Action getAzioneEsci() {
        return azioneEsci;
    }

    private class AzioneCalcolaStatistiche extends AbstractAction {

        public AzioneCalcolaStatistiche() {
            this.putValue(NAME, "Calcola Statistiche");
            this.putValue(SHORT_DESCRIPTION, "Calcola le statistiche per ogni regione");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_S);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            OperatoreStatistica operatore = new OperatoreStatistica();
            try {
                DAOUtilHibernate.beginTransaction();
                List<Persona> persone = Applicazione.getInstance().getDaoPersona().findAll();
                List<Statistica> statistiche = operatore.calcolaStatistica(persone);
                Applicazione.getInstance().getModello().putBean(Costanti.STATISTICHE, statistiche);
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFrame().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
                return;
            }
            Applicazione.getInstance().getFinestraStatistiche().visualizza();
        }

    }

    private class AzioneEsci extends AbstractAction {

        public AzioneEsci() {
            this.putValue(NAME, "Esci");
            this.putValue(SHORT_DESCRIPTION, "Esci dall'applicazione");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl E"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_E);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }
}
