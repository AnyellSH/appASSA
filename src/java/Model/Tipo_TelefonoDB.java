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
import java.util.LinkedList;

/**
 *
 * @author Pablo Mora
 */
public class Tipo_TelefonoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
//    private Connection conn;
//    LinkedList<Tipo_Telefono> lista = new LinkedList<Tipo_Telefono>();

    public Tipo_TelefonoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

    }

    public Tipo_TelefonoDB() {
    }

    /*SELECCIONAR TODOS*/
    public LinkedList<Tipo_Telefono> SeleccionarTodos() throws SNMPExceptions, SQLException {

        LinkedList<Tipo_Telefono> otraLista = new LinkedList<Tipo_Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select Id,Descripcion, Estado from Tipo_Telefono";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String desc = rsPA.getString("Descripcion");
                int est = rsPA.getInt("Estado");

                Tipo_Telefono Obj = new Tipo_Telefono(id, desc, est);

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
    public LinkedList<Tipo_Telefono> SeleccionarUno(int idp) throws SNMPExceptions, SQLException {

        LinkedList<Tipo_Telefono> otraLista = new LinkedList<Tipo_Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select Id,Descripcion, Estado from Tipo_Telefono where id= " + idp;

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String desc = rsPA.getString("Descripcion");
                int est = rsPA.getInt("Estado");

                Tipo_Telefono Obj = new Tipo_Telefono(id, desc, est);

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

    /*ACTUALIZAR UNO DE LA TABLA ID*/
    public void Actualizar(Tipo_Telefono tipo) throws SNMPExceptions, SQLException {

        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Update Tipo_Telefono set Descripcion='" + tipo.getDescripcion() + "' where id= " + tipo.getId();

            accesoDatos.ejecutaSQL(select);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
    }

    /*DESACTIVAR UNO DE LA TABLA POR ID*/
    public void Desactivar(Tipo_Telefono tipo) throws SNMPExceptions, SQLException {

        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Update Tipo_Telefono set estado=" + tipo.getEstado() + " where id= " + tipo.getId();

            accesoDatos.ejecutaSQL(select);

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
