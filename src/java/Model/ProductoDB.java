/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Anyel
 */
class ProductoDB {
     private AccesoDatos accesoDatos = new AccesoDatos();
    LinkedList<Producto> listaProductos = new LinkedList<Producto>();
    
     public void insertaProducto(Producto productoP) throws SNMPExceptions, SQLException {
    
        String strSQL = "";
        try {
            //Se obtienen los valores del objeto Empresa
            Producto prod = new Producto();
            prod = productoP;
            strSQL = 
                    "INSERT INTO Producto ( Id,Nombre,Precio,Cantidad_Min_Compra,Precio,"
                    + "Fotograbia,Estado,Id_Usu_Registra, Fecha_Registra,Id_Usu_Edita,Fecha_Edita) VALUES ('" +prod.getNombreProducto()
                    + "','" +prod.getPrecio()+"','" +prod.getCantidadMinima()+"','" 
                    +"','" +prod.getFechaRegistro()+"','" +prod.getFechaModificacion()+"','" +prod.getUsuarioRegistra()+"','" +prod.getUsuarioModifica()+
                    "','" +prod.getImagen()+"')";
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL/*, sqlBitacora*/);
        
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                    e.getMessage(), e.getErrorCode());
        }catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                    e.getMessage());
        } finally {
        
        }
    }
}

