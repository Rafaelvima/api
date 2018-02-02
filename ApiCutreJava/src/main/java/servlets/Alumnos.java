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
@WebServlet(name = "Alumnos", urlPatterns = {"/alumnos"})
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
        Alumno a = (Alumno) request.getAttribute("alumno");
        LocalDate local = LocalDate.of(1910, Month.MARCH, 12);
        AlumnosServicios as = new AlumnosServicios();
        String op = request.getParameter("op");
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String mayor = request.getParameter("mayor");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        List<Alumno> alumnos = new ArrayList<>();
        Alumno alumno = new Alumno();
        alumno.setNombre("Juan");
        alumnos.add(alumno);
        alumno = new Alumno();
        alumno.setNombre("KIKO");
        alumnos.add(alumno);
        request.setAttribute("json", alumnos);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Alumno a = (Alumno) request.getAttribute("alumno");
        LocalDate local = LocalDate.of(1910, Month.MARCH, 12);
        AlumnosServicios as = new AlumnosServicios();
        String op = request.getParameter("op");
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String mayor = request.getParameter("mayor");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        a.setId(Long.parseLong(id));
        as.delAlumno(a);
        a.setNombre("DELETE");
        // if (alumno no se puede borrar)
        resp.setStatus(500);
        ErrorHttp error = new ErrorHttp("se rompio");

        request.setAttribute("json", error);

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Alumno a = (Alumno) request.getAttribute("alumno");
        LocalDate local = LocalDate.of(1910, Month.MARCH, 12);
        AlumnosServicios as = new AlumnosServicios();
        String op = request.getParameter("op");
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String mayor = request.getParameter("mayor");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        //
        Date fechaDate= null;
        try {
            fechaDate = format.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setId(Long.parseLong(id));
        a.setNombre(nombre);
        a.setFecha_nacimiento(fechaDate);
        if ("on".equals(mayor)) {
            a.setMayor_edad(Boolean.TRUE);
        } else {
            a.setMayor_edad(Boolean.FALSE);
        }
        as.updateAlumno(a);
        //
        a.setNombre("PUT");
        Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
        String body = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
        request.setAttribute("json", a);
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
        Alumno a = (Alumno) request.getAttribute("alumno");

        LocalDate local = LocalDate.of(1910, Month.MARCH, 12);
        AlumnosServicios as = new AlumnosServicios();
        String op = request.getParameter("op");
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String mayor = request.getParameter("mayor");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDate=null;
        try {
            fechaDate = format.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Alumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
        a.setNombre(nombre);
        if (fecha != null) {
            a.setFecha_nacimiento(fechaDate);
        } else {
            a.setFecha_nacimiento(Date.from(local.atStartOfDay().toInstant(ZoneOffset.UTC)));
        }

        if ("on".equals(mayor)) {
            a.setMayor_edad(Boolean.TRUE);
        } else {
            a.setMayor_edad(Boolean.FALSE);
        }
        as.addAlumno(a);
        a.setNombre("conseguido");
        request.setAttribute("json", a);
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
