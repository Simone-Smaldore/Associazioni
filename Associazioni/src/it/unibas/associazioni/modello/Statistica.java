package it.unibas.associazioni.modello;

public class Statistica implements Comparable<Statistica> {

    private String regione;
    private int uomini;
    private int donne;
    private int iscrizioni;

    public Statistica(String regione, int uomini, int donne, int iscrizioni) {
        this.regione = regione;
        this.uomini = uomini;
        this.donne = donne;
        this.iscrizioni = iscrizioni;
    }

    public String getRegione() {
        return regione;
    }

    public int getUomini() {
        return uomini;
    }

    public int getDonne() {
        return donne;
    }

    public int getIscrizioni() {
        return iscrizioni;
    }

    public void incrementaUomini() {
        this.uomini++;
    }

    public void incrementaDonne() {
        this.donne++;
    }

    public void incrementaIscrizioni(int numIscrizioni) {
        this.iscrizioni += numIscrizioni;
    }

    public double getMediaRegionaleIscrizioni() {
        return (double) iscrizioni / (uomini + donne);
    }

    @Override
    public int compareTo(Statistica o) {
        return this.regione.compareTo(o.regione);
    }

}
