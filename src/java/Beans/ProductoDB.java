/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import DAO.AccesoDatos;
import Model.Producto;
import java.util.LinkedList;

/**
 *
 * @author Anyel
 */
class ProductoDB {
     private AccesoDatos accesoDatos = new AccesoDatos();
    LinkedList<Producto> listaProductos = new LinkedList<Producto>();
}
