package controlcontacto.accesoadatos;
//importaciones de objetos a ocupar 
import java.util.*;
import java.sql.*;
import controlcontactos.entidadesdenegocio.*;

public class ProductoDAL {
     static String obtenerCampos() {
        return "p.Id,p.Nproducto,p.Caracteristica,p.Presio";
    }

    private static String obtenerSelect(Producto pProducto) {
        String sql;
        sql = "SELECT ";
        if (pProducto.getTop_aux() > 0 && ComunBD.TIPODB == ComunBD.TipoDB.SQLSERVER) {
            sql += "TOP " + pProducto.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " FROM Producto p");
        return sql;
    }

    private static String agregarOrderBy(Producto pProducto) {
        String sql = " ORDER BY p.Id DESC";
        if (pProducto.getTop_aux() > 0 && ComunBD.TIPODB == ComunBD.TipoDB.MYSQL) {
            sql += " LIMIT " + pProducto.getTop_aux() + " ";
        }
        return sql;
    }
//metodo para crear
    public static int crear(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunBD.obtenerConexion();) { 
            sql = "INSERT INTO Producto(Nproducto,Caracteristica,Presio) VALUES(?,?,?)";
            try (PreparedStatement ps = ComunBD.createPreparedStatement(conn, sql);) { 
                ps.setString(1, pProducto.getNproducto()); 
                ps.setString(2, pProducto.getCaracteristica()); 
                ps.setString(3, pProducto.getPresio()); 
                result = ps.executeUpdate(); 
                ps.close(); 
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
        return result;
    }

    // Metodo para poder actualizar un registro en la tabla de Producto
    public static int modificar(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunBD.obtenerConexion();) { 
            sql = "UPDATE Producto SET Nproducto=?,Caracteristica=?,Presio=? WHERE Id=?"; 
            try (PreparedStatement ps = ComunBD.createPreparedStatement(conn, sql);) {
                ps.setString(1, pProducto.getNproducto()); 
                ps.setString(2, pProducto.getCaracteristica());
                ps.setString(3, pProducto.getPresio());
                ps.setInt(4,pProducto.getId());
                result = ps.executeUpdate(); 
                ps.close(); 
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex; 
        }
        return result;
    }

    // Metodo para poder eliminar un registro en la tabla de Producto
    public static int eliminar(Producto pProducto) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunBD.obtenerConexion();) {
            sql = "DELETE FROM Producto WHERE Id=?"; 
            try (PreparedStatement ps = ComunBD.createPreparedStatement(conn, sql);) { 
                ps.setInt(1, pProducto.getId()); 
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex; 
            }
            conn.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
        return result;
    }    
    
