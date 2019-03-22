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
public class TelefonoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();

    public TelefonoDB(Connection conn) {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);

    }

    public TelefonoDB() {
    }

    /*SELECCIONAR TODOS*/
    public LinkedList<Telefono> SeleccionarTodos() throws SNMPExceptions, SQLException {

        LinkedList<Telefono> otraLista = new LinkedList<Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select Id, Estado, Numero, ID_TIPO_TELEFONO"
                    + "Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita"
                    + "from Telefono";

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String num = rsPA.getString("Numero");
                int est = rsPA.getInt("Estado");
                int idtpTel = rsPA.getInt("ID_TIPO_TELEFONO");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                Date feRegistra = rsPA.getDate("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                Date feEdita = rsPA.getDate("Fecha_Edita");

                Telefono Obj = new Telefono(id, est, num, idtpTel, idUsuRegistra, feRegistra, idUsuEdita, feEdita);

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
    public LinkedList<Telefono> SeleccionarUno(int idp) throws SNMPExceptions, SQLException {
        LinkedList<Telefono> otraLista = new LinkedList<Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Select Id, Estado, Numero, ID_TIPO_TELEFONO"
                    + "Id_Usu_Registra, Fecha_Registra, Id_Usu_Edita,Fecha_Edita"
                    + "from Telefono where id=" + idp;

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String num = rsPA.getString("Numero");
                int est = rsPA.getInt("Estado");
                int idtpTel = rsPA.getInt("ID_TIPO_TELEFONO");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                Date feRegistra = rsPA.getDate("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                Date feEdita = rsPA.getDate("Fecha_Edita");

                Telefono Obj = new Telefono(id, est, num, idtpTel, idUsuRegistra, feRegistra, idUsuEdita, feEdita);

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
    public LinkedList<Telefono> Actualizar(int idp, String nump, int tipp, int usup, Date fecp) throws SNMPExceptions, SQLException {

        LinkedList<Telefono> otraLista = new LinkedList<Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Update Telefono set Numero = " + nump + ",set ID_TIPO_TELEFONO =" + tipp
                    + ", set Id_Usu_Edit =" + usup + ", set Fecha_Edita = " + fecp
                    + "from Telefono where id=" + idp;

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String num = rsPA.getString("Numero");
                int est = rsPA.getInt("Estado");
                int idtpTel = rsPA.getInt("ID_TIPO_TELEFONO");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                Date feRegistra = rsPA.getDate("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                Date feEdita = rsPA.getDate("Fecha_Edita");

                Telefono Obj = new Telefono(id, est, num, idtpTel, idUsuRegistra, feRegistra, idUsuEdita, feEdita);

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

    /*DESACTIVAR UNO DE LA TABLA POR ID*/
    public LinkedList<Telefono> Desactivar(int idp, int estp) throws SNMPExceptions, SQLException {

        LinkedList<Telefono> otraLista = new LinkedList<Telefono>();
        String select = "";
        try {

            AccesoDatos accesoDatos = new AccesoDatos();

            select = "Update Telefono set estado=" + estp + " where id= " + idp;

            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);

            while (rsPA.next()) {

                int id = rsPA.getInt("Id");
                String num = rsPA.getString("Numero");
                int est = rsPA.getInt("Estado");
                int idtpTel = rsPA.getInt("ID_TIPO_TELEFONO");

                int idUsuRegistra = rsPA.getInt("Id_Usu_Registra");
                Date feRegistra = rsPA.getDate("Fecha_Registra");
                int idUsuEdita = rsPA.getInt("Id_Usu_Edita");
                Date feEdita = rsPA.getDate("Fecha_Edita");

                Telefono Obj = new Telefono(id, est, num, idtpTel, idUsuRegistra, feRegistra, idUsuEdita, feEdita);
                
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

}
