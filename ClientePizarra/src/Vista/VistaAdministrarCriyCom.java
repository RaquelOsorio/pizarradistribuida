package Vista;

import Controlador.CAdministrarCriyCom;
import VO.VOCriterio;
import VO.VOTipoComentario;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import org.jdesktop.application.Action;

public class VistaAdministrarCriyCom extends javax.swing.JFrame {
    CAdministrarCriyCom control;
    TAdmCriyCom TCri;
    TAdmCriyCom TCom;

    public VistaAdministrarCriyCom() {
        initComponents();
    }

    public VistaAdministrarCriyCom(CAdministrarCriyCom aThis) {
        control = aThis;
        initComponents();
        TCri = new TAdmCriyCom();
        String[] campos={"ID","Criterio", "Descripcion"};
        for(int i=0; i<campos.length; i++)
            TCri.addColumn(campos[i]);
        this.TablaCriterios.setModel(TCri);
        TableColumnModel cm = TablaCriterios.getColumnModel();
        cm.getColumn(0).setResizable(false);
        cm.getColumn(0).setMaxWidth(30);
        cm.getColumn(1).setResizable(false);
        cm.getColumn(1).setMaxWidth(80);

        TCom = new TAdmCriyCom();
        String[] camposCom={"ID","Comentario", "Descripcion"};
        for(int i=0; i<camposCom.length; i++)
            TCom.addColumn(camposCom[i]);
        this.TablaComentarios.setModel(TCom);
        TableColumnModel co = TablaComentarios.getColumnModel();
        co.getColumn(0).setResizable(false);
        co.getColumn(0).setMaxWidth(30);
        co.getColumn(1).setResizable(false);
        co.getColumn(1).setMaxWidth(80);
    }

    public void ActualizarCriterioFila(VOCriterio c, int fila) {
        this.TablaCriterios.setValueAt(c.getNombre(), fila, 1);
        this.TablaCriterios.setValueAt(c.getDescripcion(), fila, 2);
    }

    public void ActualizarTipoComentarioFila(VOTipoComentario c, int fila) {
        this.TablaComentarios.setValueAt(c.getNombre(), fila, 1);
        this.TablaComentarios.setValueAt(c.getDescripcion(), fila, 2);
    }

