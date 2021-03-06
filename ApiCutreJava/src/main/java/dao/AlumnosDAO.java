/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Alumno;
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
public class AlumnosDAO
{
//Select JDBC
    public List<Alumno> getAllAlumnos() {
        List<Alumno> lista = null;
       DBConnection db = new DBConnection();
        Connection con = null;
        try {
            con = db.getConnection();

            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Alumno>> handler
              = new BeanListHandler<Alumno>(Alumno.class);
            lista = qr.query(con, "select * FROM alumnos", handler);

        } catch (Exception ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
           db.cerrarConexion(con);
        }
        return lista;
    }

    public int insertAlumnoJDBC(Alumno a)
    {
        DBConnection db = new DBConnection();
        Connection con = null; int filas =0;
        try
        {
            con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("INSERT INTO alumnos (NOMBRE,FECHA_NACIMIENTO,"
                    + "MAYOR_EDAD) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, a.getNombre());
            stmt.setDate(2, new java.sql.Date(a.getFecha_nacimiento().getTime()));
            stmt.setBoolean(3, a.getMayor_edad());

             filas = stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
            {
                a.setId(rs.getInt(1));
            }

        } catch (Exception ex)
        {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            filas=-1;
        } finally
        {
            db.cerrarConexion(con);
        }

        return filas;
    }

    public int delUser(Alumno u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas=0;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "DELETE FROM alumnos WHERE ID=?",
                    u.getId());

        } catch (Exception ex)
        {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            filas=-1;
        } finally
        {
            db.cerrarConexion(con);
        }
        return filas;
    }

    public int updateUser(Alumno u)
    {
        DBConnection db = new DBConnection();
        Connection con = null;
        int filas=0;
        try
        {
            con = db.getConnection();
            QueryRunner qr = new QueryRunner();

            filas = qr.update(con,
                    "UPDATE alumnos SET NOMBRE=?,FECHA_NACIMIENTO=?"
                    + ", MAYOR_EDAD=? WHERE ID=?",
                    u.getNombre(),new java.sql.Date(u.getFecha_nacimiento().getTime()),u.getMayor_edad(),u.getId());
          
        } catch (Exception ex)
        {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
            filas=-1;
        } finally
        {
            db.cerrarConexion(con);
        }
        return filas;
    }

}
