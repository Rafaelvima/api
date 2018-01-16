/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prueba;

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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oscar
 */
@WebServlet(name = "GoogleHttpConsumingApi", urlPatterns = {"/api"})
public class GoogleHttpConsumingApi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

        HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        JsonFactory JSON_FACTORY = new JacksonFactory();
        HttpRequestFactory requestFactory
          = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
              @Override
              public void initialize(HttpRequest request) {
                  request.setParser(new JsonObjectParser(JSON_FACTORY));
              }
          });

        GenericUrl url = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetArriveStop.php");

        GenericData data = new GenericData();
        data.put("idClient",config.Configuration.getInstance().getIdemt());
        data.put("passKey", config.Configuration.getInstance().getKeyemt());
        data.put("idStop", "3727");

        HttpRequest requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));


         GenericJson json = requestGoogle.execute().parseAs(GenericJson.class);

         request.setAttribute("json", json);
        ArrayList arrives = (ArrayList) json.get("arrives");
        response.getWriter().print("<html><body>");
        for (int i = 0; i < arrives.size(); i++) {
            ArrayMap arrive = (ArrayMap)arrives.get(i);
            response.getWriter().print(arrive.get("busTimeLeft")+" ");
            response.getWriter().print(arrive.get("lineId")+" ");
            response.getWriter().print(arrive.get("busDistance")+" ");
            response.getWriter().print("<br>");
        }
        
        data = new GenericData();
        data.put("idClient",config.Configuration.getInstance().getIdemt());
        data.put("passKey", config.Configuration.getInstance().getKeyemt());
        data.put("line", "76");
        data.put("direction", "PLAZA BEATA");
        url = new GenericUrl("https://openbus.emtmadrid.es:9443/emt-proxy-server/last/geo/GetStopsLine.php");
        requestGoogle = requestFactory.buildPostRequest(url, new UrlEncodedContent(data));
        json = requestGoogle.execute().parseAs(GenericJson.class);
        
      ArrayList stops = (ArrayList) json.get("stop");
      response.getWriter().print(json.get("destination"));
      for (int i = 0; i < stops.size(); i++) {
            ArrayMap stop = (ArrayMap)stops.get(i);
            response.getWriter().print(stop.get("stopId")+" ");
            response.getWriter().print(stop.get("name")+" ");
           
            response.getWriter().print("<br>");
        }
        
        
        
        response.getWriter().print("</body></html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
