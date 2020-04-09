package it.unibas.associazioni.vista;

import it.unibas.associazioni.modello.Persona;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelloTabellaPersone extends AbstractTableModel {

    private List<Persona> persone;

    public void setPersone(List<Persona> persone) {
        this.persone = persone;
    }

    @Override
    public int getRowCount() {
        if (persone == null) {
            return 0;
        }
        return persone.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (persone == null) {
            return "";
        }
        Persona persona = persone.get(rowIndex);
        if (columnIndex == 0) {
            return persona.getCognome();
        }
        if (columnIndex == 1) {
            return persona.getNome();
        }
        if (columnIndex == 2) {
            return persona.getEta();
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Cognome";
        }
        if (column == 1) {
            return "Nome";
        }
        if (column == 2) {
            return "Eta";
        }
        return "";
    }

    public void aggiorna() {
        this.fireTableDataChanged();
    }

}
