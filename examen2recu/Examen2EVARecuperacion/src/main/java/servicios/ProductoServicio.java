/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ProductoDAO;
import java.util.List;
import modelo.Cliente;
import modelo.Producto;

/**
 *
 * @author rafa
 */
public class ProductoServicio {

    public List<Producto> getAllProductoCliente(Cliente cliente) {
        ProductoDAO dao = new ProductoDAO();
        return dao.getAllProductsCliente(cliente);
    }
    public boolean addProducto(Cliente temp, Producto p){
        ProductoDAO dao = new ProductoDAO();
        return dao.addProducto(temp,p);
    }
     public boolean updateProducto(Cliente temp, Producto p){
        ProductoDAO dao = new ProductoDAO();
        return dao.updateProducto(temp,p);
    }
      public boolean delProducto(Cliente temp, Producto p){
        ProductoDAO dao = new ProductoDAO();
        return dao.delProducto(temp,p);
    }
    
}
