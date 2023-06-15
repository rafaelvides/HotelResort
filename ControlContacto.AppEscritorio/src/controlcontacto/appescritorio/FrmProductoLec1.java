package controlcontacto.appescritorio;

// Importaciones para el funcionamiento de la pantalla de mantenimiento de Producto
import controlcontacto.appescritorio.utils.*; // importar todas las clases de utilidades de la aplicaciones escritorio
import controlcontacto.accesoadatos.*; // importar todas la clases de la capa de acceso a datos
import controlcontactos.entidadesdenegocio.*; // importar todas la clases de la capa de entidades de negocio
import java.util.ArrayList; // importar el ArrayList para recibir la lista de Roles de la base de datos
import javax.swing.JOptionPane; // importa la clase JOptionPane para mostrar alertas o advertencias a los usuarios
import javax.swing.table.DefaultTableModel; // importar la clase DefaultTableModel para llenar los datos de la tabla

public class FrmProductoLec1 extends javax.swing.JFrame {

   
    // <editor-fold defaultstate="collapsed" desc="Codigo para las clases,propiedades y metodos locales del formulario FrmRolLec">
    private javax.swing.JFrame frmPadre; // Propiedad para almacenar la pantalla de Inicio del sistema

    // Crear la clase anidada ColumnaTabla para saber la posicion de las columnas en la tabla de datos
    public class ColumnaTabla {

        static final int Id = 0; // El campo Id sera  la primera columna en la tabla de datos
        static final int Nproducto = 1; // El campo Nombre sera  la segunda columna en la tabla de datos
         static final int Caracteristica= 2; // El campo Nombre sera  la segunda columna en la tabla de datos
          static final int Presio = 3; // El campo Nombre sera  la segunda columna en la tabla de datos
    }

    // Metodo para ocultar columnas de nuestra tabla de datos
    private void ocultarColumnasDeLaTabla(int pColumna) {
        this.tbPro.getColumnModel().getColumn(pColumna).setMaxWidth(0); // le dejamos en el ancho maximo de la tabla en cero en la columna
        this.tbPro.getColumnModel().getColumn(pColumna).setMinWidth(0);// le dejamos en el ancho minimo de la tabla en cero  en la columna
        // le dejamos en el ancho maximo de la tabla en cero en el header
        this.tbPro.getTableHeader().getColumnModel().getColumn(pColumna).setMaxWidth(0);
        // le dejamos en el ancho minimo de la tabla en cero  en el header
        this.tbPro.getTableHeader().getColumnModel().getColumn(pColumna).setMinWidth(0);
    }

