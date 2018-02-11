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
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alumno;
import model.Asignatura;
import model.Nota;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Rafa
 */
public class NotasDAO {

    JsonFactory JSON_FACTORY = new JacksonFactory();
    HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
        @Override
        public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));

        }
    });
    GenericUrl url = new GenericUrl("http://localhost:8083/ApiCutreJava/rest/notas");
    ObjectMapper objectMapper = new ObjectMapper();

    //Select DBUtils
    public List<Nota> getAllNotas() throws IOException {
 HttpRequest requestGoogle = requestFactory.buildGetRequest(url);
 //HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), a));
        //    requestGoogle.getHeaders().set("X-Auth-Token", "2deee83e549c4a6e9709871d0fd58a0a");

        List<Nota> json = (List) requestGoogle.execute().parseAs(Nota.class);
//        List<Alumno> lista = objectMapper.readValue(requestGoogle.execute().parseAsString(),
//                objectMapper.getTypeFactory().constructCollectionType(List.class, Alumno.class));
        return json;
    }

    //UPDATE SI
    public int updateNota(Nota u) throws IOException {
 ObjectMapper m = new ObjectMapper();
        url.set("alumno", m.writeValueAsString(u));
        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new JsonHttpContent(new JacksonFactory(), u));
            requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");

        Nota json = requestGoogle.execute().parseAs(Nota.class);
        if(json!=null) return 1;
        else return 0;
    }

    // insert DBUTILS SI
    public Nota addNota(Nota u) throws SQLException, IOException {
 ObjectMapper m = new ObjectMapper();
        url.set("alumno", m.writeValueAsString(u));
        HttpRequest requestGoogle = requestFactory.buildPutRequest(url, new JsonHttpContent(new JacksonFactory(), u));

        Nota json = requestGoogle.execute().parseAs(Nota.class);

        return json;
    }
    //DEL SI

    public int delNota(Nota u) throws IOException {
 ObjectMapper m = new ObjectMapper();
        url.set("alumno", m.writeValueAsString(u));
        HttpRequest requestGoogle = requestFactory.buildDeleteRequest(url);
            requestGoogle.getHeaders().set("Apikey", "2deee83e549c4a6e9709871d0fd58a0a");

        Asignatura json = requestGoogle.execute().parseAs(Asignatura.class);
        if(json!=null) return 1;
        else return 0;
    }

}
