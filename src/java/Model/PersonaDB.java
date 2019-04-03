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
public class PersonaDB {
     public AccesoDatos accesoDatos = new AccesoDatos();
     
      public void Guardar(Persona obj) throws SNMPExceptions, SQLException {

        String strSQL = "";
        try {
            strSQL = "INSERT INTO [dbo].[Persona]([Id],[Nombre],"
                    + "[P_Apellido],[S_Apellido],[Contrasnna],[ID_IDENTIFICACION],"
                    + "[Id_Usu_Registra],[Fecha_Registra],[Id_Usu_Edita],[Fecha_Edita])"
                    + "VALUES(" + obj.getId() + ",'" + obj.getNombre() + "',"
                    + obj.getpApellido() + "," + obj.getsApellido() + ",'"
                    + obj.getContrasenna() + "'," + obj.getIdIdentificacion() + ","
                    + obj.getIdUsuRegistra() + ",'"
                    + obj.getFeRegistra() + "',"
                    + obj.getIdUsuEdita()+ ",'"
                    + obj.getFeEdita()+ "')";

            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
    }
       public LinkedList<Persona> SeleccionaTodos() throws SNMPExceptions, SQLException {
        String select = "";

        LinkedList<Persona> lista = new LinkedList<Persona>();

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            //Se instancia la clase de acceso a datos
//            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Nombre,P_Apellido,S_Apellido,"
                    + "Contrasenna,ID_IDENTIFICACION,"
                    + "Id_Usu_Registra, Fecha_Registra,"
                    + "Id_Usu_Edita,Fecha_Edita "
                    + "from dbo.Persona";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {

                int idPro = rsPA.getInt("id");
                String nombre = rsPA.getString("Nombre");
                String p_Apellido = rsPA.getString("P_Apellido");
                String s_Apellido = rsPA.getString("S_Apellido");
                String contrasenna = rsPA.getString("Contrasenna");

                int idIdentificacion = rsPA.getInt("id_Identificacion");
                int usuarioI = rsPA.getInt("Id_Usu_Registra");
                String fechaI = rsPA.getString("Fecha_Registra");
                int usuarioM = rsPA.getInt("Id_Usu_Edita");
                String fechaM = rsPA.getString("Fecha_Edita");

                Persona per = new Persona(idPro, nombre, p_Apellido, s_Apellido, contrasenna, idIdentificacion, usuarioI, fechaI, usuarioM, fechaM);
                
                    lista.add(per);
                
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

        return lista;
    }
      
     
      public Producto SeleccionarUno(int idProduct) throws SNMPExceptions, SQLException {

        String select = "";
        Producto obj = null;
        try {

            select = "Select * from Producto where Id =" + idProduct;
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            while (rsPA.next()) {
               int idPro = rsPA.getInt("id");
                String nombre = rsPA.getString("Nombre");
                String p_Apellido = rsPA.getString("Primer apellido");
                String s_Apellido = rsPA.getString("Segundo apellido");
                String contrasenna = rsPA.getString("Contraseña");

                int idIdentificacion = rsPA.getInt("id_Identificacion");
                int usuarioI = rsPA.getInt("Id_Usu_Registra");
                String fechaI = rsPA.getString("Fecha_Registra");
                int usuarioM = rsPA.getInt("Id_Usu_Edita");
                String fechaM = rsPA.getString("Fecha_Edita");

                obj= new Persona (idPro, nombre, p_Apellido, s_Apellido, contrasenna, idIdentificacion, usuarioI, fechaI, usuarioM, fechaM,);

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
        return obj;
    }
       public void Desactivar(int idPer, int estp) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String desactivar = "";
        desactivar = "UPDATE Producto SET Estado=" + estp + " Where id = " + idPer;
        accesoDatos.ejecutaSQL(desactivar);

    }
       public void Actualizar(Persona obj) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        //Se crea la sentencia de actualización
        String update = "";
        try {
            update = "UPDATE Persona SET Nombre = '" + obj.getNombre()
                    + "',P_Apellido= " + obj.getpApellido()
                    + ",S_Apellido = " + obj.getsApellido()
                    + ",contrasenna '" + obj.getContrasenna()
                    + "',Id_Usu_Edita = " + obj.getIdUsuEdita()
                   
                    + "'where Id = " + obj.getId();
            //Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(update);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {
            accesoDatos.cerrarConexion();
        }

    }
}
