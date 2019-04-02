/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.SNMPExceptions;
import Model.Persona;
import Model.PersonaDB;
import Model.Producto;
import Model.ProductoDB;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author Anyel
 */
@Named(value = "personaBean")
@SessionScoped
public class PersonaBean implements Serializable {

     SimpleDateFormat date = null;
    Date now = new Date(System.currentTimeMillis());
    LinkedList<Persona> listaTablaPersonas = new LinkedList<Persona>();
    LinkedList<Persona> listaTablaPersonasDesactivados = new LinkedList<Persona>();

    public void setListaTablaPersonas(LinkedList<Persona> listaTablaPersonas) {
        this.listaTablaPersonas = listaTablaPersonas;
    }
    Persona obj;
    
    
     int id;
    String nombre;
    String pApellido;
    String sApellido;
    String contrasenna;
    int idIdentificacion;
    int idUsuRegistra;
    Date feRegistra;
    int idUsuEdita;
    Date feEdita;

    public SimpleDateFormat getDate() {
        return date;
    }

    public void setDate(SimpleDateFormat date) {
        this.date = date;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    

    public Persona getObj() {
        return obj;
    }

    public void setObj(Persona obj) {
        this.obj = obj;
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
    
    public PersonaBean() {
      date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
     public LinkedList<Persona> getListaTablaPersonas() throws SNMPExceptions, SQLException {
        LinkedList<Persona> lista = new LinkedList<Persona>();
        PersonaDB sDB = new PersonaDB();

        lista = sDB.SeleccionaTodos();

        LinkedList resultlista = new LinkedList();
        resultlista = lista;
        return resultlista;
    }
    
     public LinkedList<Persona> getListaTablaPersonasDesactivados() throws SNMPExceptions, SQLException {
        LinkedList<Persona> lista = new LinkedList<Persona>();
        PersonaDB sDB = new PersonaDB();

        lista = sDB.SeleccionaTodosDesactivados();

        LinkedList resultlista = new LinkedList();
        resultlista = lista;
        return resultlista;
    }
     public void setListaTablaPersonasDesactivados(LinkedList<Persona> listaTablaPersonasDesactivados) {
        this.listaTablaPersonasDesactivados = listaTablaPersonasDesactivados;
    }
    public void limpia() {
        this.setId(0);
        this.setNombre("");
        this.setpApellido("");
        this.setsApellido("");
        this.setContrasenna("");
        this.setIdIdentificacion(0);
        this.setIdUsuRegistra(0);
        this.setIdUsuEdita(idUsuEdita);

    }
    
     public void eliminarPersona(Persona obj) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        PersonaDB pDB = new PersonaDB();
        
     

        this.getListaTablaPersonas();
        this.getListaTablaPersonasDesactivados();

    }
     public void activarPersona(int idp) throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
        PersonaDB pDB = new PersonaDB();

        obj = pDB.SeleccionarUno(idp);
        

        this.getListaTablaPersonas();
        this.getListaTablaPersonasDesactivados();

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
       
        Persona obj = new Persona(this.getId(),this.getNombre(),this.getpApellido(),this.getsApellido(),this.getContrasenna(),this.getIdIdentificacion(),
                this.getIdUsuRegistra(),this.getFeRegistra(),this.getIdUsuEdita(),this.getFeEdita());

        PersonaDB pDB = new PersonaDB();

        pDB.Actualizar(obj);

        this.limpia();
        this.getListaTablaPersonas();

    }
      public void agregarPersonas() throws SNMPExceptions, SQLException, NamingException, ClassNotFoundException {
       
        Persona obj = new Persona(this.getId(),this.getNombre(),this.getpApellido(),this.getsApellido(),this.getContrasenna(),this.getIdIdentificacion(),
                this.getIdUsuRegistra(),this.getFeRegistra(),this.getIdUsuEdita(),this.getFeEdita());

        PersonaDB pDB = new PersonaDB();

        pDB.Guardar(obj);

        this.limpia();
        this.getListaTablaPersonas();
    }
       public void cierraSesion() throws IOException {

        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
        public void asignaValores(Persona per) {
       this.setId(per.getId());
       this.setpApellido(per.getpApellido());
       this.setsApellido(per.getsApellido());
       this.setContrasenna(per.getContrasenna());
       this.setIdUsuEdita(per.getIdUsuEdita());
       //this.setFeEdita(this.getDate().format(now));
       
       
    }

}
