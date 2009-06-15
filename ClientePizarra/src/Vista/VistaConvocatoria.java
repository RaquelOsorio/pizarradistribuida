package Vista;

import Controlador.CConvocatoria;
import VO.VOConvocatoria;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.jdesktop.application.Action;

public class VistaConvocatoria extends javax.swing.JFrame {
    CConvocatoria control;
    private TConv tabla;

    public VistaConvocatoria() {
        initComponents();
    }

    public VistaConvocatoria(CConvocatoria aThis) {
        control = aThis;
        initComponents();
        tabla = new TConv();
        this.setTablaConvocatoria();
        this.setVisible(true);
    }

    public JTable getTabla() {
        return jTable1;
    }

    @Action
    public void cerrar() throws RemoteException {
        control.MataVista();
    }
    @Action
    public void IngresarConvocatoriaPresionado() throws RemoteException {
        control.nuevaConvocatoriaVista();
    }
    @Action
    public void VerConvocatoriaPresionado() throws RemoteException {
        if(this.jTable1.getSelectedRow()>=0)
            control.VerConvocatoriaVista(Integer.parseInt(this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 3).toString()));
        else
            this.VentanaFeedback("Debes Seleccionar una convocatoria primero", "Ver Convocatoria");
    }
    @Action
    public void postular() throws RemoteException {
        if(this.jTable1.getSelectedRow()>=0)
            control.PostularVista(Integer.parseInt(this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 3).toString()));
        else
            this.VentanaFeedback("Debes Seleccionar una convocatoria primero", "Postular");
    }
    
    public void VentanaFeedback(String m, String t) {
        JOptionPane.showMessageDialog(this, m, t, JOptionPane.INFORMATION_MESSAGE);
    }





//2 Convocatoria-2 22-05-2009 Esta-es-la-descripcion-de-la-convocatoria-2
    public void setTablaConvocatoria() {
        String[] campos = {"Nombre Convocatoria", "Fecha", "Descripción", "ID"};
        this.jTable1.setModel(tabla);
        for (int i = 0; i < campos.length; i++) {
            tabla.addColumn(campos[i]);
        }
        //jTable1.getColumnModel().removeColumn(jTable1.getColumnModel().getColumn(3));
//        this.jTable1.getColumn("ID").setMaxWidth(0);
//        this.jTable1.getColumn("ID").setMinWidth(0);
//        this.jTable1.getColumn("ID").setPreferredWidth(0);
    }

    public void addConvocatorias(Vector us) {
        Object[][] info = new Object[us.size()][4];
        for (int i = 0; i < us.size(); i++) {
            for (int j = 0; j < 4; j++) {
                switch (j) {
                    case 0:
                        info[i][j] = ((VOConvocatoria) us.get(i)).getTitulo();
                        break;
                    case 1:
                        info[i][j] = ((VOConvocatoria) us.get(i)).getFecha();
                        break;
                    case 2:
                        info[i][j] = ((VOConvocatoria) us.get(i)).getDescripcion();
                        break;
                    case 3:
                        info[i][j] = ((VOConvocatoria) us.get(i)).getID();
                        break;
                }
            }
            tabla.addRow(info[i]);
        }
    }

    public JButton getBAgregarConvocatoria() {
        return BAgregarConvocatoria;
    }

    public JButton getBModificarConvocatoria() {
        return BModificarConvocatoria;
    }

    public JButton getBEliminarConvocatoria() {
        return BEliminarConvocatoria;
    }

    public JButton getBVerConvocatoria() {
        return BVerConvocatoria;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        BVerConvocatoria = new javax.swing.JButton();
        BEliminarConvocatoria = new javax.swing.JButton();
        BModificarConvocatoria = new javax.swing.JButton();
        BAgregarConvocatoria = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        estado = new javax.swing.JLabel();
        BPostular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getResourceMap(VistaConvocatoria.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel7.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jLabel7.setName("jLabel7"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Convocatoria", "Fecha", "Descripción", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(909, 909, 909)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(119, 119, 119))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getActionMap(VistaConvocatoria.class, this);
        BVerConvocatoria.setAction(actionMap.get("VerConvocatoriaPresionado")); // NOI18N
        BVerConvocatoria.setText(resourceMap.getString("BVerConvocatoria.text")); // NOI18N
        BVerConvocatoria.setName("BVerConvocatoria"); // NOI18N

        BEliminarConvocatoria.setText(resourceMap.getString("BEliminarConvocatoria.text")); // NOI18N
        BEliminarConvocatoria.setName("BEliminarConvocatoria"); // NOI18N

        BModificarConvocatoria.setText(resourceMap.getString("BModificarConvocatoria.text")); // NOI18N
        BModificarConvocatoria.setName("BModificarConvocatoria"); // NOI18N

        BAgregarConvocatoria.setText(resourceMap.getString("BAgregarConvocatoria.text")); // NOI18N
        BAgregarConvocatoria.setName("BAgregarConvocatoria"); // NOI18N
        BAgregarConvocatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarConvocatoriaActionPerformed(evt);
            }
        });

        jButton4.setAction(actionMap.get("cerrar")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        estado.setText(resourceMap.getString("estado.text")); // NOI18N
        estado.setName("estado"); // NOI18N

        BPostular.setAction(actionMap.get("postular")); // NOI18N
        BPostular.setText(resourceMap.getString("BPostular.text")); // NOI18N
        BPostular.setName("BPostular"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, 0, 558, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BAgregarConvocatoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BModificarConvocatoria))
                            .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BPostular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BEliminarConvocatoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BVerConvocatoria, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(230, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(202, 202, 202))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BVerConvocatoria, jButton4});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BEliminarConvocatoria, BPostular});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BVerConvocatoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BModificarConvocatoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BAgregarConvocatoria, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(BEliminarConvocatoria, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(estado)
                    .addComponent(BPostular, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BAgregarConvocatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarConvocatoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BAgregarConvocatoriaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new VistaConvocatoria().setVisible(true);
            }
        });
    }

    public JLabel getEstado() {
        return estado;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAgregarConvocatoria;
    private javax.swing.JButton BEliminarConvocatoria;
    private javax.swing.JButton BModificarConvocatoria;
    private javax.swing.JButton BPostular;
    private javax.swing.JButton BVerConvocatoria;
    private javax.swing.JLabel estado;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
