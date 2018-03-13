/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.LinkedList;
import java.util.List;
import modelo.Cliente;
import modelo.Producto;

/**
 *
 * @author oscar
 */
public class ProductoDAO {

    public List<Producto> getAllProductsCliente(Cliente cliente) {

        List<Producto> clientes = new LinkedList<Producto>();
        for (Producto c : StaticDB.getInstance().clientes_productos.get(cliente.getName())) {
            clientes.add(c);
        }
        return clientes;
        
    }

    public boolean addProducto(Cliente temp, Producto p) {
        boolean clienteOK = false;
        List<Producto> productos = StaticDB.getInstance().clientes_productos.get(temp.getName());
        if (productos != null) {
            productos.add(p);
            clienteOK = true;
        }

        return clienteOK;
    }

    public boolean updateProducto(Cliente temp, Producto p) {
        boolean clienteOK = false;
        LinkedList<Producto> productos = (LinkedList<Producto>) StaticDB.getInstance().clientes_productos.get(temp.getName());
        if (productos != null) {
            int indice = productos.indexOf(p);
            if (indice > 0) {
                Producto p1 = productos.get(indice);
                p1.setPrecio(p.getPrecio());
                p1.setStock(p.getStock());
                clienteOK = true;
            }
        }

        return clienteOK;
    }

    public boolean delProducto(Cliente temp, Producto p) {
        boolean clienteOK = false;
        LinkedList<Producto> productos = (LinkedList<Producto>) StaticDB.getInstance().clientes_productos.get(temp.getName());
        if (productos != null) {
            clienteOK = productos.remove(p);
        }

        return clienteOK;
    }

}
