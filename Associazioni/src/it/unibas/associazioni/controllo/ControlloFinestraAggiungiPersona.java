package it.unibas.associazioni.controllo;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
import it.unibas.associazioni.modello.Iscrizione;
import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.persistenza.hibernate.DAOException;
import it.unibas.associazioni.persistenza.hibernate.DAOUtilHibernate;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import static javax.swing.Action.SHORT_DESCRIPTION;
import javax.swing.KeyStroke;

public class ControlloFinestraAggiungiPersona {

    private Action azioneAggiungiPersona = new AzioneAggiungiPersona();

    public Action getAzioneAggiungiPersona() {
        return azioneAggiungiPersona;
    }

    private class AzioneAggiungiPersona extends AbstractAction {

        public AzioneAggiungiPersona() {
            this.putValue(NAME, "Aggiungi persona");
            this.putValue(SHORT_DESCRIPTION, "Aggiunge una persona al database");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl A"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = Applicazione.getInstance().getFinestraAggiungiPersona().getCampoNome();
            String cognome = Applicazione.getInstance().getFinestraAggiungiPersona().getCampoCognome();
            String regione = Applicazione.getInstance().getFinestraAggiungiPersona().getCampoRegione();
            String campoEta = Applicazione.getInstance().getFinestraAggiungiPersona().getCampoEta();
            String codiceFiscale = Applicazione.getInstance().getFinestraAggiungiPersona().getCampoCodiceFiscale();
            boolean sesso = Applicazione.getInstance().getFinestraAggiungiPersona().getCampoSesso();
            String errori = trovaErrori(nome, cognome, regione, campoEta, codiceFiscale);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getFinestraAggiungiPersona().mostraErrori(errori);
                return;
            }
            Persona persona = new Persona(codiceFiscale, nome, cognome, regione, sesso, Integer.parseInt(campoEta));
            try {
                DAOUtilHibernate.beginTransaction();
                Applicazione.getInstance().getDaoPersona().makePersistent(persona);
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFinestraAggiungiPersona().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
                return;
            }
            if (cognome.equalsIgnoreCase(Applicazione.getInstance().getPannelloPrincipale().getCampoCognome())) {
                List<Persona> persone = (List<Persona>) Applicazione.getInstance().getModello().getBean(Costanti.PERSONE_CERCATE);
                persone.add(persona);
                Collections.sort(persone);
                Applicazione.getInstance().getPannelloPrincipale().aggiornaTabella();
            }
            Applicazione.getInstance().getFinestraAggiungiPersona().nascondi();
        }

        private String trovaErrori(String nome, String cognome, String regione, String campoEta, String codiceFiscale) {
            String errori = "";
            if (nome.isEmpty()) {
                errori += "Inserire un campo per il nome\n";
            }
            if (cognome.isEmpty()) {
                errori += "Inserire un campo per il cognome\n";
            }
            if (regione.isEmpty()) {
                errori += "Inserire un campo per la regione\n";
            }
            if (campoEta.isEmpty()) {
                errori += "Inserire un campo per l'eta\n";
            }
            if (nome.isEmpty()) {
                errori += "Inserire un campo per il codice fiscale\n";
            }
            try {
                int eta = Integer.parseInt(campoEta);
                if (eta < 0 || eta > 150) {
                    errori += "Inserire un valore accettabile per l'eta\n";
                }
            } catch (NumberFormatException ne) {
                errori += "Inserire un'eta valida\n";
            }
            try {
                DAOUtilHibernate.beginTransaction();
                List<Persona> persone = Applicazione.getInstance().getDaoPersona().cercaPerCodiceFiscale(codiceFiscale);
                if (persone.size() > 0) {
                    errori += "Esiste già una persona con questo codice fiscale\n";
                }
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                errori += "Errore con il DB riprovare\n";
            }
            return errori;
        }

    }
}
