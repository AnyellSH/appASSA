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
public class Identificacion {

    public int id;
    public String identificacion;
    public int estado;
    public int idTipIdentificacion;
    public int idUsuRegistra;
    public Date feRegistra;
    public int idUsuEdita;
    public Date feEdita;

    public Identificacion() {
    }

    public Identificacion(int id, String identificacion, int estado, int idTipIdentificacion, int idUsuRegistra, Date feRegistra, int idUsuEdita, Date feEdita) {
        this.id = id;
        this.identificacion = identificacion;
        this.estado = estado;
        this.idTipIdentificacion = idTipIdentificacion;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdTipIdentificacion() {
        return idTipIdentificacion;
    }

    public void setIdTipIdentificacion(int idTipIdentificacion) {
        this.idTipIdentificacion = idTipIdentificacion;
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
