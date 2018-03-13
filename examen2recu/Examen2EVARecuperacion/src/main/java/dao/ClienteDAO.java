/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author oscar
 */
public class ClienteDAO {
    
    
    public Cliente getUser(String name) {
        return StaticDB.getInstance().clientes.get(name);
    }

    public List<Cliente> getAllUser() {

        List<Cliente> clientes = new LinkedList<Cliente>();
        for (Cliente c : StaticDB.getInstance().clientes.values())
        {
            clientes.add(c);
        }
        return clientes;
    }

      public boolean addCliente(Cliente temp) {
        boolean clienteOK = false;
        Cliente u = StaticDB.getInstance().clientes.get(temp.getName());
        if (u == null) {
            StaticDB.getInstance().clientes.put(temp.getName(), temp);
            clienteOK = true;
        }
        return clienteOK;
    }

    public boolean updateCliente(Cliente temp) {
        boolean clienteOK = false;
        Cliente u = StaticDB.getInstance().clientes.get(temp.getName());
        if (u != null) {
            StaticDB.getInstance().clientes.put(temp.getName(), temp);
            clienteOK = true;
        }
        return clienteOK;
    }

    public boolean delCliente(Cliente temp) {
        boolean clienteOK = false;
        Cliente c = StaticDB.getInstance().clientes.remove(temp.getName());
        StaticDB.getInstance().clientes_productos.remove(temp.getName());
        if (c!=null)
            clienteOK=true;
        return clienteOK;
    }
    
}
