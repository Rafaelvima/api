/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.GenericData;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.GenericData;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.lang.reflect.Type;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class AlumnosDAO {
//Select JDBC

    JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));

        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8083/ApiCutreJava/rest/alumnos");
    ObjectMapper objectMapper = new ObjectMapper();

    public List<Alumno> getAllAlumnosJDBC() throws IOException {

        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
 //HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        //    requestGoogle.getHeaders().set("X-Auth-Token", "2deee83e549c4a6e9709871d0fd58a0a");

        List<Alumno> json = (List) requestGoogle.execute().parseAs(Alumno.class);
//        List<Alumno> lista = objectMapper.readValue(requestGoogle.execute().parseAsString(),
//                objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));
        return json;
        //    List<GenericJson> json = (List) requestGoogle.execute().parseAs(type);
        // HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        //requestGoogle.getHeaders().set("X-Auth-Token", "2deee83e549c4a6e9709871d0fd58a0a");

    }

    public Alumno insertAlumnoJDBC(Alumno a) throws IOException {

        ObjectMapper m = new ObjectMapper();
        url.set("alumno", m.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");
        Alumno json = requestGoogle.execute().parseAs(Alumno.class);

        return json;
    }

    public int delUser(Alumno a) throws IOException {
         ObjectMapper m = new ObjectMapper();
        url.set("alumno", m.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
         requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");

        Alumno json = requestGoogle.execute().parseAs(Alumno.class);
        if(json!=null) return 1;
        else return 0;
        
    }

    public int updateUser(Alumno a) throws IOException {
         ObjectMapper m = new ObjectMapper();
        url.set("alumno", m.writeValueAsString(a));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), a));
requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");
        Alumno json = requestGoogle.execute().parseAs(Alumno.class);
        if(json!=null) return 1;
        else return 0;
        
    }

}
