/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.AsignaturasDAO;
import java.io.IOException;
import java.util.List;
import model.Asignatura;

/**
 *
 * @author Rafa
 */
public class AsignaturasServicios
{

    public List<Asignatura> getAllAsignaturas() throws IOException
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.getAllAsignaturas();
    }

    public Asignatura addAsig(Asignatura asignaturaNuevo) throws IOException
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.addAsig(asignaturaNuevo);
    }

    public int delAsig(Asignatura asignaturaNuevo) throws IOException
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.delAsig(asignaturaNuevo);
    }

    public int updateAsig(Asignatura asignaturaNuevo) throws IOException
    {
        AsignaturasDAO dao = new AsignaturasDAO();

        return dao.updateAsig(asignaturaNuevo);
    }

}
