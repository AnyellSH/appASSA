/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author Anyel
 */
public class ProductoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    LinkedList<Producto> listaProductos = new LinkedList<Producto>();

    public void insertaProducto(Producto productoP) throws SNMPExceptions, SQLException {

        String strSQL = "";
        try {
            //Se obtienen los valores del objeto Empresa
            Producto prod = new Producto();
            prod = productoP;
            strSQL
                    = "INSERT INTO Producto ( Id,Nombre,Precio,Cantidad_Min_Compra,Precio,"
                    + "Fotograbia,Estado,Id_Usu_Registra, Fecha_Registra,Id_Usu_Edita,Fecha_Edita) VALUES ('" + prod.getNombreProducto()
                    + "','" + prod.getPrecio() + "','" + prod.getCantidadMinima() + "','"
                    + "','" + prod.getFechaRegistro() + "','" + prod.getFechaModificacion() + "','" + prod.getUsuarioRegistra() + "','" + prod.getUsuarioModifica()
                    + "','" + prod.getImagen() + "')";
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL/*, sqlBitacora*/);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
    }

    public LinkedList<Producto> consultarProductos() throws SNMPExceptions, SQLException {
        String select = "";

        LinkedList<Producto> listaProduct = new LinkedList<Producto>();

        try {
            //open();
            //Se instancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Nombre,Precio,Cantidad_Min_Compra,Precio,"
                    + "Fotograbia,Estado,Id_Usu_Registra, Fecha_Registra,Id_Usu_Edita,Fecha_Edita";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {

                int idPro = rsPA.getInt("IdProducto");
                String nombre = rsPA.getString("Nombre");
                float precio = rsPA.getFloat("Precio");

                int cantidadM = rsPA.getInt("CantidadMinima");

                String fechaI = rsPA.getString("FechaRegistra");
                String fechaM = rsPA.getString("FechaModificacion");
                int usuarioI = rsPA.getInt("UsuarioRegistra");
                int usuarioM = rsPA.getInt("UsuarioModifico");

                String img = rsPA.getString("Imagen");

                Producto pro = new Producto(idPro, nombre, precio, cantidadM, fechaI, fechaM, usuarioI, usuarioM, img);
                listaProduct.add(pro);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }

        return listaProduct;
    }

    public LinkedList<Producto> consultaUnProducto(int idProduct) throws SNMPExceptions, SQLException {

        String strSQL = "";
        LinkedList<Producto> unPro = new LinkedList<Producto>();
        try {

            strSQL
                    = "Select * from Producto where Id =" + idProduct;
            //Se ejecuta la sentencia SQL
            ResultSet rsEM = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            while (rsEM.next()) {
                int idPro = rsEM.getInt("IdProducto");
                String nombre = rsEM.getString("Nombre");
                float precio = rsEM.getFloat("Precio");

                int cantidadM = rsEM.getInt("CantidadMinima");

                String fechaI = rsEM.getString("FechaRegistra");
                String fechaM = rsEM.getString("fechaModificacion");
                int usuarioI = rsEM.getInt("UsuarioRegistra");
                int usuarioM = rsEM.getInt("UsuarioModifico");

                String img = rsEM.getString("Imagen");

                Producto pro = new Producto(idPro, nombre, precio, cantidadM, fechaI, fechaM, usuarioI, usuarioM, img);
                unPro.add(pro);
            }
            rsEM.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
        }
        return unPro;
    }

    public void eliminarProducto(int idProduct) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String delete
                = "DELETE FROM Producto Where IdProducto = " + idProduct;
        accesoDatos.ejecutaSQL(delete);

    }
    
    
    public void ActualizarProducto(Producto productoP) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
           //Se obtienen los valores del objeto Cliente
           Producto p = new Producto();
           p = productoP;
           
           //Datos de CLiente         
                int idPro = p.getIdProducto();
                String nombre = p.getNombreProducto();
                float precio = p.getPrecio();
                
                int cantidadM = p.getCantidadMinima();
                String descripcion = p.getDescripcion();
                String fechaI = p.getFechaRegistro();
                String fechaM = p.getFechaModificacion();
                int usuarioI = p.getUsuarioRegistra();
                int usuarioM = p.getUsuarioModifica();
               
                String im = p.getImagen();
           //Se crea la sentencia de actualización
           String update = 
                   "UPDATE Producto SET Nombre = '" + nombre + "', Precio='"+precio+
                  " CantidadMinima='"+cantidadM+"', Descripcion='"+descripcion+
                   "', FechaRegistro='"+fechaI+"', fechaModificacion='"+fechaM+"',"
                   + "UsuarioRegistro='"+usuarioI+"', UsuarioModifico='"+usuarioM+"', Imagen='"+im+"'where IdProducto = "+idPro;
           //Se ejecuta la sentencia SQL
           accesoDatos.ejecutaSQL(update);
               
     }
}
