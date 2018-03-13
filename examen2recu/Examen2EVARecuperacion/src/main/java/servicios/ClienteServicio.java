/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.ClienteDAO;
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author rafa
 */
public class ClienteServicio {

    public List<Cliente> getAllClientes() {
        ClienteDAO dao = new ClienteDAO();
        return dao.getAllUser();
    }

    public boolean updateCliente(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        return dao.updateCliente(cliente);
    }
     public boolean addCliente(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        return dao.addCliente(cliente);
    }
      public boolean delCliente(Cliente cliente) {
        ClienteDAO dao = new ClienteDAO();
        return dao.delCliente(cliente);
    }
    
}
