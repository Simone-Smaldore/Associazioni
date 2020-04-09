package it.unibas.associazioni;

import it.unibas.associazioni.controllo.ControlloFinestraAssociazioni;
import it.unibas.associazioni.controllo.ControlloFinestraModifichePersona;
import it.unibas.associazioni.controllo.ControlloFrame;
import it.unibas.associazioni.controllo.ControlloPannelloPrincipale;
import it.unibas.associazioni.modello.Modello;
import it.unibas.associazioni.persistenza.DAOAssociazioneHibernate;
import it.unibas.associazioni.persistenza.DAOPersonaHibernate;
import it.unibas.associazioni.persistenza.IDAOAssociazione;
import it.unibas.associazioni.persistenza.IDAOPersona;
import it.unibas.associazioni.vista.FinestraAssociazioni;
import it.unibas.associazioni.vista.FinestraModifichePersona;
import it.unibas.associazioni.vista.FinestraStatistiche;
import it.unibas.associazioni.vista.Frame;
import it.unibas.associazioni.vista.PannelloPrincipale;
import javax.swing.SwingUtilities;

public class Applicazione {

    private static Applicazione singleton = new Applicazione();

    private Modello modello;

    private IDAOAssociazione daoAssociazione;
    private IDAOPersona daoPersona;

    private Frame frame;
    private PannelloPrincipale pannelloPrincipale;
    private FinestraAssociazioni finestraAssociazioni;
    private FinestraStatistiche finestraStatistiche;
    private FinestraModifichePersona finestraModifichePersona;

    private ControlloFinestraModifichePersona controlloFinestraModificaPersona;
    private ControlloPannelloPrincipale controlloPannelloPrincipale;
    private ControlloFrame controlloFrame;
    private ControlloFinestraAssociazioni controlloFinestraAssociazioni;

    private Applicazione() {

    }

    public static Applicazione getInstance() {
        return singleton;
    }

    public void inizializza() {
        this.modello = new Modello();

        this.daoAssociazione = new DAOAssociazioneHibernate();
        this.daoPersona = new DAOPersonaHibernate();

        this.controlloFrame = new ControlloFrame();
        this.controlloPannelloPrincipale = new ControlloPannelloPrincipale();
        this.controlloFinestraAssociazioni = new ControlloFinestraAssociazioni();
        this.controlloFinestraModificaPersona = new ControlloFinestraModifichePersona();

        this.pannelloPrincipale = new PannelloPrincipale();
        this.frame = new Frame();
        this.finestraAssociazioni = new FinestraAssociazioni(frame);
        this.finestraStatistiche = new FinestraStatistiche(frame);
        this.finestraModifichePersona = new FinestraModifichePersona(frame);

        this.finestraModifichePersona.inizializza();
        this.finestraStatistiche.inizializza();
        this.finestraAssociazioni.inizializza();
        this.pannelloPrincipale.inizializza();
        this.frame.inizializza();
    }

    public FinestraModifichePersona getFinestraModifichePersona() {
        return finestraModifichePersona;
    }

    public FinestraStatistiche getFinestraStatistiche() {
        return finestraStatistiche;
    }

    public ControlloFinestraModifichePersona getControlloFinestraModificaPersona() {
        return controlloFinestraModificaPersona;
    }

    public ControlloFinestraAssociazioni getControlloFinestraAssociazioni() {
        return controlloFinestraAssociazioni;
    }

    public FinestraAssociazioni getFinestraAssociazioni() {
        return finestraAssociazioni;
    }

    public PannelloPrincipale getPannelloPrincipale() {
        return pannelloPrincipale;
    }

    public ControlloPannelloPrincipale getControlloPannelloPrincipale() {
        return controlloPannelloPrincipale;
    }

    public ControlloFrame getControlloFrame() {
        return controlloFrame;
    }

    public Modello getModello() {
        return modello;
    }

    public IDAOAssociazione getDaoAssociazione() {
        return daoAssociazione;
    }

    public IDAOPersona getDaoPersona() {
        return daoPersona;
    }

    public Frame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Applicazione.getInstance().inizializza();
            }
        });
    }
}
