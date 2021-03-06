/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Asignatura;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Rafa
 */
public class AsignaturasDAO
{
//Select DBUtils

    public List<Asignatura> getAllAsignaturas()
    {
        List<Asignatura> lista = null;
        DBConnection db = new DBConnection();
        Connection con = null;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Asignatura>> h = new BeanListHandler<Asignatura>(Asignatura.class);
            lista = qr.query(con, "SELECT * FROM asignaturas", h);

        } catch (Exception ex)
        {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
        return lista;
    }
    //DEL SI

    public int delAsig(Asignatura u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas=0;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

             filas = qr.update(con,
                    "DELETE FROM asignaturas WHERE id=?",
                    u.getId());

        } catch (Exception ex)
        {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
            filas=-1;
        } finally
        {
            db.cerrarConexion(con);
        }
        return filas;
    }

    //UPDATE SI
    public int updateAsig(Asignatura u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas =0;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE ASIGNATURAS SET NOMBRE=?,CURSO=?"
                    + ", CICLO=? WHERE ID=?",
                    u.getNombre(), u.getCurso(), u.getCiclo(), u.getId());

        } catch (Exception ex)
        {
            filas=-1;
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
        return filas;
    }

    // insert DBUTILS SI
    public int addAsig(Asignatura u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        Asignatura id=null;
        try
        {
            con = db.getConnection();
            con.setAutoCommit(false);
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Asignatura> h = new BeanHandler<>(Asignatura.class);
          id = qr.insert(con,
                    "INSERT INTO ASIGNATURAS(ID,NOMBRE,CURSO,CICLO) VALUES(?,?,?,?)", h,
                    u.getId(), u.getNombre(), u.getCurso(), u.getCiclo());
            u.setId(id.getId());
            con.commit();

        } catch (Exception ex)
        {
            Logger.getLogger(AsignaturasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally
        {
            db.cerrarConexion(con);
        }
        if(id!=null)
        return 1;
        else return 0;

    }
    

}
