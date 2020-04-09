package it.unibas.associazioni.modello;

import java.util.List;
import javax.persistence.*;

@Entity
public class Associazione {

    private long id;
    private String codice;
    private String nome;
    private int annoFondazione;
    private List<Iscrizione> iscrizioni;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnnoFondazione() {
        return annoFondazione;
    }

    public void setAnnoFondazione(int annoFondazione) {
        this.annoFondazione = annoFondazione;
    }
    
    @OneToMany(mappedBy = "associazione", cascade = CascadeType.ALL)
    public List<Iscrizione> getIscrizioni() {
        return iscrizioni;
    }

    public void setIscrizioni(List<Iscrizione> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }

    @Override
    public String toString() {
        return nome;
    }

    
    
}