    // Metodo para iniciar los datos de la tabla a partir de una lista de Roles
    public void iniciarDatosDeLaTabla(ArrayList<Producto> pProducto) {
        // Iniciamos el DefaultTableModel utilizaremos para crear las columnas y los datos en nuestra tabla    
        DefaultTableModel model = new DefaultTableModel() {
            // aplicamos override al metodo isCellEditable para deshabilitar la edicion en la filas de la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // retornamos false para deshabilitar todas las fila y no puedan ser editables en la tabla de datos
            }
        };
        model.addColumn("Id"); // agregar la columna Id a la tabla de datos 
        model.addColumn("Nobre Prod."); // agregar la columna Nombre a la tabla de Datos           
        model.addColumn("Caracteristica"); // agregar la columna Nombre a la tabla de Datos           
        model.addColumn("Precio"); // agregar la columna Nombre a la tabla de Datos           
        this.tbPro.setModel(model); // iniciar el DefaultTableModel en nuestra tabla de datos
        Object row[] = null; // crear un array de un objecto para iniciar los datos de la fila
        for (int i = 0; i < pProducto.size(); i++) { // Recorrer el array de Productos
            Producto producto = pProducto.get(i);  // Obtener un Producto de array de Roles por su indice
            model.addRow(row); // Iniciar una fila vacia en la tabla de datos
            model.setValueAt(producto.getId(), i, ColumnaTabla.Id); // agregar el valor de la columna Id en la fila
            model.setValueAt(producto.getNproducto(), i, ColumnaTabla.Nproducto); // agregar el valor de la columna Nombre en la fila
            model.setValueAt(producto.getCaracteristica(), i, ColumnaTabla.Caracteristica);
             model.setValueAt(producto.getPresio(), i, ColumnaTabla.Presio);
        }
        ocultarColumnasDeLaTabla(ColumnaTabla.Id); // Ocultar la columna de Id en la tabla 
    }
 
    // Metodo para llenar la clase de Producto con los datos que tiene la fila seleccionada de la tabla
    private boolean llenarEntidadConLaFilaSeleccionadaDeLaTabla(Producto pProducto) {
        int filaSelect; // variable para almacenar el indice de la fila seleccionada
        boolean isSelectRow = false; // variable para saber si esta seleccionada una fila o no
        filaSelect = this.tbPro.getSelectedRow(); // obtener el indice de la fila seleccionada
        if (filaSelect != -1) { // verificar que se ha seleccionado una fila el cual la variable filaSelect debe ser diferente a -1
            isSelectRow = true; // colocar en true la variable isSelectRow porque si esta seleccionada una fila
            int id = (int) this.tbPro.getValueAt(filaSelect, ColumnaTabla.Id); // obtener el valor de la fila en la columna Id
            String Nproducto = (String) this.tbPro.getValueAt(filaSelect, ColumnaTabla.Nproducto);// obtener el valor de la fila en la columna Nombre
            String Caracteristica = (String) this.tbPro.getValueAt(filaSelect, ColumnaTabla.Caracteristica);
            String Precio = (String) this.tbPro.getValueAt(filaSelect, ColumnaTabla.Presio);
            pProducto.setId(id); // Llenar propiedad de Id de Producto con el valor obtenido de la fila de la tabla 
            pProducto.setNproducto(Nproducto); // Llenar propiedad de Nombre de Producto con el valor obtenido de la fila de la tabla 
            pProducto.setCaracteristica(Caracteristica);
            pProducto.setPresio(Precio);
        }
        return isSelectRow; // devolver el valor de isSelectRow 
    }

    // El metodo abrirFormularioDeEscritura lo utilizaremos para abrir el formulario de FrmRolEsc
    private void abrirFormularioDeEscritura(int pOpcionForm) {
        Producto producto = new Producto(); // Crear una instancia de la clase de Producto
        // Verificar si pOpcionForm es Crear abrimos el formulario de FrmRolEsc
        // en el caso que la pOpcionForm sea diferente a Crear, se va a verificar el metodo de  llenarEntidadConLaFilaSeleccionadaDeLaTabla
        // para llenar la instancia de Producto y verificar que este seleccionada una fila en la tabla de datos
        if (pOpcionForm == FormEscOpcion.CREAR || this.llenarEntidadConLaFilaSeleccionadaDeLaTabla(producto)) {
            // Abrir el formulario FrmRolEsc utilizando el contructor lleno con los parametros de Producto,OpcionForm y enviando el
            // formulario actual de FrmRolLec
            FrmProductoEsc1 frmRolEsc = new FrmProductoEsc1(producto, pOpcionForm, this);
            frmRolEsc.setVisible(true); // Mostrar el formulario FrmRolEsc
            this.setVisible(false); // ocultar el formulario FrmRolLec
        } else {
            // en el caso que pOpcionForm sea diferente a Crear y el metodo llenarEntidadConLaFilaSeleccionadaDeLaTabla devuelva false
            // se le notificara al usuario del sistema que debe de seleccionar una fila de la tabla 
            JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fila.");
        }

    }

    // metodo que se utilizara para buscar los datos en la base de datos al dar click en el boton de buscar
    private void buscar() {
        try {
            Producto productoSearch = new Producto();
            // llenar la propiedad de Top de la instancia de Producto con el valor seleccionado en el combobox cbTop
            productoSearch.setTop_aux(TopRegistro.obtenerValorSeleccionado(cbTop));
            // llenar la propiedad Nombre de la instancia de Producto con el valor de caja de texto txtNombre
            productoSearch.setNproducto(this.txtNombre1.getText());
            productoSearch.setCaracteristica(this.txtCaracteristica.getText());
            productoSearch.setPresio(this.txtPrecio.getText());
            ArrayList<Producto> productos = ProductoDAL.buscar(productoSearch); // buscar el Producto en la base de datps
            iniciarDatosDeLaTabla(productos); // iniciar la tabla con los datos obtenidos en el metodo de buscar de la DAL de Producto
        } catch (Exception ex) {
            // mostrar un error al usuario de pantalla en el caso que suceda al momento de obtener los datos
            JOptionPane.showMessageDialog(this, "Sucedio el siguiente error: " + ex.getMessage());
        }
    }

    // limpiar los controles que tienen la informacion a enviar a buscar los Productos en la base de datos
    private void limpiarControles() {
        this.txtNombre1.setText(""); // limpiar la caja de texto txtNombre
         this.txtCaracteristica.setText("");
          this.txtPrecio.setText("");
        TopRegistro.limpiarTopRegistro(cbTop); // iniciar el combo box de cbTop al valor 10
    }

    // cerrar el formulario de FrmRolLect
    private void cerrarFormulario(boolean pIsEvtClosing) {
        if (frmPadre != null) {
            frmPadre.setEnabled(true); // habilitar el formulario de Inicio
        }
        if (pIsEvtClosing == false) {
            this.setVisible(false); // cerrar el formulario de FrmProductoLec
            this.dispose(); // cerrar el formulario de FrmProductoLec
        }
    }

    // contructor de la clae FrmProductoLec que pide como parametro un JFrame
    // en nuestro caso pedira como parametro el formulario FrmInicio
    public FrmProductoLec1(javax.swing.JFrame pFrmPadre) {
        initComponents();
        frmPadre = pFrmPadre;
        pFrmPadre.setEnabled(true); // deshabilitar el formulario FrmInicio
        TopRegistro.llenarDatos(cbTop); // iniciar los datos del combo box cbTop con los valores a enviar en el top registro 
    }
