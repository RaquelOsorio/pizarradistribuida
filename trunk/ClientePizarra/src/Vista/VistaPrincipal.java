package Vista;

import Controlador.CPrincipal;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.rmi.RemoteException;
import org.jdesktop.application.Action;

public class VistaPrincipal extends javax.swing.JFrame {
    CPrincipal cp;

    public VistaPrincipal(CPrincipal aThis) {
        cp=aThis;
        initComponents();
        //segun incremento
        this.BConvocatoria.setEnabled(true);
        this.BPerfil.setEnabled(false);
        this.BAreaAdministrar.setEnabled(false);
        this.BArtEvaluar.setEnabled(true);
        this.BArticulos.setEnabled(true);
        this.BMensajes.setEnabled(false);
        this.BPostulaciones.setEnabled(false);
    }

    public VistaPrincipal() {
        initComponents();
    }

    @Action
    public void CerrarSesionPresionado() throws RemoteException {
        cp.LogOut();
    }
    @Action
    public void AdminUsuarioPresionado() throws RemoteException {
        cp.AdministarUsuarios();
    }

    @Action
    public void ArtEvaluarPresionado() throws RemoteException {
        cp.ArticulosEvaluar();
    }

    
    @Action
    public void AdminCriyComPresionado() throws RemoteException {
        cp.AdministarCriyCom();
    }

    @Action
    public void ArticuloPresionado() throws RemoteException {
        cp.Articulo();
    }

    @Action
    public void ConvocatoriasPresionado() throws RemoteException {
        cp.Convocatorias();
    }

    public JButton getBAreaAdministrar() {
        return BAreaAdministrar;
    }

    public JButton getBArtEvaluar() {
        return BArtEvaluar;
    }

    public JButton getBArticulos() {
        return BArticulos;
    }

    public JButton getBCerrarSesion() {
        return BCerrarSesion;
    }

    public JButton getBConvocatoria() {
        return BConvocatoria;
    }

    public JButton getBCriterioAdministrar() {
        return BCriterioAdministrar;
    }

    public JButton getBMensajes() {
        return BMensajes;
    }

    public JButton getBPerfil() {
        return BPerfil;
    }

    public JButton getBPostulaciones() {
        return BPostulaciones;
    }

    public JButton getBUsuariosAdministrar() {
        return BUsuariosAdministrar;
    }

    public CPrincipal getCp() {
        return cp;
    }

    public JLabel getJLabel1() {
        return jLabel1;
    }

    public JLabel getJLabel2() {
        return jLabel2;
    }

    public JLabel getJLabel3() {
        return jLabel3;
    }

    public JLabel getJLabel8() {
        return jLabel8;
    }

    public JPanel getJPanel1() {
        return jPanel1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BUsuariosAdministrar = new javax.swing.JButton();
        BAreaAdministrar = new javax.swing.JButton();
        BCriterioAdministrar = new javax.swing.JButton();
        BMensajes = new javax.swing.JButton();
        BArticulos = new javax.swing.JButton();
        BArtEvaluar = new javax.swing.JButton();
        BConvocatoria = new javax.swing.JButton();
        BPostulaciones = new javax.swing.JButton();
        BPerfil = new javax.swing.JButton();
        BCerrarSesion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setName("jPanel1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getResourceMap(VistaPrincipal.class);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Vista.ClienteIwillApp.class).getContext().getActionMap(VistaPrincipal.class, this);
        BUsuariosAdministrar.setAction(actionMap.get("AdminUsuarioPresionado")); // NOI18N
        BUsuariosAdministrar.setText(resourceMap.getString("BUsuariosAdministrar.text")); // NOI18N
        BUsuariosAdministrar.setName("BUsuariosAdministrar"); // NOI18N

        BAreaAdministrar.setText(resourceMap.getString("BAreaAdministrar.text")); // NOI18N
        BAreaAdministrar.setName("BAreaAdministrar"); // NOI18N

        BCriterioAdministrar.setAction(actionMap.get("AdminCriyComPresionado")); // NOI18N
        BCriterioAdministrar.setText(resourceMap.getString("BCriterioAdministrar.text")); // NOI18N
        BCriterioAdministrar.setName("BCriterioAdministrar"); // NOI18N

        BMensajes.setText(resourceMap.getString("BMensajes.text")); // NOI18N
        BMensajes.setName("BMensajes"); // NOI18N

        BArticulos.setAction(actionMap.get("ArticuloPresionado")); // NOI18N
        BArticulos.setText(resourceMap.getString("BArticulos.text")); // NOI18N
        BArticulos.setName("BArticulos"); // NOI18N

        BArtEvaluar.setAction(actionMap.get("ArtEvaluarPresionado")); // NOI18N
        BArtEvaluar.setText(resourceMap.getString("BArtEvaluar.text")); // NOI18N
        BArtEvaluar.setName("BArtEvaluar"); // NOI18N

        BConvocatoria.setAction(actionMap.get("ConvocatoriasPresionado")); // NOI18N
        BConvocatoria.setText(resourceMap.getString("BConvocatoria.text")); // NOI18N
        BConvocatoria.setName("BConvocatoria"); // NOI18N

        BPostulaciones.setText(resourceMap.getString("BPostulaciones.text")); // NOI18N
        BPostulaciones.setName("BPostulaciones"); // NOI18N

        BPerfil.setText(resourceMap.getString("BPerfil.text")); // NOI18N
        BPerfil.setName("BPerfil"); // NOI18N

        BCerrarSesion.setAction(actionMap.get("CerrarSesionPresionado")); // NOI18N
        BCerrarSesion.setText(resourceMap.getString("BCerrarSesion.text")); // NOI18N
        BCerrarSesion.setName("BCerrarSesion"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BUsuariosAdministrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BAreaAdministrar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BCriterioAdministrar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BMensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BArtEvaluar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BPostulaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BAreaAdministrar, BArtEvaluar, BArticulos, BCerrarSesion, BConvocatoria, BCriterioAdministrar, BMensajes, BPerfil, BPostulaciones, BUsuariosAdministrar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BUsuariosAdministrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BAreaAdministrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BCriterioAdministrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BMensajes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BArticulos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BArtEvaluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BConvocatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BPostulaciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BCerrarSesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BAreaAdministrar, BArtEvaluar, BArticulos, BCerrarSesion, BCriterioAdministrar, BMensajes, BPerfil, BPostulaciones, BUsuariosAdministrar});

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAreaAdministrar;
    private javax.swing.JButton BArtEvaluar;
    private javax.swing.JButton BArticulos;
    private javax.swing.JButton BCerrarSesion;
    private javax.swing.JButton BConvocatoria;
    private javax.swing.JButton BCriterioAdministrar;
    private javax.swing.JButton BMensajes;
    private javax.swing.JButton BPerfil;
    private javax.swing.JButton BPostulaciones;
    private javax.swing.JButton BUsuariosAdministrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}