    // Metodo para llenar las propiedades de la entidad de Producto con los datos que viene en el ResultSet,
    // el metodo nos ayudara a no preocuparlos por los indices al momento de obtener los valores del ResultSet
    static int asignarDatosResultSet(Producto pProducto, ResultSet pResultSet, int pIndex) throws Exception {
        //  SELECT r.Id(indice 1),r.Nombre(indice 2) * FROM Rol
        pIndex++;
        pProducto.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pProducto.setNproducto(pResultSet.getString(pIndex)); // index 2
        pIndex++;
        pProducto.setCaracteristica(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pProducto.setPresio(pResultSet.getString(pIndex)); // index 4
        
        return pIndex;
    }
    
    // Metodo para  ejecutar el ResultSet de la consulta SELECT a la tabla de Producto 
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Producto> pProducto) throws Exception {
        try (ResultSet resultSet = ComunBD.obtenerResultSet(pPS);) { 
            while (resultSet.next()) {
                Producto producto = new Producto(); 
                asignarDatosResultSet(producto, resultSet, 0); 
                pProducto.add(producto); 
            }
            resultSet.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
    }
    
   // Metodo para obtener por Id un registro de la tabla de Producto 
    public static Producto obtenerPorId(Producto pProducto) throws Exception {
        Producto producto = new Producto();
        ArrayList<Producto> Productos = new ArrayList();
        try (Connection conn = ComunBD.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProducto); // Obtener la consulta SELECT de la tabla Producto
            sql += " WHERE p.Id=?"; // Concatenar a la consulta SELECT de la tabla Producto el WHERE 
            try (PreparedStatement ps = ComunBD.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                ps.setInt(1, pProducto.getId()); // Agregar el parametro a la consulta donde estan el simbolo ? #1 
                obtenerDatos(ps, Productos); // Llenar el ArrayList de Rol con las fila que devolvera la consulta SELECT a la tabla de Producto
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close();  // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        if (Productos.size() > 0) { // Verificar si el ArrayList de Producto trae mas de un registro en tal caso solo debe de traer uno
            producto = Productos.get(0); // Si el ArrayList de Producto trae un registro o mas obtenemos solo el primero 
        }
        return producto; // Devolver el Producto encontrado por Id 
    }

    // Metodo para obtener todos los registro de la tabla de Producto
    public static ArrayList<Producto> obtenerTodos() throws Exception {
        ArrayList<Producto> Productos;
        Productos = new ArrayList<>();
        try (Connection conn = ComunBD.obtenerConexion();) {// Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(new Producto());  // Obtener la consulta SELECT de la tabla Producto
            sql += agregarOrderBy(new Producto());  // Concatenar a la consulta SELECT de la tabla Rol el ORDER BY por Id 
            try (PreparedStatement ps = ComunBD.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                obtenerDatos(ps, Productos); // Llenar el ArrayList de Producto con las fila que devolvera la consulta SELECT a la tabla de Producto
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex; // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        } 
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return Productos; // Devolver el ArrayList de Producto
    }
    
    // Metodo para asignar los filtros de la consulta SELECT de la tabla de Producto de forma dinamica
    static void querySelect(Producto pProducto, ComunBD.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement(); // Obtener el PreparedStatement al cual aplicar los parametros
        if (pProducto.getId() > 0) { // Verificar si se va incluir el campo Id en el filtro de la consulta SELECT de la tabla de Producto
            pUtilQuery.AgregarWhereAnd(" p.Id=? "); // Agregar el campo Id al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) { 
                // Agregar el parametro del campo Id a la consulta SELECT de la tabla de Producto
                statement.setInt(pUtilQuery.getNumWhere(), pProducto.getId()); 
            }
        }
        // Verificar si se va incluir el campo Nombre en el filtro de la consulta SELECT de la tabla de Producto
        if (pProducto.getNproducto() != null && pProducto.getNproducto().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Nproducto LIKE ?"); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getNproducto() + "%"); 
            }
        }
         if (pProducto.getCaracteristica()!= null && pProducto.getCaracteristica().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Caracteristica LIKE ?"); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getCaracteristica()+ "%"); 
            }
        }
          if (pProducto.getPresio()!= null && pProducto.getPresio().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" p.Presio LIKE ?"); // Agregar el campo Nombre al filtro de la consulta SELECT y agregar en el WHERE o AND
            if (statement != null) {
                // Agregar el parametro del campo Nombre a la consulta SELECT de la tabla de Producto
                statement.setString(pUtilQuery.getNumWhere(), "%" + pProducto.getPresio() + "%"); 
            }
        }
    }

     // Metodo para obtener todos los registro de la tabla de Producto que cumplan con los filtros agregados 
     // a la consulta SELECT de la tabla de Producto 
    public static ArrayList<Producto> buscar(Producto pProductol) throws Exception {
        ArrayList<Producto> Productos = new ArrayList();
        try (Connection conn = ComunBD.obtenerConexion();) { // Obtener la conexion desde la clase ComunDB y encerrarla en try para cierre automatico
            String sql = obtenerSelect(pProductol); // Obtener la consulta SELECT de la tabla Producto
            ComunBD comundb = new ComunBD();
            ComunBD.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pProductol, utilQuery); // Asignar el filtro a la consulta SELECT de la tabla de Producto 
            sql = utilQuery.getSQL(); 
            sql += agregarOrderBy(pProductol); // Concatenar a la consulta SELECT de la tabla Producto el ORDER BY por Id
            try (PreparedStatement ps = ComunBD.createPreparedStatement(conn, sql);) { // Obtener el PreparedStatement desde la clase ComunDB
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pProductol, utilQuery);  // Asignar los parametros al PreparedStatement de la consulta SELECT de la tabla de Producto
                obtenerDatos(ps, Productos); // Llenar el ArrayList de Producto con las fila que devolvera la consulta SELECT a la tabla de Producto
                ps.close(); // Cerrar el PreparedStatement
            } catch (SQLException ex) {
                throw ex;  // Enviar al siguiente metodo el error al ejecutar PreparedStatement en el caso que suceda
            }
            conn.close(); // Cerrar la conexion a la base de datos
        }
        catch (SQLException ex) {
            throw ex; // Enviar al siguiente metodo el error al obtener la conexion  de la clase ComunDB en el caso que suceda
        }
        return Productos; // Devolver el ArrayList de Producto
    }
}
