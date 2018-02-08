/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.AlumnosDAO;
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
import model.Alumno;
import model.ErrorHttp;
import servicios.AlumnosServicios;

/**
 *
 * @author oscar
 */
@WebServlet(name = "Alumnos", urlPatterns = {"/rest/alumnos"})
public class Alumnos extends HttpServlet {

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
        //Alumno a = (Alumno) request.getAttribute("alumno");
        AlumnosServicios as = new AlumnosServicios();
        List<Alumno> alumnos = new ArrayList<>();
        alumnos = as.getAllAlumnos();/*
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumnos.add(alumno);
        alumno = new Alumno();
        alumno.setNombre("KIKO");
        alumnos.add(alumno);*/
        request.setAttribute("json", alumnos);
            
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Alumno a = (Alumno) request.getAttribute("alumno");
        AlumnosServicios as = new AlumnosServicios();

        if (as.delAlumno(a) > 0) 
        {
            request.setAttribute("json", a);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Alumno a = (Alumno) request.getAttribute("alumno");
        AlumnosServicios as = new AlumnosServicios();
        if (as.updateAlumno(a) > 0) 
        //       ErrorHttp error = null;
        //        if(response.getStatus()==500){
        //        error = new ErrorHttp("se rompio");}
        {
            request.setAttribute("json", a);
        }
    }

    /*
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
        Alumno a = (Alumno) request.getAttribute("alumno");
        AlumnosServicios as = new AlumnosServicios();
        if (as.addAlumno(a) > 0) {
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
