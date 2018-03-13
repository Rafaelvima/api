/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.DeleteForceException;
import dao.UserDAO;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.User;

/**
 *
 * @author Rafa
 */
public class ServiciosUsuarios
{
    public Collection<User> getAllUser()
    {
        UserDAO a = new UserDAO();
        return a.getAllUser();
    }
    
    public int addUser(User usuario)
    {
        UserDAO a = new UserDAO();
        
        if(a.addUser(usuario) == true)
        {
            return 1;
        }
        else
        {
            return 0 ;
        }
    }
    
    public int updateUser(User usuario)
    {
        UserDAO a = new UserDAO();
        
        if(a.updateUser(usuario) == true)
        {
            return 1;
        }
        else
        {
            return 0 ;
        }
    }
    
    public int delUser(User usuario)
    {
        UserDAO a = new UserDAO();
        try
        {
            if (a.delUser(usuario) == true)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (DeleteForceException ex)
        {
            Logger.getLogger(ServiciosUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 2;
        }
    }
    
    public int delUserForce(User usuario)
    {
        UserDAO a = new UserDAO();
        
        if (a.delUserForce(usuario) == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