// </editor-fold> 

    
    public FrmProductoLec1() {
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

        pnlBg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        cbTop = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnModificar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtNombre1 = new javax.swing.JTextField();
        txtCaracteristica = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbPro = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlBg.setBackground(new java.awt.Color(255, 255, 255));
        pnlBg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Caracteristica:");
        pnlBg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        txtPrecio.setBackground(new java.awt.Color(0, 204, 204));
        txtPrecio.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(0, 0, 0));
        txtPrecio.setText("$");
        txtPrecio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlBg.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 140, 30));

        cbTop.setBackground(new java.awt.Color(0, 204, 204));
        cbTop.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbTop.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cbTop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTopActionPerformed(evt);
            }
        });
        pnlBg.add(cbTop, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 140, 30));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Top:");
        pnlBg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, -1, -1));

        btnLimpiar.setBackground(new java.awt.Color(0, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(0, 0, 0));
        btnLimpiar.setMnemonic('L');
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlBg.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 110, 40));

        btnBuscar.setBackground(new java.awt.Color(0, 204, 204));
        btnBuscar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setMnemonic('B');
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlBg.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 110, 40));

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnModificar.setBackground(new java.awt.Color(0, 102, 153));
        btnModificar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setMnemonic('M');
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 100, 40));

        btnVer.setBackground(new java.awt.Color(0, 102, 153));
        btnVer.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setMnemonic('V');
        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });
        jPanel1.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 100, 40));

        btnEliminar.setBackground(new java.awt.Color(0, 102, 153));
        btnEliminar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setMnemonic('E');
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 100, 40));

        btnCerrar.setBackground(new java.awt.Color(0, 102, 153));
        btnCerrar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setMnemonic('C');
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 100, 40));

        btnNuevo.setBackground(new java.awt.Color(0, 102, 153));
        btnNuevo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setMnemonic('N');
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 100, 40));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/controlcontacto/appescritorio/images/bd.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 220, 220));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/controlcontacto/appescritorio/images/Produc.jpg"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 240, 110));

        pnlBg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, 240, 500));
        pnlBg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 220, -1));
        pnlBg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 140, -1));

        txtNombre1.setBackground(new java.awt.Color(0, 204, 204));
        txtNombre1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombre1.setBorder(new javax.swing.border.MatteBorder(null));
        pnlBg.add(txtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 220, 30));

        txtCaracteristica.setBackground(new java.awt.Color(0, 204, 204));
        txtCaracteristica.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtCaracteristica.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlBg.add(txtCaracteristica, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 220, 30));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Precio:");
        pnlBg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Nombre de producto:");
        pnlBg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        tbPro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbPro);

        pnlBg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 520, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTopActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        this.limpiarControles();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        this.abrirFormularioDeEscritura(FormEscOpcion.MODIFICAR);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // TODO add your handling code here:
        this.abrirFormularioDeEscritura(FormEscOpcion.VER);
    }//GEN-LAST:event_btnVerActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        this.abrirFormularioDeEscritura(FormEscOpcion.ELIMINAR);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.cerrarFormulario(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        this.abrirFormularioDeEscritura(FormEscOpcion.CREAR);
    }//GEN-LAST:event_btnNuevoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmProductoLec1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProductoLec1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProductoLec1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProductoLec1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmProductoLec1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<ItemsCombo> cbTop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pnlBg;
    private javax.swing.JTable tbPro;
    private javax.swing.JTextField txtCaracteristica;
    private javax.swing.JTextField txtNombre1;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
