/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AsignaturasDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import model.Asignatura;
import model.Asignatura;
import model.ErrorHttp;
import servicios.AsignaturasServicios;
import servicios.AsignaturasServicios;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Asignaturas", urlPatterns =
{
    "/asignaturas"
})
public class Asignaturas extends HttpServlet
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
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Asignatura a = (Asignatura) request.getAttribute("asignatura");
        AsignaturasServicios as = new AsignaturasServicios();
        List<Asignatura> asignaturas = new ArrayList<>();
        asignaturas = as.getAllAsignaturas();
        ErrorHttp error = null;
        if(response.getStatus()==500){
        error = new ErrorHttp("se rompio");}

        request.setAttribute("json", error);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        AsignaturasServicios as = new AsignaturasServicios();
        String error=null;
        int result = as.delAsig(a);
        if(result == 0){
            //no se ha añadido nunguna persona
        }
        else if(result ==-1){
            
        }
        // if (asignatura no se puede borrar)
        
        if(request.getAttribute("mensajeError")!=null){
           // error=request.getAttribute("mensajeError");
        }

        request.setAttribute("json", error);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        AsignaturasServicios as = new AsignaturasServicios();
        if(as.updateAsig(a)<1){
            //error de algo o no se puede añadir algo
        }
        Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
        String body = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
       ErrorHttp error = null;
        if(response.getStatus()==500){
        error = new ErrorHttp("se rompio");}

        request.setAttribute("json", error);
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
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        AsignaturasServicios as = new AsignaturasServicios();
        as.addAsig(a);
        request.setAttribute("json", a);
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
