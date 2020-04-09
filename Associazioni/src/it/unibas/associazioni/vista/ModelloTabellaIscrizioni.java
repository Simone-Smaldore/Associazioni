package it.unibas.associazioni.vista;

import it.unibas.associazioni.modello.Iscrizione;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelloTabellaIscrizioni extends AbstractTableModel {

    private List<Iscrizione> iscrizioni;

    public void setIscrizioni(List<Iscrizione> iscrizioni) {
        this.iscrizioni = iscrizioni;
    }

    @Override
    public int getRowCount() {
        if (iscrizioni == null) {
            return 0;
        }
        return iscrizioni.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (iscrizioni == null) {
            return "";
        }
        Iscrizione iscrizione = iscrizioni.get(rowIndex);
        if (columnIndex == 0) {
            return iscrizione.getAssociazione().getNome();
        }
        if (columnIndex == 1) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(iscrizione.getDataIscrizione().getTime());
        }
        return "";
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Associazione";
        }
        if (column == 1) {
            return "Data Iscrizione";
        }
        return "";
    }

    public void aggiorna() {
        this.fireTableDataChanged();
    }

}
