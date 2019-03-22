/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Pablo Mora
 */
public class Persona {

    public int id;
    public String nombre;
    public String pApellido;
    public String sApellido;
    public String contrasenna;
    public int idIdentificacion;
    public int idUsuRegistra;
    public Date feRegistra;
    public int idUsuEdita;
    public Date feEdita;

    public Persona() {
    }

    public Persona(int id, String nombre, String pApellido, String sApellido, String contrasenna, int idIdentificacion, int idUsuRegistra, Date feRegistra, int idUsuEdita, Date feEdita) {
        this.id = id;
        this.nombre = nombre;
        this.pApellido = pApellido;
        this.sApellido = sApellido;
        this.contrasenna = contrasenna;
        this.idIdentificacion = idIdentificacion;
        this.idUsuRegistra = idUsuRegistra;
        this.feRegistra = feRegistra;
        this.idUsuEdita = idUsuEdita;
        this.feEdita = feEdita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getpApellido() {
        return pApellido;
    }

    public void setpApellido(String pApellido) {
        this.pApellido = pApellido;
    }

    public String getsApellido() {
        return sApellido;
    }

    public void setsApellido(String sApellido) {
        this.sApellido = sApellido;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public int getIdIdentificacion() {
        return idIdentificacion;
    }

    public void setIdIdentificacion(int idIdentificacion) {
        this.idIdentificacion = idIdentificacion;
    }

    public int getIdUsuRegistra() {
        return idUsuRegistra;
    }

    public void setIdUsuRegistra(int idUsuRegistra) {
        this.idUsuRegistra = idUsuRegistra;
    }

    public Date getFeRegistra() {
        return feRegistra;
    }

    public void setFeRegistra(Date feRegistra) {
        this.feRegistra = feRegistra;
    }

    public int getIdUsuEdita() {
        return idUsuEdita;
    }

    public void setIdUsuEdita(int idUsuEdita) {
        this.idUsuEdita = idUsuEdita;
    }

    public Date getFeEdita() {
        return feEdita;
    }

    public void setFeEdita(Date feEdita) {
        this.feEdita = feEdita;
    }
    
    

}
