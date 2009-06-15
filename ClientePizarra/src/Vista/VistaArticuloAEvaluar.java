package Vista;

import Controlador.CArticuloAEvaluar;
import Controlador.CListaPostulaciones;
import VO.VOPostulacion;
import java.io.File;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

public class VistaArticuloAEvaluar extends javax.swing.JFrame {

    CArticuloAEvaluar control;
    TArtPost tabla;
    public JFileChooser fc;

    public VistaArticuloAEvaluar(CArticuloAEvaluar c) {
        initComponents();
        control = c;
        tabla = new TArtPost();
        fc = new JFileChooser();
        this.setTablaArticulos();
        this.setVisible(true);
    }

    public VistaArticuloAEvaluar(CListaPostulaciones aThis) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Artículo", "Autor", "Estado"
            }
        ));
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getResourceMap(VistaArticuloAEvaluar.class);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getActionMap(VistaArticuloAEvaluar.class, this);
        jButton1.setAction(actionMap.get("detalleArticulo")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jButton3.setAction(actionMap.get("evaluarArticulo")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        jButton4.setAction(actionMap.get("descargarArticulo")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(144, 144, 144))
        );

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jButton2.setAction(actionMap.get("cerrar")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setTablaArticulos() {

        String[] campos = {"Articulo", "Autor", "Estado"};
        this.jTable1.setModel(tabla);
        for (int i = 0; i < campos.length; i++) {
            tabla.addColumn(campos[i]);
        }
    }
  
        
    public void addArticulosPostulados(Vector us) {

        Object[][] info = new Object[us.size()][3];

        for (int i = 0; i < us.size(); i++) {
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        info[i][j] = ((VOPostulacion) us.get(i)).getTitulo();

                        break;
                    case 1:
                        info[i][j] = ((VOPostulacion) us.get(i)).getUser();
                        break;
                    case 2:
                        info[i][j] = ((VOPostulacion) us.get(i)).getEstado();
                        break;
                }
            }
            tabla.addRow(info[i]);
        }

    }

    @Action
    public void cerrar() throws RemoteException {
        control.MataVista();
    }

    @Action
    public void detalleArticulo() throws RemoteException {
        if(this.jTable1.getSelectedRow()>=0)
            control.verDetalle(this.jTable1.getSelectedRow());
        else
            this.VentanaFeedback("Debes Seleccionar un articulo primero", "Ver Informacion");
    }
    @Action
    public void evaluarArticulo() throws RemoteException {
        if(this.jTable1.getSelectedRow()>=0)
            control.evaluarArticulo(this.jTable1.getSelectedRow());
        else
            this.VentanaFeedback("Debes Seleccionar un articulo primero", "Evaluar Articulo");
    }
    @Action
    public void descargarArticulo() {
        if(this.jTable1.getSelectedRow()>=0) {
            control.descargarArticulo(this.jTable1.getSelectedRow());
        } else
            this.VentanaFeedback("Debes Seleccionar un articulo primero", "Descargar Articulo");
        
    }

    public void VentanaFeedback(String m, String t) {
        JOptionPane.showMessageDialog(this, m, t, JOptionPane.INFORMATION_MESSAGE);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
