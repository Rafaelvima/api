/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import servicios.ClienteServicio;

/**
 *
 * @author rafa
 */
@WebServlet(name = "Clientes", urlPatterns = {"/rest/clientes"})
public class Clientes extends HttpServlet {

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
            throws ServletException, IOException {
        
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
            throws ServletException, IOException {
        ClienteServicio cs = new ClienteServicio();
        List<Cliente> clientes = cs.getAllClientes();
        request.setAttribute("json", clientes);
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
            throws ServletException, IOException {
    ClienteServicio cs = new ClienteServicio();
    Cliente cliente = (Cliente) request.getAttribute("cliente");
                boolean resul=cs.updateCliente(cliente);
                if(resul){
                    request.setAttribute("json",1);
                }
                else
                request.setAttribute("json", 0);
                
            }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    ClienteServicio cs = new ClienteServicio();
    Cliente cliente = (Cliente) request.getAttribute("cliente");
                boolean resul=cs.addCliente(cliente);
                if(resul){
                    request.setAttribute("json",1);
                }
                else
                request.setAttribute("json", 0);
                
            }
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    ClienteServicio cs = new ClienteServicio();
    Cliente cliente = (Cliente) request.getAttribute("cliente");
                boolean resul=cs.delCliente(cliente);
                if(resul){
                    request.setAttribute("json",1);
                }
                else
                request.setAttribute("json", 0);
                
            }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
