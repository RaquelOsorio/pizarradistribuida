package Vista;

import Controlador.CAdmSolicitud;
import VO.VOAreaInteres;
import VO.VOSolicitud;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;

public class VistaAdmSolicitud extends javax.swing.JFrame {
    CAdmSolicitud control;
    TUsAdmUs tabla;
    TModeloLogin tabla2;
    Vector todasAreas;
    Vector areas;
    int solCargada;

    public VistaAdmSolicitud(CAdmSolicitud c) {
        control = c;
        solCargada = -1;
        todasAreas = new Vector();
        areas = new Vector();
        VOAreaInteres a1=new VOAreaInteres(1, "Mineria de datos");
        VOAreaInteres a2=new VOAreaInteres(2, "Estructuras Discretas");
        VOAreaInteres a3=new VOAreaInteres(3, "Redes Neuronales");
        this.todasAreas.add(a1);
        this.todasAreas.add(a2);
        this.todasAreas.add(a3);
        initComponents();
        tabla = new TUsAdmUs();
        this.setTablaSolicitud();
        this.limpiarDetalle();
    }

    public void borrarFila(int idv) {
        this.tabla.removeRow(idv);
    }

    public void cargaSolicitud(VOSolicitud s) {
        this.tipouser.setText(s.getTipoUsuario());
        this.username.setText(s.getNombreUsuario());
        this.nombre.setText(s.getNombre());
        this.apellido.setText(s.getApellido());
        this.email.setText(s.getEmail());
        this.run.setText(s.getRut());
        this.domicilio.setText(s.getDomicilio());
        this.telefono.setText(s.getTelefono());
        areas.removeAllElements();
        tabla2 = new TModeloLogin();
        for(int i=0; i<s.getAreas().length; i++) {
            VOAreaInteres n=(VOAreaInteres)this.todasAreas.get(s.getAreas()[i]-1);
            areas.add(n);
            tabla2.addArea(((VOAreaInteres)areas.get(i)).getNombre());
        }
        this.tablaAreas.setModel(tabla2);
        this.aceptar2.setEnabled(true);
        this.rechazar2.setEnabled(true);
    }

    public void limpiarDetalle() {
        this.tipouser.setText("");
        this.nombre.setText("");
        this.apellido.setText("");
        this.domicilio.setText("");
        this.email.setText("");
        this.run.setText("");
        this.telefono.setText("");
        this.username.setText("");
        if(areas!=null)
            this.areas.removeAllElements();
        TModeloLogin t=new TModeloLogin();
        this.tablaAreas.setModel(t);
        this.aceptar2.setEnabled(false);
        this.rechazar2.setEnabled(false);
    }

    public void setTablaSolicitud() {
        String[] campos={"Nombre Usuario", "Nombre", "Apellido", "Tipo Usuario"};
        this.jTable1.setModel(tabla);
        for(int i=0; i<campos.length; i++)
            tabla.addColumn(campos[i]);
    }
    public void CargarSolicitudes(Vector us) {
        Object[][] info=new Object[us.size()][4];
        for(int i=0; i<us.size(); i++) {
            for(int j=0; j<4; j++) {
                switch (j) {
                    case 0:
                        info[i][j]=((VOSolicitud)us.get(i)).getNombreUsuario();
                        break;
                    case 1:
                        info[i][j]=((VOSolicitud)us.get(i)).getNombre();
                        break;
                    case 2:
                        info[i][j]=((VOSolicitud)us.get(i)).getApellido();
                        break;
                    case 3:
                        info[i][j]=((VOSolicitud)us.get(i)).getTipoUsuario();
                        break;
                }
            }
            tabla.addRow(info[i]);
        }
    }
    public void VentanaFeedback(String m, String t) {
        JOptionPane.showMessageDialog(this, m, t, JOptionPane.INFORMATION_MESSAGE);
    }

    public int VentanaConfirmacion(String m, String t) {
        return JOptionPane.showConfirmDialog(this, m, t, JOptionPane.YES_NO_OPTION);
    }

