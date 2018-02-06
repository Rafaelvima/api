/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Nota;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Rafa
 */
public class NotasDAO
{

    //Select DBUtils
   
    public Nota getAllNota(Nota n)
    {
        Nota lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
             ResultSetHandler<Nota> h = new BeanHandler<>(Nota.class);
            lista = qr.query(con, "select * FROM NOTAS WHERE ID_ASIGNATURA=? AND ID_ALUMNO=?",h,n.getId_asignatura(),n.getId_alumno());

        } catch (Exception ex)
        {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
        return lista;
    }

    //UPDATE SI
    public int updateNota(Nota u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas =0;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

             filas = qr.update(con,
                    "UPDATE NOTAS SET NOTA=?"
                    + " WHERE ID_ALUMNO=? AND ID_ASIGNATURA=?",
                    u.getNota(), u.getId_alumno(), u.getId_asignatura());
            if (filas == 0)
            {
                addNota(u);
            }

        } catch (Exception ex)
        {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
        return filas;
    }

    // insert DBUTILS SI
    public Nota addNota(Nota u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        Nota filas=null; 
        try
        {
            con = db.getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Nota> h = new BeanHandler<>(Nota.class);
            filas=qr.insert(con, "INSERT INTO NOTAS(ID_ALUMNO,ID_ASIGNATURA,NOTA) VALUES(?,?,?)", h,
                    u.getId_alumno(), u.getId_asignatura(), u.getNota());
            con.commit();

        } catch (Exception ex)
        {

            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
        return filas;

    }
    //DEL SI

    public int delNota(Nota u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas=0;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "DELETE FROM NOTAS WHERE ID_ALUMNO=? AND ID_ASIGNATURA=?",
                    u.getId_alumno(), u.getId_asignatura());

        } catch (SQLIntegrityConstraintViolationException ex)
        {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            Logger.getLogger(NotasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
        return filas;
    }

}
