/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import modelo.Producto;
import modelo.Cliente;

/**
 *
 * @author oscar
 */
public class StaticDB {
    
    public Map<String,Cliente> clientes;
    public Map<String,Producto> productos;

    public Map<String,List<Producto>> clientes_productos;

    
    private static StaticDB instance;
    
    private StaticDB(){
        clientes = new LinkedHashMap<>();
        productos = new LinkedHashMap<>();
        clientes_productos = new LinkedHashMap<>();
      
        clientes.put("juan", new Cliente("juan","juan","100"));
        clientes.put("eduardo", new Cliente("eduardo","eduardo","100"));
        clientes.put("guti", new Cliente("guti","guti","100"));

        clientes_productos.put("juan", new LinkedList<Producto>());
        clientes_productos.put("eduardo", new LinkedList<Producto>());

        clientes_productos.get("juan").add(new Producto("kk",10,1.56f));

        

    }
    
    public static StaticDB getInstance()
    {
        if (instance==null)
        {
            instance = new StaticDB();
        }
        
        return instance;
    }
    

    
}
