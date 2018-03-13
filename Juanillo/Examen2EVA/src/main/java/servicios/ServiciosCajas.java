/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.CajaDAO;
import dao.PermisosDAO;
import dao.StaticDB;
import dao.UserDAO;
import java.util.Collection;
import modelo.Caja;
import modelo.Cosa;
import modelo.User;

/**
 *
 * @author Rafa
 */
public class ServiciosCajas
{
    public int checkUser(User temp) 
    {
        int resultado = 0;
        PermisosDAO a = new PermisosDAO();
        
        if(a.checkUser(temp) == true)
        {
            resultado = 1;
        }
        else
        {
            resultado = 0;
        }
        return resultado;
    }
    
    public Collection<Caja> getAllCajasUser(User usuario)
    {
        UserDAO a = new UserDAO();
        return a.getAllCajasUser(usuario);
        
    }
    
    public boolean addCajaAUser(User user, Caja caja ) {
       UserDAO a = new UserDAO();
       return a.addCajaAUser(user, caja);
    }
    
    public boolean addCaja (Caja caja)
    {
        CajaDAO a = new CajaDAO();
        return a.addCaja(caja);
    }
    public boolean getCaja2(String name){
        CajaDAO a = new CajaDAO();
       Caja caja = a.getCaja(name);
       if(caja!=null){
           return false;
       }
       else return true;
    }
    
    public String getCaja(String name)
    {
       CajaDAO a = new CajaDAO();
       Caja caja = a.getCaja(name);
       
       String resultado = "";
       
       if (caja.getCosas().size() != 0)
       {
           for (int i = 0; i<caja.getCosas().size(); i++)
            {
                resultado += caja.getCosas().get(i).getNombre()+". Cantidad: "+caja.getCosas().get(i).getCantidad()+"<br>";
            }
            return resultado;
       }
       else
       {
           return "No hay cosas en la caja";
       }
       
    }
    
    public int addCosaACaja (Cosa cosa, Caja caja)
    {
        
        CajaDAO a = new CajaDAO();
        boolean resultado = a.addCosaACaja(cosa, caja);
                
        if (resultado == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    
    public int addCantidadCosaACaja (Cosa cosa, Caja caja)
    {
        CajaDAO a = new CajaDAO();
        boolean resultado = a.addCantidadCosaACaja(cosa, caja);
                
        if (resultado == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
