/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.NotasDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.Nota;

/**
 *
 * @author Rafa
 */
public class NotasServicios
{

    public List<Nota> getAllNotas() throws IOException
    {
        NotasDAO dao = new NotasDAO();

        return dao.getAllNotas();
    }

    public Nota addNota(Nota notaNuevo) throws SQLException, IOException
    {
        NotasDAO dao = new NotasDAO();

        return dao.addNota(notaNuevo);
    }

    public int delNota(Nota notaNuevo) throws IOException
    {
        NotasDAO dao = new NotasDAO();

        return dao.delNota(notaNuevo);
    }

    public int updateNota(Nota notaNuevo) throws IOException
    {
        NotasDAO dao = new NotasDAO();

        return dao.updateNota(notaNuevo);
    }
}
