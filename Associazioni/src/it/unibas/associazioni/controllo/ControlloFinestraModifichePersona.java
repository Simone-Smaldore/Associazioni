package it.unibas.associazioni.controllo;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
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

public class ControlloFinestraModifichePersona {

    private Action azioneModifica = new AzioneModifica();
    private Action azioneAggiungiPersona = new AzioneAggiungiPersona();

    public Action getAzioneModifica() {
        return azioneModifica;
    }

    public Action getAzioneAggiungiPersona() {
        return azioneAggiungiPersona;
    }

    private class AzioneAggiungiPersona extends AbstractAction {

        public AzioneAggiungiPersona() {
            this.putValue(NAME, "Salva aggiunta");
            this.putValue(SHORT_DESCRIPTION, "Aggiunge una persona al database");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl A"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_A);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = Applicazione.getInstance().getFinestraModifichePersona().getCampoNome();
            String cognome = Applicazione.getInstance().getFinestraModifichePersona().getCampoCognome();
            String regione = Applicazione.getInstance().getFinestraModifichePersona().getCampoRegione();
            String campoEta = Applicazione.getInstance().getFinestraModifichePersona().getCampoEta();
            String codiceFiscale = Applicazione.getInstance().getFinestraModifichePersona().getCampoCodiceFiscale();
            boolean sesso = Applicazione.getInstance().getFinestraModifichePersona().getCampoSesso();
            String errori = trovaErrori(nome, cognome, regione, campoEta, codiceFiscale);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getFinestraModifichePersona().mostraErrori(errori);
                return;
            }
            Persona persona = new Persona(codiceFiscale, nome, cognome, regione, sesso, Integer.parseInt(campoEta));
            try {
                DAOUtilHibernate.beginTransaction();
                Applicazione.getInstance().getDaoPersona().makePersistent(persona);
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFinestraModifichePersona().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
                return;
            }
            if (cognome.equalsIgnoreCase(Applicazione.getInstance().getPannelloPrincipale().getCampoCognome())) {

// CODICE COMMENTATO DA NULL POINTER EXCEPTION SE VADO AD AGGIUNGERE UN ISCRIZIONE ALLA PERSONA APPENA CREATA
//                List<Persona> persone = (List<Persona>) Applicazione.getInstance().getModello().getBean(Costanti.PERSONE_CERCATE);
//                persone.add(persona);
//                Collections.sort(persone);
//                Applicazione.getInstance().getPannelloPrincipale().aggiornaTabella();
                Applicazione.getInstance().getControlloPannelloPrincipale().getAzioneCerca().actionPerformed(e);
            }
            Applicazione.getInstance().getFinestraModifichePersona().nascondi();
        }


    }

    private class AzioneModifica extends AbstractAction {

        public AzioneModifica() {
            this.putValue(NAME, "Salva modifiche");
            this.putValue(SHORT_DESCRIPTION, "Modifica la persona selezionata nella tabella");
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl M"));
            this.putValue(MNEMONIC_KEY, KeyEvent.VK_M);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = Applicazione.getInstance().getFinestraModifichePersona().getCampoNome();
            String cognome = Applicazione.getInstance().getFinestraModifichePersona().getCampoCognome();
            String regione = Applicazione.getInstance().getFinestraModifichePersona().getCampoRegione();
            String campoEta = Applicazione.getInstance().getFinestraModifichePersona().getCampoEta();
            boolean sesso = Applicazione.getInstance().getFinestraModifichePersona().getCampoSesso();
            Persona personaSelezionata = (Persona) Applicazione.getInstance().getModello().getBean(Costanti.PERSONA_SELEZIONATA);
            String errori = trovaErroriCampi(nome, cognome, regione, campoEta);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getFinestraModifichePersona().mostraErrori(errori);
                return;
            }
            personaSelezionata.setNome(nome);
            personaSelezionata.setCognome(cognome);
            personaSelezionata.setRegione(regione);
            personaSelezionata.setEta(Integer.parseInt(campoEta));
            personaSelezionata.setMaschio(sesso);
            try {
                DAOUtilHibernate.beginTransaction();
                Applicazione.getInstance().getDaoPersona().makePersistent(personaSelezionata);
                DAOUtilHibernate.commit();
            } catch (DAOException ex) {
                DAOUtilHibernate.rollback();
                Applicazione.getInstance().getFinestraModifichePersona().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
                return;
            }
            List<Persona> persone = (List<Persona>) Applicazione.getInstance().getModello().getBean(Costanti.PERSONE_CERCATE);
            Collections.sort(persone);
            Applicazione.getInstance().getPannelloPrincipale().aggiornaTabella();
            Applicazione.getInstance().getFinestraModifichePersona().nascondi();
        }

    }
    
    private String trovaErrori(String nome, String cognome, String regione, String campoEta, String codiceFiscale) {
        return trovaErroriCampi(nome, cognome, regione, campoEta) + trovaErroriCodicefiscale(codiceFiscale);
    }
    

    private String trovaErroriCodicefiscale(String codiceFiscale) {
        String errori = "";
        if (codiceFiscale.isEmpty()) {
            errori += "Inserire un campo per il codice fiscale\n";
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

    private String trovaErroriCampi(String nome, String cognome, String regione, String campoEta) {
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
        try {
            int eta = Integer.parseInt(campoEta);
            if (eta < 0 || eta > 150) {
                errori += "Inserire un valore accettabile per l'eta\n";
            }
        } catch (NumberFormatException ne) {
            errori += "Inserire un'eta valida\n";
        }
        return errori;
    }
}
