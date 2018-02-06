/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import dao.NotasDAO;
import java.sql.SQLException;
import java.util.List;
import model.Nota;

/**
 *
 * @author Rafa
 */
public class NotasServicios
{

    public Nota getAllNota(Nota n)
    {
        NotasDAO dao = new NotasDAO();

        return dao.getAllNota(n);
    }

    public Nota addNota(Nota notaNuevo)
    {
        NotasDAO dao = new NotasDAO();

        return dao.addNota(notaNuevo);
    }

    public int delNota(Nota notaNuevo)
    {
        NotasDAO dao = new NotasDAO();

        return dao.delNota(notaNuevo);
    }

    public int updateNota(Nota notaNuevo)
    {
        NotasDAO dao = new NotasDAO();

        return dao.updateNota(notaNuevo);
    }
}
