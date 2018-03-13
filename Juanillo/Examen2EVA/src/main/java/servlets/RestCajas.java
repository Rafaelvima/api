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
 * @author Rafa
 */
@WebServlet(name = "RestCajas", urlPatterns
        = {
            "/rest/restcajas"
        })
public class RestCajas extends HttpServlet {

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
            throws ServletException, IOException {

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
            throws ServletException, IOException {
        String op2 = request.getParameter("op2");
        ServiciosCajas a = new ServiciosCajas();
        Caja caja = new Caja();
        caja = (Caja) request.getAttribute("caja");

        if (op2.equals("add_caja")) {
            User usuario = (User) request.getAttribute("usuario");
            Caja cajita = new Caja(caja.getNombre());
            boolean add = a.addCaja(cajita);
        int resultado;
            if (add == false) {
                //la caja ya existe
                if (a.addCajaAUser(usuario, cajita) == true) {
                    //caja existente añadida
                    resultado = 1;
                } else {
                    //caja existe y no se ha añadadido
                    resultado = 0;
                }
            }
            //caja creada
                else{
                if (a.addCajaAUser(usuario, cajita) == true){
                    //caja creada y añdida
                resultado = 2;
            }
                else{
                    //caja creada y no añadida a naide
                    resultado = -1;
                }
            }

                request.setAttribute("json", resultado);
            } else if (op2.equals("add_cosa")) {
                Cosa cosa = (Cosa) request.getAttribute("cosa");
                request.setAttribute("json", a.addCosaACaja(cosa, caja));
            } else if (op2.equals("add_cantidad")) {
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
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
