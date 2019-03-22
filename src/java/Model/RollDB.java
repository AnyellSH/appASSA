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
                    + "Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita"
                    + "from Roll";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String decrip = rsPA.getString("Descripcion");
                int est = rsPA.getInt("Estado");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                Date feRegistra = rsPA.getDate("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                Date feEdita = rsPA.getDate("Fecha_Edita");

                Roll Obj = new Roll(id, decrip, est, idUsuRegistra, feRegistra, idUsuEdita, feEdita);

                otraLista.add(Obj);

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
    public LinkedList<Roll> SeleccionarUno(int idp) throws SNMPExceptions, SQLException {
        LinkedList<Roll> otraLista = new LinkedList<Roll>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select Id, Estado, Descripcion,"
                    + "Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita"
                    + "from Roll where id = " + idp;

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String decrip = rsPA.getString("Descripcion");
                int est = rsPA.getInt("Estado");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                Date feRegistra = rsPA.getDate("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                Date feEdita = rsPA.getDate("Fecha_Edita");

                Roll Obj = new Roll(id, decrip, est, idUsuRegistra, feRegistra, idUsuEdita, feEdita);

                otraLista.add(Obj);
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

    /*GUARDAR EN LA TABLA*/
    public void Guardar(Roll roll) throws SNMPExceptions, SQLException {

        LinkedList<Roll> otraLista = new LinkedList<Roll>();
        String insert = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            insert = "INSER INTO ROLL(Id, Estado, Descripcion, Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita)"
                    + "VALUES (" + roll.getId() + "," + roll.getEstado() + ",'" + roll.getDescripcion() + "')";

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

            select = "Update Roll set Estado = " + roll.getEstado() + "where id = " + roll.getId();

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
    public void Desactivar(Roll roll) throws SNMPExceptions, SQLException {

        LinkedList<Telefono> otraLista = new LinkedList<Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Update Telefono set estado=" + roll.getEstado() + " where id= " + roll.getId();
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
