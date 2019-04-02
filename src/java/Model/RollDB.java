/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author Pablo Mora
 */
public class RollDB {

    private AccesoDatos accesoDatos = new AccesoDatos();

    public RollDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

    }

    public RollDB() {
    }

    /*SELECCIONAR TODOS*/
    public LinkedList<Roll> SeleccionarTodos() throws SNMPExceptions, SQLException {

        LinkedList<Roll> otraLista = new LinkedList<Roll>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select Id, Estado, Descripcion,"
                    + "Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita "
                    + " from Roll";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String decrip = rsPA.getString("Descripcion");
                int est = rsPA.getInt("Estado");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                String feRegistra = rsPA.getString("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                String feEdita = rsPA.getString("Fecha_Edita");

                Roll Obj = new Roll(id, decrip, est, idUsuRegistra, feRegistra, idUsuEdita, feEdita);

                if (Obj.estado ==1) {
                    otraLista.add(Obj);
                }

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
        return otraLista;
    }

    /*SELECCIONAR UNO DE TABLA ID*/
    public Roll SeleccionarUno(int idp) throws SNMPExceptions, SQLException {
        Roll Obj = null;
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select Id, Estado, Descripcion,"
                    + "Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita "
                    + "from Roll where id = " + idp;

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String decrip = rsPA.getString("Descripcion");
                int est = rsPA.getInt("Estado");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                String feRegistra = rsPA.getString("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                String feEdita = rsPA.getString("Fecha_Edita");

                Obj = new Roll(id, decrip, est, idUsuRegistra, feRegistra, idUsuEdita, feEdita);
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
        return Obj;

    }

    /*SELECCIONAR TODOS LOS DESACTIVADOS*/
    public LinkedList<Roll> SeleccionaTodosDesactivados() throws SNMPExceptions, SQLException   {
        String select = "";

        LinkedList<Roll> lista = new LinkedList<Roll>();

        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            //Se instancia la clase de acceso a datos
//            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT Id,Descripcion,"
                    + "Estado,"
                    + "Id_Usu_Registra, Fecha_Registra,"
                    + "Id_Usu_Edita,Fecha_Edita "
                    + "from dbo.Roll";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //Se llena el arryaList con los catálogos   
            while (rsPA.next()) {

                int idPro = rsPA.getInt("id");
                String nombre = rsPA.getString("Descripcion");
                int estado = rsPA.getInt("Estado");

                int usuarioI = rsPA.getInt("Id_Usu_Registra");
                String fechaI = rsPA.getString("Fecha_Registra");
                int usuarioM = rsPA.getInt("Id_Usu_Edita");
                String fechaM = rsPA.getString("Fecha_Edita");

                Roll obj = new Roll(idPro, nombre, estado, usuarioI, fechaI, usuarioM, fechaM);
                if (obj.getEstado() == 0) {
                    lista.add(obj);
                }
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

    /*GUARDAR EN LA TABLA*/
    public void Guardar(Roll roll) throws SNMPExceptions, SQLException {

        LinkedList<Roll> otraLista = new LinkedList<Roll>();
        String insert = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            insert = "INSERT INTO ROLL(Id, Estado, Descripcion, Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita)"
                    + "VALUES (" + roll.getId()
                    + "," + roll.getEstado() + ",'"
                    + roll.getDescripcion() + "',"
                    + roll.getIdUsuRegistra() + ",'"
                    + roll.getFeRegistra() + "',"
                    + roll.getIdUsuEdita() + ",'"
                    + roll.getFeEdita() + "')";

            accesoDatos.ejecutaSQLRetornaRS(insert);

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

    /*ACTUALIZAR UNO DE LA TABLA ID*/
    public void Actualizar(Roll roll) throws SNMPExceptions, SQLException {

        LinkedList<Roll> otraLista = new LinkedList<Roll>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Update dbo.ROLL SET Descripcion = '" + roll.getDescripcion()
                    + "',Id_Usu_Edita =" + roll.getIdUsuEdita()
                    + ",Fecha_Edita = '" + roll.getFeEdita()
                    + "' where id = " + roll.getId();

            accesoDatos.ejecutaSQLRetornaRS(select);

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

    /*DESACTIVAR UNO DE LA TABLA POR ID*/
    public void Desactivar(int idp, int estp) throws SNMPExceptions, SQLException {

        LinkedList<Telefono> otraLista = new LinkedList<Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Update Roll set estado=" + estp + " where id= " + idp;
            accesoDatos.ejecutaSQL(select);

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
