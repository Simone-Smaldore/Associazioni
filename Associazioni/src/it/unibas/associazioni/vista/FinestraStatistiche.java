package it.unibas.associazioni.vista;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
import it.unibas.associazioni.modello.Statistica;
import java.util.List;

public class FinestraStatistiche extends javax.swing.JDialog {

    public FinestraStatistiche(Frame parent) {
        super(parent, true);
    }
    
        
    public void inizializza() {
        initComponents();
        initTabella();
    }

    public void visualizza() {
        aggiornaTabella();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void initTabella() {
        this.jTableStatistiche.setModel(new ModelloTabellaStatistiche());
    }
    
    public void aggiornaTabella() {
        ModelloTabellaStatistiche modello = (ModelloTabellaStatistiche) this.jTableStatistiche.getModel();
        List<Statistica> statistiche = (List<Statistica>) Applicazione.getInstance().getModello().getBean(Costanti.STATISTICHE);
        modello.setStatistiche(statistiche);
        modello.aggiorna();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jTableStatistiche = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabella Statistiche", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTableStatistiche.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableStatistiche);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable jTableStatistiche;
    // End of variables declaration//GEN-END:variables
}