    @Action
    public void EliminarCriterioPresionado() throws RemoteException {
        if(this.TablaCriterios.getSelectedRow()>=0)
            control.eliminarCriterio(""+this.TablaCriterios.getValueAt(this.TablaCriterios.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar un Criterio primero", "Eliminar Usuario");
    }

    @Action
    public void EliminarComentarioPresionado() throws RemoteException {
        if(this.TablaComentarios.getSelectedRow()>=0)
            control.eliminarTipoComentario(""+this.TablaComentarios.getValueAt(this.TablaComentarios.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar un Tipo de Comentario primero", "Eliminar Usuario");
    }

    @Action
    public void IngresarCriterioPresionado() throws RemoteException {
        control.IngresarCriterioVista();
    }

    @Action
    public void IngresarTipoComentarioPresionado() throws RemoteException {
        control.IngresarTipoComentarioVista();
    }

    @Action
    public void ModificarCriterioPresionado() throws RemoteException {
        if(this.TablaCriterios.getSelectedRow()>=0)
            control.ModificarCriterioVista(""+this.TablaCriterios.getValueAt(this.TablaCriterios.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar un Criterio primero", "Eliminar Usuario");
    }

    @Action
    public void ModificarTipoComentarioPresionado() throws RemoteException {
        if(this.TablaComentarios.getSelectedRow()>=0)
            control.ModificarTipoComentarioVista(""+this.TablaComentarios.getValueAt(this.TablaComentarios.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar un Tipo de Comentario primero", "Eliminar Usuario");
    }

    @Action
    public void VerCriterioPresionado() throws RemoteException {
        if(this.TablaCriterios.getSelectedRow()>=0)
            control.VerCriterioVista(""+this.TablaCriterios.getValueAt(this.TablaCriterios.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar un Criterio primero", "Eliminar Usuario");
    }

    @Action
    public void VerTipoComentarioPresionado() throws RemoteException {
        if(this.TablaComentarios.getSelectedRow()>=0)
            control.VerTipoComentarioVista(""+this.TablaComentarios.getValueAt(this.TablaComentarios.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar un Tipo de Comentario primero", "Eliminar Usuario");
    }

    public void agregarTipoComentario(VOTipoComentario c) {
        Object x[] = new Object[3];
        x[0]=c.getID();
        x[1]=c.getNombre();
        x[2]=c.getDescripcion();
        this.TCom.addRow(x);
    }

    @Action
    public void cerrar() {
        this.TCom=null;
        this.TCri=null;
        control.MataVista();
    }
    public void agregarCriterio(VOCriterio c) {
        Object x[] = new Object[3];
        x[0]=c.getID();
        x[1]=c.getNombre();
        x[2]=c.getDescripcion();
        this.TCri.addRow(x);
    }

    public void borrarCriFila(int idv) {
        this.TCri.removeRow(idv);
    }

    public void borrarComFila(int idv) {
        this.TCom.removeRow(idv);
    }

    public void cargarCriterios(Vector cri) {
        
        Object[][] info=new Object[cri.size()][3];
        for(int i=0; i<cri.size(); i++) {
            for(int j=0; j<3; j++) {
                switch (j) {
                    case 0:
                        info[i][j]=((VOCriterio)cri.get(i)).getID();
                        break;
                    case 1:
                        info[i][j]=((VOCriterio)cri.get(i)).getNombre();
                        break;
                    case 2:
                        info[i][j]=((VOCriterio)cri.get(i)).getDescripcion();
                        break;
                }
            }
            TCri.addRow(info[i]);
        }
        this.TablaCriterios.setModel(TCri);
    }

    public void cargarComentarios(Vector com) {
        
        Object[][] info=new Object[com.size()][3];
        for(int i=0; i<com.size(); i++) {
            for(int j=0; j<3; j++) {
                switch (j) {
                    case 0:
                        info[i][j]=((VOTipoComentario)com.get(i)).getID();
                        break;
                    case 1:
                        info[i][j]=((VOTipoComentario)com.get(i)).getNombre();
                        break;
                    case 2:
                        info[i][j]=((VOTipoComentario)com.get(i)).getDescripcion();
                        break;
                }
            }
            TCom.addRow(info[i]);
        }
        this.TablaComentarios.setModel(TCom);
    }

    public JLabel getEstado() {
        return estado;
    }

    public void VentanaFeedback(String m, String t) {
        JOptionPane.showMessageDialog(this, m, t, JOptionPane.INFORMATION_MESSAGE);
    }

    public int VentanaConfirmacion(String m, String t) {
        return JOptionPane.showConfirmDialog(this, m, t, JOptionPane.YES_NO_OPTION);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCriterios = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaComentarios = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        estado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getResourceMap(VistaAdministrarCriyCom.class);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        TablaCriterios.setAutoCreateRowSorter(true);
        TablaCriterios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Criterio", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCriterios.setName("TablaCriterios"); // NOI18N
        TablaCriterios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(TablaCriterios);
        TablaCriterios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TablaCriterios.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("TablaCriterios.columnModel.title0")); // NOI18N
        TablaCriterios.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("TablaCriterios.columnModel.title1")); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getActionMap(VistaAdministrarCriyCom.class, this);
        jButton1.setAction(actionMap.get("IngresarCriterioPresionado")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setAction(actionMap.get("ModificarCriterioPresionado")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        jButton3.setAction(actionMap.get("EliminarCriterioPresionado")); // NOI18N
        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N

        jButton4.setAction(actionMap.get("VerCriterioPresionado")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        TablaComentarios.setAutoCreateRowSorter(true);
        TablaComentarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tipo de Comentario", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaComentarios.setName("TablaComentarios"); // NOI18N
        jScrollPane2.setViewportView(TablaComentarios);
        TablaComentarios.getColumnModel().getColumn(0).setResizable(false);
        TablaComentarios.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("TablaComentarios.columnModel.title0")); // NOI18N
        TablaComentarios.getColumnModel().getColumn(1).setResizable(false);
        TablaComentarios.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("TablaComentarios.columnModel.title1")); // NOI18N

        jButton5.setAction(actionMap.get("IngresarTipoComentarioPresionado")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        jButton6.setAction(actionMap.get("ModificarTipoComentarioPresionado")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N

        jButton7.setAction(actionMap.get("EliminarComentarioPresionado")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N

        jButton8.setAction(actionMap.get("VerTipoComentarioPresionado")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton9.setAction(actionMap.get("cerrar")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N

        estado.setText(resourceMap.getString("estado.text")); // NOI18N
        estado.setName("estado"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(estado, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(estado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaComentarios;
    private javax.swing.JTable TablaCriterios;
    private javax.swing.JLabel estado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}