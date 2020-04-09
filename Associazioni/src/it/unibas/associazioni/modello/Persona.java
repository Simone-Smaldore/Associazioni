package it.unibas.associazioni.modello;

import java.util.List;
import javax.persistence.*;

@Entity
public class Persona implements Comparable<Persona> {
    
    private long id;
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String regione;
    private boolean maschio;
    private int eta;
    private List<Iscrizione> iscrizioni;
    
    public Persona() {
    }
    
    public Persona(String codiceFiscale, String nome, String cognome, String regione, boolean maschio, int eta) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.regione = regione;
        this.maschio = maschio;
        this.eta = eta;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(unique = true)
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    public String getRegione() {
        return regione;
    }
    
    public void setRegione(String regione) {
        this.regione = regione;
    }
    
    public boolean isMaschio() {
        return maschio;
    }
    
    public void setMaschio(boolean maschio) {
        this.maschio = maschio;
    }
    
    public int getEta() {
        return eta;
    }
    
    public void setEta(int eta) {
        this.eta = eta;
    }
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Iscrizione> getIscrizioni() {
        return iscrizioni;
    }
    
    public void setIscrizioni(List<Iscrizione> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }
    
    public void addIscrizione(Iscrizione iscrizione) {
        this.iscrizioni.add(iscrizione);
    }

    @Override
    public int compareTo(Persona o) {
        return eta - o.eta;
    }
    
    
}
