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
import modelo.User;
import servicios.ServiciosUsuarios;

/**
 *
 * @author Rafa
 */
@WebServlet(name = "RestUsuarios", urlPatterns =
{
    "/rest/restusuarios"
})
public class RestUsuarios extends HttpServlet
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
    {    }

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
        ServiciosUsuarios a = new ServiciosUsuarios();
        Collection<User> usuarios = a.getAllUser();
        
        request.setAttribute("json", usuarios);
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
        ServiciosUsuarios a = new ServiciosUsuarios();
        User usuario = (User) request.getAttribute("usuario");
        request.setAttribute("json", a.updateUser(usuario));
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
        ServiciosUsuarios a = new ServiciosUsuarios();
        User usuario = (User) request.getAttribute("usuario");
        request.setAttribute("json", a.addUser(usuario));
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServiciosUsuarios a = new ServiciosUsuarios();
        User usuario = (User) request.getAttribute("usuario");
        String respuesta = request.getParameter("borrar");
        
        if(respuesta.equals("no"))
        {
             request.setAttribute("json", a.delUser(usuario));
        }
        else
        {
            request.setAttribute("json", a.delUserForce(usuario));
        }
    }
    
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
