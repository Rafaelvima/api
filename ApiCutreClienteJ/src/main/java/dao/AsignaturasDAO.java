/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
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
import model.Alumno;

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
public class AsignaturasDAO {

    JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));

        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8083/ApiCutreJava/rest/asignaturas");
    ObjectMapper objectMapper = new ObjectMapper();
//Select DBUtils

    public List<Asignatura> getAllAsignaturas() throws IOException {
        HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
        //HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        //    requestGoogle.getHeaders().set("X-Auth-Token", "2deee83e549c4a6e9709871d0fd58a0a");
        requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");
        List<Asignatura> json = (List) requestGoogle.execute().parseAs(Asignatura.class);
//        List<Alumno> lista = objectMapper.readValue(requestGoogle.execute().parseAsString(),
//                objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));
        return json;
    }
    //DEL SI

    public int delAsig(Asignatura u) throws IOException {
        ObjectMapper m = new ObjectMapper();
        url.set("asignatura", m.writeValueAsString(u));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
        requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");

        Asignatura json = requestGoogle.execute().parseAs(Asignatura.class);
        if (json != null) {
            return 1;
        } else {
            return 0;
        }
    }

    //UPDATE SI
    public int updateAsig(Asignatura u) throws IOException {
        ObjectMapper m = new ObjectMapper();
        url.set("asignatura", m.writeValueAsString(u));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), u));
        requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");

        Asignatura json = requestGoogle.execute().parseAs(Asignatura.class);
        if (json != null) {
            return 1;
        } else {
            return 0;
        }
    }

    // insert DBUTILS SI
    public Asignatura addAsig(Asignatura u) throws IOException {
        ObjectMapper m = new ObjectMapper();
        url.set("asignatura", m.writeValueAsString(u));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), u));
        requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");
        Asignatura json = requestGoogle.execute().parseAs(Asignatura.class);

        return json;

    }

}
