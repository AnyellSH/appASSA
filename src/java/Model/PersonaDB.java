/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.SQLException;

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
}
