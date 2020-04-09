package it.unibas.associazioni.controllo;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
import it.unibas.associazioni.modello.Persona;
import it.unibas.associazioni.persistenza.hibernate.DAOException;
import it.unibas.associazioni.persistenza.hibernate.DAOUtilHibernate;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public class ControlloFinestraModificaPersona {

    private Action azioneModifica = new AzioneModifica();

    public Action getAzioneModifica() {
        return azioneModifica;
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
            String nome = Applicazione.getInstance().getFinestraModificaPersona().getCampoNome();
            String cognome = Applicazione.getInstance().getFinestraModificaPersona().getCampoCognome();
            String regione = Applicazione.getInstance().getFinestraModificaPersona().getCampoRegione();
            String campoEta = Applicazione.getInstance().getFinestraModificaPersona().getCampoEta();
            boolean sesso = Applicazione.getInstance().getFinestraModificaPersona().getCampoSesso();        
            Persona personaSelezionata = (Persona) Applicazione.getInstance().getModello().getBean(Costanti.PERSONA_SELEZIONATA);
            String errori = trovaErrori(nome, cognome, regione, campoEta);
            if (!errori.isEmpty()) {
                Applicazione.getInstance().getFinestraModificaPersona().mostraErrori(errori);
                return;
            }          
            String vecchioCognome = personaSelezionata.getCognome();
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
                Applicazione.getInstance().getFinestraAggiungiPersona().mostraErrori("Impossibile accedere al DB " + ex.getMessage());
                return;                               
            }
            if (vecchioCognome.equals(Applicazione.getInstance().getPannelloPrincipale().getCampoCognome())) {
                Applicazione.getInstance().getControlloPannelloPrincipale().getAzioneCerca().actionPerformed(e);
            }
            Applicazione.getInstance().getFinestraModificaPersona().nascondi();            
        }

        private String trovaErrori(String nome, String cognome, String regione, String campoEta) {
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
}
