/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AlumnosDAO;
import java.io.IOException;
import java.util.List;
import model.Alumno;

/**
 *
 * @author oscar
 */
public class AlumnosServicios {
    
    
    public List<Alumno> getAllAlumnos() throws IOException
    {
        AlumnosDAO dao = new AlumnosDAO();
        
        return dao.getAllAlumnosJDBC();
    }
    
    public Alumno addAlumno(Alumno alumnoNuevo) throws IOException
    {
        AlumnosDAO dao = new AlumnosDAO();
        
        return dao.insertAlumnoJDBC(alumnoNuevo);
    }
    public int delAlumno(Alumno alumnoNuevo) throws IOException
    {
        AlumnosDAO dao = new AlumnosDAO();
        
        return dao.delUser(alumnoNuevo);
    }
    public int updateAlumno (Alumno alumnoNuevo) throws IOException{
         AlumnosDAO dao = new AlumnosDAO();
        
        return dao.updateUser(alumnoNuevo);
    }
    
}
