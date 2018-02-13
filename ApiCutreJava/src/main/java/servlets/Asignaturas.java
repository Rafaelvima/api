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
@WebServlet(name = "Asignaturas", urlPatterns
        = {
            "/rest/asignaturas"
        })
public class Asignaturas extends HttpServlet {

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
        request.setAttribute("json", asignaturas);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        AsignaturasServicios as = new AsignaturasServicios();
        if (as.delAsig(a) > 0) {
            request.setAttribute("json", a);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Asignatura a = (Asignatura) request.getAttribute("asignatura");
        AsignaturasServicios as = new AsignaturasServicios();
        if (as.updateAsig(a) > 0) //coorecto
        {
            request.setAttribute("json", a);
        }
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
        if (as.addAsig(a) > 0) {
            request.setAttribute("json", a);
        }
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
