package it.unibas.associazioni.vista;

import it.unibas.associazioni.modello.Statistica;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelloTabellaStatistiche extends AbstractTableModel {

    private List<Statistica> statistiche;

    public void setStatistiche(List<Statistica> statistiche) {
        this.statistiche = statistiche;
    }

    @Override
    public int getRowCount() {
        if(statistiche == null) {
            return 0;
        }
        return statistiche.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(statistiche == null) {
            return "";
        }
        Statistica statistica = statistiche.get(rowIndex);
        if(columnIndex == 0) {
            return statistica.getRegione();
        }
        if(columnIndex == 1) {
            return statistica.getUomini();
        }
        if(columnIndex == 2) {
            return statistica.getDonne();
        }
        if(columnIndex == 3) {
            return statistica.getMediaRegionaleIscrizioni();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0) {
            return "Regione";
        }
        if(column == 1) {
            return "Uomini";
        }
        if(column == 2) {
            return "Donne";
        }
        if(column == 3) {
            return "Media Iscrizioni";
        }
        return "";
    }
    
    public void aggiorna() {
        this.fireTableDataChanged();
    }

}
