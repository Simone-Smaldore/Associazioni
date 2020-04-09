package it.unibas.associazioni.vista;

import it.unibas.associazioni.Applicazione;
import it.unibas.associazioni.Costanti;
import it.unibas.associazioni.modello.Persona;
import java.util.List;

public class PannelloPrincipale extends javax.swing.JPanel {

    public PannelloPrincipale() {
    }

        
    public void inizializza() {
        initComponents();
        initActions();
        initTable();
    }
    
    public void initActions() {
        this.jButtonCerca.setAction(Applicazione.getInstance().getControlloPannelloPrincipale().getAzioneCerca());
        this.jTextFieldCognome.setAction(Applicazione.getInstance().getControlloPannelloPrincipale().getAzioneCerca());
        this.jButtonMostraIscrizioni.setAction(Applicazione.getInstance().getControlloPannelloPrincipale().getAzioneMostraAssociazioni());
        this.jButtonAggiungi.setAction(Applicazione.getInstance().getControlloPannelloPrincipale().getAzioneMostraAggiungiPersona());
        this.jButtonModifica.setAction(Applicazione.getInstance().getControlloPannelloPrincipale().getAzioneApriFinestraModifica());
    }
    
    public void initTable() {
        this.jTablePersone.setModel(new ModelloTabellaPersone());
    }
    
    public int getPersonaSelezionata() {
        return this.jTablePersone.getSelectedRow();
    }
    
    public void aggiornaTabella() {
        ModelloTabellaPersone modello = (ModelloTabellaPersone) this.jTablePersone.getModel();
        List<Persona> persone = (List<Persona>) Applicazione.getInstance().getModello().getBean(Costanti.PERSONE_CERCATE);
        modello.setPersone(persone);
        modello.aggiorna();
    }
    
    public String getCampoCognome() {
        return this.jTextFieldCognome.getText();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        jTextFieldCognome = new javax.swing.JTextField();
        jButtonCerca = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        jTablePersone = new javax.swing.JTable();
        jButtonMostraIscrizioni = new javax.swing.JButton();
        jButtonModifica = new javax.swing.JButton();
        jButtonAggiungi = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cerca per cognome", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Cognome");

        jButtonCerca.setText("jButton1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonCerca, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCerca))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTablePersone.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTablePersone);

        jButtonMostraIscrizioni.setText("jButton1");

        jButtonModifica.setText("jButton1");

        jButtonAggiungi.setText("jButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAggiungi, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonModifica, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMostraIscrizioni, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMostraIscrizioni)
                    .addComponent(jButtonModifica)
                    .addComponent(jButtonAggiungi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAggiungi;
    private javax.swing.JButton jButtonCerca;
    private javax.swing.JButton jButtonModifica;
    private javax.swing.JButton jButtonMostraIscrizioni;
    private javax.swing.JTable jTablePersone;
    private javax.swing.JTextField jTextFieldCognome;
    // End of variables declaration//GEN-END:variables
}
