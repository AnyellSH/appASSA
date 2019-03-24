/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.SNMPExceptions;
import Model.ProductoDB;
import Model.Producto;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Part;
import javax.naming.NamingException;

/**
 *
 * @author Anyel
 */
@Named(value = "productosBean")
@SessionScoped
public class ProductosBean implements Serializable {

    LinkedList<Producto> listaTablaProductos = new LinkedList<Producto>();
    int idProducto;
    String nombreProducto;
    float precio;
    int cantidadMinima;
    String fechaRegistro;
    String fechaModificacion;
    int usuarioRegistro;
    int usuarioModifica;
    String imagen;
    String rutaRecibida = "";
    Part file = null;
    String usuarioIng;

    /**
     * Creates a new instance of ProductosBean
     */
    public ProductosBean() {

    }

    public LinkedList<Producto> getListaTablaProductos() throws SNMPExceptions, SQLException {
        LinkedList<Producto> lista = new LinkedList<Producto>();
        ProductoDB sDB = new ProductoDB();

        lista = sDB.consultarProductos();

        LinkedList resultlista = new LinkedList();
        resultlista = lista;
        return resultlista;
    }

    public void setListaTablaProductos(LinkedList<Producto> listaTablaProductos) {
        this.listaTablaProductos = listaTablaProductos;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(int usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRutaRecibida() {
        return rutaRecibida;
    }

    public void setRutaRecibida(String rutaRecibida) {
        this.rutaRecibida = rutaRecibida;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public int getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(int usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public void asignaValores(Producto pro) {
        this.setIdProducto(pro.getIdProducto());
        this.setNombreProducto(pro.getNombreProducto());
        this.setCantidadMinima(pro.getCantidadMinima());
        this.setFechaRegistro(pro.getFechaRegistro());
        this.setFechaModificacion(pro.getFechaModificacion());
        this.setPrecio(pro.getPrecio());
        this.setUsuarioRegistro(pro.getUsuarioRegistra());
        this.setUsuarioModifica(pro.getUsuarioModifica());
        this.setImagen(pro.getImagen());
    }

    public void limpia() {
        this.setIdProducto(0);
        this.setNombreProducto("");
        this.setCantidadMinima(0);
        this.setFechaRegistro("");
        this.setFechaModificacion("");
        this.setPrecio(0);
        this.setUsuarioRegistro(0);
        this.setUsuarioModifica(0);
        this.setImagen("");
        this.setRutaRecibida("");
    }

    /**
     * public void guardarImagenRutaEsp() throws IOException { File f = null;
     * String im = this.getFile().getName()+".jpg";
     *
     * try{ InputStream input = this.getFile().getInputStream(); f = new
     * File("C:/Users/Kim
     * Sánchez/Documents/NetBeansProjects/ProyectoPrograWeb/web/img/"+im);
     *
     * if(!f.exists()){ f.createNewFile(); } FileOutputStream output = new
     * FileOutputStream(f); byte[] buffer = new byte[1024]; int length;
     * while((length = input.read(buffer)) > 0){ output.write(buffer,0,length);
     * } input.close(); output.close(); } catch(Exception e){
     * e.printStackTrace(System.out); }
     *
     * this.setRutaRecibida("../img/"+im);
     *
     * }
     */
//   public void asignaURL() {
//
//        String i = this.getFile().getSubmittedFileName();
//        if (this.getImagen().equals("")) {
//            this.setImagen("../img/" + i);
//        }
//        this.setRutaRecibida("../img/" + i);
//    }

    public void eliminarProducto(Producto p) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        ProductoDB pDB = new ProductoDB();
        pDB.eliminarProducto(p.getIdProducto());

        this.getListaTablaProductos();
    }

    public void consultarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        final Map<String, Object> session = context.getSessionMap();
        final Object user = session.get("Usuario");

        if (user != null) {
            try {
                String userId = user.toString();
                this.setUsuarioRegistro(usuarioRegistro);

            } catch (ClassCastException e) {

            }
        } else {
            context.invalidateSession();
        }
    }

    public void actualizaDatos() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        String img = "";

        if (this.getRutaRecibida().equals("")) {
            img = this.getImagen();
        } else {
            img = this.getRutaRecibida();
        }
        Producto p = new Producto(this.getIdProducto(), this.getNombreProducto(), this.getPrecio(), this.getCantidadMinima(), this.getFechaRegistro(), this.getFechaModificacion(), this.getUsuarioRegistro(), this.getUsuarioModifica(), this.getImagen());

        ProductoDB pDB = new ProductoDB();

        pDB.ActualizarProducto(p);

        this.limpia();
        this.getListaTablaProductos();

    }

    public void agregarProducto() throws SNMPExceptions, SQLException {
        Producto p = new Producto(this.getIdProducto(), this.getNombreProducto(), this.getPrecio(), this.getCantidadMinima(), this.getFechaRegistro(), this.getFechaModificacion(), this.getUsuarioRegistro(), this.getUsuarioModifica(), this.getImagen());
        ProductoDB pDB = new ProductoDB();

        pDB.insertaProducto(p);

        this.limpia();
        this.getListaTablaProductos();
    }
     public void cierraSesion() throws IOException{
        
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
}