    @Action
    public void VerDetallePresionado() throws RemoteException {
        solCargada=this.jTable1.getSelectedRow();
        if(this.jTable1.getSelectedRow()>=0)
            control.VerDetalleSolicitudVista((String)this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar una solicitud primero", "Ver Detalle");
    }

    @Action
    public void RechazarSolicitudPresionado() throws RemoteException {
        if(this.jTable1.getSelectedRow()>=0)
            control.RechazarSolicitud((String)this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0));
        else
            this.VentanaFeedback("Debes Seleccionar una solicitud primero", "Eliminar Usuario");
    }
    @Action
    public void RechazarSolicitud2Presionado() throws RemoteException {
        if(solCargada>=0) {
            control.RechazarSolicitud((String)this.jTable1.getValueAt(solCargada, 0));
            solCargada = -1;
        }
    }
    @Action
    public void AceptarSolicitudPresionado() throws RemoteException {
        if(this.jTable1.getSelectedRow()>=0)
            control.AceptarSolicitud((String)this.jTable1.getValueAt(this.jTable1.getSelectedRow(), 0), this.jTable1.getSelectedRow());
        else
            this.VentanaFeedback("Debes Seleccionar una solicitud primero", "Eliminar Usuario");
    }
    @Action
    public void AceptarSolicitud2Presionado() throws RemoteException {
        if(solCargada>=0) {
            control.AceptarSolicitud((String)this.jTable1.getValueAt(solCargada, 0), solCargada);
            solCargada = -1;
        }
    }

    public int getSolCargada() {
        return solCargada;
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        aceptar2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaAreas = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        email = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        domicilio = new javax.swing.JLabel();
        run = new javax.swing.JLabel();
        apellido = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        tipouser = new javax.swing.JLabel();
        rechazar2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getResourceMap(VistaAdmSolicitud.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getActionMap(VistaAdmSolicitud.class, this);
        jButton2.setAction(actionMap.get("VerDetallePresionado")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        jButton4.setAction(actionMap.get("RechazarSolicitudPresionado")); // NOI18N
        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N

        jButton5.setAction(actionMap.get("AceptarSolicitudPresionado")); // NOI18N
        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setName("jTable1"); // NOI18N
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        aceptar2.setAction(actionMap.get("AceptarSolicitud2Presionado")); // NOI18N
        aceptar2.setText(resourceMap.getString("aceptar2.text")); // NOI18N
        aceptar2.setName("aceptar2"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        tablaAreas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Áreas del Usuario"
            }
        ));
        tablaAreas.setName("tablaAreas"); // NOI18N
        jScrollPane2.setViewportView(tablaAreas);

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        email.setText(resourceMap.getString("email.text")); // NOI18N
        email.setName("email"); // NOI18N

        telefono.setText(resourceMap.getString("telefono.text")); // NOI18N
        telefono.setName("telefono"); // NOI18N

        domicilio.setText(resourceMap.getString("domicilio.text")); // NOI18N
        domicilio.setName("domicilio"); // NOI18N

        run.setText(resourceMap.getString("run.text")); // NOI18N
        run.setName("run"); // NOI18N

        apellido.setText(resourceMap.getString("apellido.text")); // NOI18N
        apellido.setName("apellido"); // NOI18N

        nombre.setText(resourceMap.getString("nombre.text")); // NOI18N
        nombre.setName("nombre"); // NOI18N

        username.setText(resourceMap.getString("username.text")); // NOI18N
        username.setName("username"); // NOI18N

        tipouser.setText(resourceMap.getString("tipouser.text")); // NOI18N
        tipouser.setName("tipouser"); // NOI18N

        rechazar2.setAction(actionMap.get("RechazarSolicitud2Presionado")); // NOI18N
        rechazar2.setText(resourceMap.getString("rechazar2.text")); // NOI18N
        rechazar2.setName("rechazar2"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipouser, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(apellido, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(run, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(domicilio, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(aceptar2)
                                .addGap(5, 5, 5)
                                .addComponent(rechazar2)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tipouser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(username)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(apellido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(run)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(domicilio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telefono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(email))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar2)
                    .addComponent(rechazar2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptar2;
    private javax.swing.JLabel apellido;
    private javax.swing.JLabel domicilio;
    private javax.swing.JLabel email;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel nombre;
    private javax.swing.JButton rechazar2;
    private javax.swing.JLabel run;
    private javax.swing.JTable tablaAreas;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel tipouser;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}