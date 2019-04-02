/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.SNMPExceptions;
import Model.Roll;
import Model.RollDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.naming.NamingException;

/**
 *
 * @author Pablo Mora
 */
@Named(value = "rollBean")
@SessionScoped
public class RollBean implements Serializable {

    SimpleDateFormat date = null;
    Date now = new Date(System.currentTimeMillis());
    LinkedList<Roll> listaActivos = new LinkedList<Roll>();
    LinkedList<Roll> listaInactivos = new LinkedList<Roll>();

    int id;
    String descripcion;
    int estado;
    int idUsuReg;
    String fechaR;
    int idUsuEdi;
    String fechaE;
    Boolean estadoV;
    Boolean estadoF;

    RollDB RDB;
    /*MENSAJES DE ERROR*/
    String errorid;

    public RollBean() {
        date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RDB = new RollDB();
    }

    public LinkedList<Roll> getListaActivos() throws SNMPExceptions, SQLException {
        LinkedList<Roll> lista = new LinkedList<Roll>();
        RollDB sDB = new RollDB();

        lista = sDB.SeleccionarTodos();

        LinkedList resultlista = new LinkedList();
        resultlista = lista;
        return resultlista;
    }

    public void setListaActivos(LinkedList<Roll> listaActivos) {
        this.listaActivos = listaActivos;
    }

    public LinkedList<Roll> getListaInactivos() throws SNMPExceptions, SQLException {
        LinkedList<Roll> lista = new LinkedList<Roll>();
        RollDB sDB = new RollDB();

        lista = sDB.SeleccionaTodosDesactivados();

        LinkedList resultlista = new LinkedList();
        resultlista = lista;
        return resultlista;
    }

    public void setListaInactivos(LinkedList<Roll> listaInactivos) {
        this.listaInactivos = listaInactivos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdUsuReg() {
        return idUsuReg;
    }

    public void setIdUsuReg(int idUsuReg) {
        this.idUsuReg = idUsuReg;
    }

    public String getFechaR() {
        return fechaR;
    }

    public void setFechaR(String fechaR) {
        this.fechaR = fechaR;
    }

    public int getIdUsuEdi() {
        return idUsuEdi;
    }

    public void setIdUsuEdi(int idUsuEdi) {
        this.idUsuEdi = idUsuEdi;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }

    public Boolean getEstadoV() {
        return estadoV;
    }

    public void setEstadoV(Boolean estadoV) {
        this.estadoV = estadoV;
    }

    public Boolean getEstadoF() {
        return estadoF;
    }

    public void setEstadoF(Boolean estadoF) {
        this.estadoF = estadoF;
    }

    public String getErrorid() {
        return errorid;
    }

    public void setErrorid(String errorid) {
        this.errorid = errorid;
    }

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public void Limpiar() {
        this.setId(0);
        this.setDescripcion("");
        this.setEstado(0);
        this.setIdUsuReg(0);
        this.setFechaR("");
        this.setIdUsuEdi(0);
        this.setFechaE("");
    }

    /*GUARDAR EN LA BD*/
    public void Guardar() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        if (id <= -1) {
            this.setErrorid("Codigo incorrecto");
        } else {
            this.setErrorid("");
        }

        try {
            if (estadoV) {
                estado = 1;
            } else {
                if (estadoF) {
                    estado = 0;
                }
            }

            Roll obj = new Roll(this.getId(), this.getDescripcion(), this.getEstado(),
                    this.getIdUsuReg(), this.getDate().format(now), this.getIdUsuEdi(), this.getDate().format(now));
            if (RDB.SeleccionarUno(obj.id) == null) {
                RDB.Guardar(obj);
            } else {
                RDB.Actualizar(obj);
            }

            this.Limpiar();
            this.getListaActivos();
        } catch (Exception e) {
            e.toString();
        }
    }

    /*ACTUALIZAR EN LA BD*/
    public void Actualizar() {
        try {
            if (estadoV) {
                estado = 1;
            } else {
                if (estadoF) {
                    estado = 0;
                }
            }

            Roll obj = new Roll(id, descripcion, estado, idUsuReg, fechaR, idUsuEdi, this.getDate().format(now));
            RDB.Actualizar(obj);

            this.Limpiar();
            this.getListaActivos();
        } catch (Exception e) {
            e.toString();
        }
    }

    /*TABLA DE PERFILES ACTIVOS*/
    public void Activo(Roll roll) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        RDB.Desactivar(roll);
        this.getListaActivos();
        this.getListaInactivos();

    }

    /*TABLA DE PERFILES INACTIVOS*/
    public void Inactivo(Roll roll) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {

        RDB.Desactivar(roll);
        this.getListaActivos();
        this.getListaInactivos();

    }

    /*ASIGNAR VALORES*/
    public void asignaValores(Roll obj) {
        this.setId(obj.getId());
        this.setDescripcion(obj.getDescripcion());
    }
}
