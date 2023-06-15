package controlcontacto.appescritorio;

public class FrmInicio extends javax.swing.JFrame {

    public FrmInicio() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH); // Colocar la pantalla maximizada al inicio
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        meInicio = new javax.swing.JMenuBar();
        meMantenimiento = new javax.swing.JMenu();
        meRol = new javax.swing.JMenuItem();
        meUsuario = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        meCambiarPassword = new javax.swing.JMenu();
        meCambiarUsuario = new javax.swing.JMenu();
        meSalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/controlcontacto/appescritorio/images/inicio.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 480));

        meInicio.setBackground(new java.awt.Color(0, 102, 153));
        meInicio.setBorder(null);
        meInicio.setForeground(new java.awt.Color(255, 255, 255));
        meInicio.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        meInicio.setPreferredSize(new java.awt.Dimension(430, 40));

        meMantenimiento.setBackground(new java.awt.Color(0, 102, 153));
        meMantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        meMantenimiento.setMnemonic('M');
        meMantenimiento.setText("Mantenimiento");
        meMantenimiento.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N

        meRol.setBackground(new java.awt.Color(0, 102, 153));
        meRol.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        meRol.setForeground(new java.awt.Color(255, 255, 255));
        meRol.setMnemonic('R');
        meRol.setText("Rol");
        meRol.setPreferredSize(new java.awt.Dimension(120, 35));
        meRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meRolActionPerformed(evt);
            }
        });
        meMantenimiento.add(meRol);

        meUsuario.setBackground(new java.awt.Color(0, 102, 153));
        meUsuario.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        meUsuario.setForeground(new java.awt.Color(255, 255, 255));
        meUsuario.setMnemonic('U');
        meUsuario.setText("Usuario");
        meUsuario.setPreferredSize(new java.awt.Dimension(120, 35));
        meUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meUsuarioActionPerformed(evt);
            }
        });
        meMantenimiento.add(meUsuario);
        meMantenimiento.add(jSeparator1);

        jMenuItem1.setBackground(new java.awt.Color(0, 102, 153));
        jMenuItem1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jMenuItem1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setText("Marca");
        jMenuItem1.setPreferredSize(new java.awt.Dimension(120, 35));
        meMantenimiento.add(jMenuItem1);

        jMenuItem2.setBackground(new java.awt.Color(0, 102, 153));
        jMenuItem2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jMenuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setText("Categoría");
        jMenuItem2.setPreferredSize(new java.awt.Dimension(120, 35));
        meMantenimiento.add(jMenuItem2);

        jSeparator2.setPreferredSize(new java.awt.Dimension(100, 5));
        meMantenimiento.add(jSeparator2);

        jMenuItem3.setBackground(new java.awt.Color(0, 102, 153));
        jMenuItem3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jMenuItem3.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setText("Producto");
        jMenuItem3.setPreferredSize(new java.awt.Dimension(120, 35));
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        meMantenimiento.add(jMenuItem3);

        meInicio.add(meMantenimiento);

        meCambiarPassword.setBackground(new java.awt.Color(0, 102, 153));
        meCambiarPassword.setForeground(new java.awt.Color(255, 255, 255));
        meCambiarPassword.setMnemonic('p');
        meCambiarPassword.setText("Cambiar password");
        meCambiarPassword.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        meCambiarPassword.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                meCambiarPasswordMenuSelected(evt);
            }
        });
        meInicio.add(meCambiarPassword);

        meCambiarUsuario.setBackground(new java.awt.Color(0, 102, 153));
        meCambiarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        meCambiarUsuario.setMnemonic('C');
        meCambiarUsuario.setText("Cambiar usuario");
        meCambiarUsuario.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        meCambiarUsuario.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                meCambiarUsuarioMenuSelected(evt);
            }
        });
        meInicio.add(meCambiarUsuario);

        meSalir.setBackground(new java.awt.Color(0, 102, 153));
        meSalir.setForeground(new java.awt.Color(255, 255, 255));
        meSalir.setMnemonic('S');
        meSalir.setText("Salir");
        meSalir.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        meSalir.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                meSalirMenuSelected(evt);
            }
        });
        meInicio.add(meSalir);

        setJMenuBar(meInicio);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meCambiarPasswordMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_meCambiarPasswordMenuSelected
        // TODO add your handling code here:    
        FrmCambiarPassword frmCambiarPassword = new FrmCambiarPassword(this);
        frmCambiarPassword.setVisible(true);
        frmCambiarPassword.setAlwaysOnTop(true); // mostrar el formulario activo en el caso que este apareciendo atras del formulario de inicio
        this.setEnabled(false);
    }//GEN-LAST:event_meCambiarPasswordMenuSelected

    private void meCambiarUsuarioMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_meCambiarUsuarioMenuSelected
        // TODO add your handling code here:
        this.setVisible(false);
        FrmLogin frmLogin = new FrmLogin();
        frmLogin.setVisible(true);
    }//GEN-LAST:event_meCambiarUsuarioMenuSelected

    private void meSalirMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_meSalirMenuSelected
        // TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
        System.exit(0); // cerrar el sistema, usar para cerrar el sistema completamente
    }//GEN-LAST:event_meSalirMenuSelected

    private void meRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meRolActionPerformed
        // TODO add your handling code here:
        FrmRolLec frmRolLec = new FrmRolLec(this);
        frmRolLec.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_meRolActionPerformed

    private void meUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meUsuarioActionPerformed
        // TODO add your handling code here:
        FrmUsuarioLec frmUsuarioLec = new FrmUsuarioLec(this);
        frmUsuarioLec.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_meUsuarioActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        FrmProductoLec1 frmUsuarioLec = new FrmProductoLec1(this);
        frmUsuarioLec.setVisible(true);
        this.setEnabled(false);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu meCambiarPassword;
    private javax.swing.JMenu meCambiarUsuario;
    private javax.swing.JMenuBar meInicio;
    private javax.swing.JMenu meMantenimiento;
    private javax.swing.JMenuItem meRol;
    private javax.swing.JMenu meSalir;
    private javax.swing.JMenuItem meUsuario;
    // End of variables declaration//GEN-END:variables
}
