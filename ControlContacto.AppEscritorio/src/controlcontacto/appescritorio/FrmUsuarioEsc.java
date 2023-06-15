package controlcontacto.appescritorio;

// Importaciones para el funcionamiento de la pantalla de mantenimiento de Usuario
import controlcontacto.appescritorio.utils.*; // importar todas las clases de utilidades de la aplicaciones escritorio
import controlcontacto.accesoadatos.*; // importar todas la clases de la capa de acceso a datos
import controlcontactos.entidadesdenegocio.*; // importar todas la clases de la capa de entidades de negocio
import java.util.ArrayList; // importar el ArrayList para recibir la lista de Usuarios de la base de datos
import javax.swing.JOptionPane; // importa la clase JOptionPane para mostrar alertas o advertencias a los usuarios

public class FrmUsuarioEsc extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc="Codigo para propiedades y metodos locales del formulario FrmUsuarioEsc">
    private FrmUsuarioLec frmPadre; // Propiedad para almacenar el formulario de FrmUsuarioLec
    private int opcionForm; // Propiedad para almacenar la opcion a realizar en el formulario puede ser Crear,Modificar,Eliminar, Ver
    private Usuario usuarioActual; // Propiedad para almacenar el rol que se desea crear,modificar,eliminar sus datos

    // metodo para llenar los controles del formulario FrmUsuarioEsc con los datos que hay en la base de datos en la tabla de Usuario 
    // solicitado desde la pantalla de FrmUsuarioLec
    private void llenarControles(Usuario pUsuario) {
        try {
            usuarioActual = UsuarioDAL.obtenerPorId(pUsuario); // Obtener el Usuario por Id 
            this.txtNombre.setText(usuarioActual.getNombre()); // Llenar la caja de texto txtNombre con la propiedad Nombre de la clase Usuario
            this.txtApellido.setText(usuarioActual.getApellido());
            this.txtLogin.setText(usuarioActual.getLogin());
            this.cbRol.setSelectedItem(new ItemsCombo(usuarioActual.getIdRol(), null)); // Seleccionar el combo box con el Id del usuario
            this.cbEstatus.setSelectedItem(new ItemsCombo(usuarioActual.getEstatus(), null)); // Seleccionar el combo box con el Estatus del usuario
        } catch (Exception ex) {
            // Enviar el mensaje al usuario de la pantalla en el caso que suceda un error al obtener los datos de la base de datos
            JOptionPane.showMessageDialog(frmPadre, "Sucedio el siguiente error: " + ex.getMessage());
        }
    }

    // metodo a utilizar para iniciar los datos al momento de mostrar el formulario FrmUsuarioEsc
    private void iniciarDatos(Usuario pUsuario, int pOpcionForm, FrmUsuarioLec pFrmPadre) {
        frmPadre = pFrmPadre;
        usuarioActual = new Usuario();
        opcionForm = pOpcionForm;
        this.frmPadre.iniciarComboEstatus(cbEstatus); // iniciar los datos del combo box  de estatus
        this.frmPadre.iniciarComboRol(cbRol, pFrmPadre); // iniciar los datos del combo box  de Rol
        this.txtNombre.setEditable(true); // colocar txtNombre que se pueda editar 
        this.txtApellido.setEditable(true); // colocar txtApellido que se pueda editar 
        this.txtLogin.setEditable(true); // colocar txtLogin que se pueda editar 
        this.txtPassword.setEditable(true); // colocar txtPassword que se pueda editar
        this.txtConfirmarPassword.setEditable(true); // colocar txtConfirmarPassword que se pueda editar
        switch (pOpcionForm) {
            case FormEscOpcion.CREAR:
                btnOk.setText("Nuevo"); // modificar el texto del boton btnOk a "Nuevo" cuando la pOpcionForm sea CREAR
                this.btnOk.setMnemonic('N'); // modificar la tecla de atajo del boton btnOk a la letra N
                this.setTitle("Crear un nuevo Usuario"); // modificar el titulo de la pantalla de FrmUsuarioEsc
                break;
            case FormEscOpcion.MODIFICAR:
                btnOk.setText("Modificar"); // modificar el texto del boton btnOk a "Modificar" cuando la pOpcionForm sea MODIFICAR
                this.btnOk.setMnemonic('M'); // modificar la tecla de atajo del boton btnOk a la letra M
                this.setTitle("Modificar el Usuario"); // modificar el titulo de la pantalla de FrmUsuarioEsc
                this.txtPassword.setEditable(false); // deshabilitar la caja de texto txtPassword 
                this.txtConfirmarPassword.setEditable(false); // deshabilitar la caja de texto txtConfirmarPassword 
                llenarControles(pUsuario);// llamar el metodo llenarControles para llenar los controles del formulario con los datos de la clase usuario
                break;
            case FormEscOpcion.ELIMINAR:
                btnOk.setText("Eliminar");// modificar el texto del boton btnOk a "Eliminar" cuando la pOpcionForm sea ELIMINAR
                this.btnOk.setMnemonic('E'); // modificar la tecla de atajo del boton btnOk a la letra E
                this.setTitle("Eliminar el Rol"); // modificar el titulo de la pantalla de FrmUsuarioEsc
                this.txtNombre.setEditable(false); // deshabilitar la caja de texto txtNombre              
                this.txtApellido.setEditable(false); // deshabilitar la caja de texto txtApellido 
                this.txtLogin.setEditable(false); // deshabilitar la caja de texto txtLogin 
                this.txtPassword.setEditable(false); // deshabilitar la caja de texto txtPassword 
                this.txtConfirmarPassword.setEditable(false); // deshabilitar la caja de texto txtConfirmarPassword 
                this.cbRol.setEnabled(false); // deshabilitar el combo box de Rol
                this.cbEstatus.setEnabled(false); // deshabilitar el combo box de Estatus
                llenarControles(pUsuario); // llamar el metodo llenarControles para llenar los controles del formulario con los datos de la clase usuario
                break;
            case FormEscOpcion.VER:
                btnOk.setText("Ver"); // modificar el texto del boton btnOk a "Ver" cuando la pOpcionForm sea VER
                btnOk.setVisible(false); // ocultar el boton btnOk cuando pOpcionForm sea opcion VER
                this.setTitle("Ver el Rol"); // modificar el titulo de la pantalla de FrmRolEsc
                this.txtNombre.setEditable(false); // deshabilitar la caja de texto txtNombre              
                this.txtApellido.setEditable(false); // deshabilitar la caja de texto txtNombre 
                this.txtLogin.setEditable(false); // deshabilitar la caja de texto txtNombre 
                this.txtPassword.setEditable(false); // deshabilitar la caja de texto txtNombre 
                this.txtConfirmarPassword.setEditable(false); // deshabilitar la caja de texto txtNombre 
                this.cbRol.setEnabled(false); // deshabilitar el combo box de Rol
                this.cbEstatus.setEnabled(false); // deshabilitar el combo box de Estatus
                llenarControles(pUsuario);// llamar el metodo llenarControles para llenar los controles del formulario con los datos de la clase usuario
                break;
            default:
                break;
        }
    }

    // validar los datos antes de enviar a la DAL de Usuario
    private boolean validarDatos() {
        boolean result = true; // variable para saber si los datos son validos para su envio a la DAL de Usuario y despues a la base de datos 
        ItemsCombo itemRol = (ItemsCombo) cbRol.getSelectedItem();
        ItemsCombo itemEstatus = (ItemsCombo) cbEstatus.getSelectedItem();
        if (this.txtNombre.getText().trim().isEmpty()) {  // verificar si la caja de texto txtNombre esta vacia 
            result = false;
        } else if (this.txtApellido.getText().trim().isEmpty()) {  // verificar si la caja de texto txtApellido esta vacia
            result = false;
        } else if (this.txtLogin.getText().trim().isEmpty()) {  // verificar si la caja de texto txtLogin esta vacia
            result = false;
        } else if (itemRol.getId() == 0) { // verificar si el combo box de rol selecciono uno
            result = false;
        } else if (itemEstatus.getId() == 0) { // verificar si el combo box de estatus selecciono uno
            result = false;
        } else if (this.opcionForm == FormEscOpcion.CREAR) { // verificar que sea la opcion de crear un nuevo registro 
            if (new String(this.txtPassword.getPassword()).trim().isEmpty()) { // verificar si la caja de texto txtPassword esta vacia
                result = false;
                // verificar si la caja de texto txtConfirmarPassword esta vacia
            } else if (new String(this.txtConfirmarPassword.getPassword()).trim().isEmpty()) {
                result = false;
            }
        }
        if (result == false) {
            // mostrar un mensaje al usuario de la pantalla que los campos son obligatorios en el caso que la variable result sea false
            JOptionPane.showMessageDialog(this, "Los campos con * son obligatorios");
        } else if (this.opcionForm == FormEscOpcion.CREAR) { // verificar que sea la opcion de crear un nuevo registro
            boolean longitudValida = true;
            // validar que la longitud de password y confirmar password sea mayor a cinco caracteres
            if (new String(this.txtPassword.getPassword()).trim().length() < 5) {
                longitudValida = false;
            } else if (new String(this.txtConfirmarPassword.getPassword()).trim().length() < 5) {
                longitudValida = false;
            }
            if (longitudValida == false) {
                JOptionPane.showMessageDialog(this, "Password, confirmar password debe estar entre 5 a 32 caracteres ");
                result = false;
            } else {
                // validar para que el password y confirmar password sea iguales
                if (!(new String(this.txtPassword.getPassword()).trim().equals(new String(this.txtConfirmarPassword.getPassword()).trim()))) {
                    JOptionPane.showMessageDialog(this, "Password y confirmar password deben de ser iguales ");
                    result = false;
                }
            }
        }
        return result; // retorna la variable result con el valor true o false para saber si los datos son validos o no
    }
    // metodo para obtener el mensaje de confirmacion de envio de informacion a la base de datos

    private int obtenerMensajeDeConfirmacion() {
        String mess = "¿Seguro que desea ";
        switch (opcionForm) {
            case FormEscOpcion.CREAR:
                mess += " Guardar?";
                break;
            case FormEscOpcion.MODIFICAR:
                mess += " Modificar?";
                break;
            case FormEscOpcion.ELIMINAR:
                mess += " Eliminar?";
                break;
            default:
                break;
        }
        // pedir confirmacion al usuario de la pantalla si desea enviar la informacion o no utilizando un showConfirmDialog
        int input = JOptionPane.showConfirmDialog(this, mess, "",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return input; // retornar la respuesta del usuario de la pantalla al utilizar el showConfirmDialog
    }

    // Metodo para llenar la entidad de usuario con la informacion que esta en la caja de texto del formulario FrmRolEsc
    private void llenarEntidadConLosDatosDeLosControles() {
        // Llenar la propiedad de Nombre,Apellido,Login,Password,Confirmar password,IdRol, Estatus
        //de la entidad de Usuario con los valores de las caja de texto y combo box
        this.usuarioActual.setNombre(this.txtNombre.getText());
        this.usuarioActual.setApellido(this.txtApellido.getText());
        this.usuarioActual.setLogin(this.txtLogin.getText());
        this.usuarioActual.setPassword(new String(this.txtPassword.getPassword()));
        this.usuarioActual.setConfirmPassword_aux(new String(this.txtConfirmarPassword.getPassword()));
        ItemsCombo itemRol = (ItemsCombo) cbRol.getSelectedItem();
        this.usuarioActual.setIdRol(itemRol.getId());
        ItemsCombo itemEstatus = (ItemsCombo) cbEstatus.getSelectedItem();
        this.usuarioActual.setEstatus((byte) itemEstatus.getId());
    }

    // metodo para cerrar el formulario FrmUsuarioEsc 
    private void cerrarFormulario(boolean pIsEvtClosing) {
        if (frmPadre != null) {
            frmPadre.setEnabled(true); // habilitar el formulario FrmUsuarioLec
            frmPadre.setVisible(true); // mostrar el formulario FrmUsuarioLec
        }
        if (pIsEvtClosing == false) { // verificar que no se este cerrando el formulario desde el evento formWindowClosing 
            this.setVisible(false); // Cerrar el formulario FrmRolEsc
            this.dispose(); // Cerrar todos los procesos abiertos en el formulario FrmRolEsc
        }
    }

    // metodo para enviar los datos a la base de datos segun la opcion de la propiedad opcionForm 
    private void enviarDatos() {
        try {
            if (validarDatos()) { // verificar si todos los datos obligatorios tienen informacion
                // verificar que el usuario de la pantalla presiono el boton YES
                if (obtenerMensajeDeConfirmacion() == JOptionPane.YES_OPTION) {
                    llenarEntidadConLosDatosDeLosControles(); // llenar la entidad de Usuario con los datos de la caja de texto,combo box del formulario
                    int resultado = 0;
                    switch (opcionForm) {
                        case FormEscOpcion.CREAR:
                            // si la propiedad opcionForm es CREAR guardar esos datos en la base de datos
                            resultado = UsuarioDAL.crear(this.usuarioActual);
                            break;
                        case FormEscOpcion.MODIFICAR:
                            // si la propiedad opcionForm es MODIFICAR actualizar esos datos en la base de datos
                            resultado = UsuarioDAL.modificar(this.usuarioActual);
                            break;
                        case FormEscOpcion.ELIMINAR:
                            // si la propiedad opcionForm es ELIMINAR entonces quitamos ese registro de la base de datos
                            resultado = UsuarioDAL.eliminar(this.usuarioActual);
                            break;
                        default:
                            break;
                    }
                    if (resultado != 0) {
                        // notificar al usuario que "Los datos fueron correctamente actualizados"
                        JOptionPane.showMessageDialog(this, "Los datos fueron correctamente actualizados");
                        if (frmPadre != null) {
                            // limpiar los datos de la tabla de datos del formulario FrmUsuarioLec
                            frmPadre.iniciarDatosDeLaTabla(new ArrayList());
                        }
                        this.cerrarFormulario(false); // Cerrar el formulario utilizando el metodo "cerrarFormulario"
                    } else {
                        // En el caso que las filas modificadas en la base de datos sean cero 
                        // mostrar el siguiente mensaje al usuario "Sucedio un error al momento de actualizar los datos"
                        JOptionPane.showMessageDialog(this, "Sucedio un error al momento de actualizar los datos");
                    }
                }
            }
        } catch (Exception ex) {
            // En el caso que suceda un error al ejecutar la consulta en la base de datos 
            // mostrar el siguiente mensaje al usuario "Sucedio un error al momento de actualizar los datos"
            JOptionPane.showMessageDialog(this, "Sucedio el siguiente error: " + ex.getMessage());
        }

    }

    // constructor de la clase FrmUsuarioEsc a utilizar en la clase FrmUsuarioLec
    // el constructor pide como parametros el Usuario, Opcion , formulario FrmUsuarioLec
    public FrmUsuarioEsc(Usuario pUsuario, int pOpcionForm, FrmUsuarioLec pFrmPadre) {
        initComponents();
        iniciarDatos(pUsuario, pOpcionForm, pFrmPadre); // Iniciar y obtener los datos de la base de datos para llenar los controles de este formulario
    }
// </editor-fold> 

    /**
     * Creates new form FrmUsuarioEsc
     */
    public FrmUsuarioEsc() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        txtConfirmarPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        cbEstatus = new javax.swing.JComboBox<>();
        btnOk = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Nombre *");

        jLabel2.setText("Apellido *");

        jLabel3.setText("Login *");

        jLabel4.setText("Rol *");

        jLabel5.setText("Password *");

        jLabel6.setText("Confirmar password *");

        jLabel7.setText("Estatus *");

        cbEstatus.setToolTipText("");

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancelar.setMnemonic('C');
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOk)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtConfirmarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(btnCancelar))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        this.enviarDatos();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.cerrarFormulario(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.cerrarFormulario(true);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnOk;
    private javax.swing.JComboBox<ItemsCombo> cbEstatus;
    private javax.swing.JComboBox<ItemsCombo> cbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtConfirmarPassword;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
