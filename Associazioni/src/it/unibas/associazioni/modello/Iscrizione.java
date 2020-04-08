package it.unibas.associazioni.modello;

import java.util.Calendar;
import javax.persistence.*;

@Entity
public class Iscrizione {

    private long id;
    private Calendar dataIscrizione;
    private Associazione associazione;
    private Persona persona;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Temporal(value = TemporalType.DATE)
    public Calendar getDataIscrizione() {
        return dataIscrizione;
    }

    public void setDataIscrizione(Calendar dataIscrizione) {
        this.dataIscrizione = dataIscrizione;
    }

    @ManyToOne()
    public Associazione getAssociazione() {
        return associazione;
    }

    public void setAssociazione(Associazione associazione) {
        this.associazione = associazione;
    }
    
    @ManyToOne()
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
