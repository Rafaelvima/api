/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Caja;
import modelo.Cosa;
import modelo.User;
import servicios.ServiciosCajas;

/**
 *
 * @author DAW
 */
@WebServlet(name = "RestCajas", urlPatterns =
{
    "/rest/RestCajas"
})
public class RestCajas extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RestCajas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RestCajas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServiciosCajas a = new ServiciosCajas();
        User usuario = (User) request.getAttribute("usuario");
        Collection<Caja> cajas = a.getAllCajasUser(usuario);
        
        request.setAttribute("json", cajas);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        
        Caja caja = (Caja) request.getAttribute("caja");
        ServiciosCajas a = new ServiciosCajas();
        String resultado = a.getCaja(caja.getNombre());
        caja = new Caja(resultado);
        request.setAttribute("json", caja);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @param request
     * @param response
     * @return a String containing servlet description
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String funcionalidad = request.getParameter("funcionalidad");
        ServiciosCajas a = new ServiciosCajas();
        Caja caja = new Caja();
        caja = (Caja) request.getAttribute("caja");
        
        if (funcionalidad.equals("add_caja"))
        {
            User usuario = (User) request.getAttribute("usuario");
            
            //Primero creamos la caja
            Caja caja1 = new Caja(caja.getNombre());
            boolean add = a.addCaja(caja1);

            //Ahora la asignamos a un usuario y comprobamos si se ha hecho correctamente
            int resultado;
            if (a.addCajaAUser(usuario, caja1) == true && add == true)
            {
                resultado = 1;
            }
            else
            {
                resultado = 0;
            }
            request.setAttribute("json", resultado);
        }
        else if(funcionalidad.equals("add_cosa"))
        {
            Cosa cosa = (Cosa) request.getAttribute("cosa");
            request.setAttribute("json", a.addCosaACaja(cosa, caja));
        }
        else if(funcionalidad.equals("add_cantidad"))
        {
            Cosa cosa = (Cosa) request.getAttribute("cosa");
            request.setAttribute("json", a.addCantidadCosaACaja(cosa, caja));
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
