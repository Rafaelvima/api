<%-- 
    Document   : pintaremt
    Created on : Jan 16, 2018, 10:53:05 AM
    Author     : Rafa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table border="1">
             <tr> 
                    <td>
                        ID_parada
                    </td>
                    <td>
                        Tiempo restante
                    </td>
                        
                    <td>
                      Distancia
                    </td>

                    <td>
                        Tipo de posicion
                        </td>
                    </tr>
            <c:forEach items="${llegadas}" var="llegada">  
                <tr> 
                    <td>
                      ${llegada.stopId}
                    </td>
                    <td>
                        ${llegada.busTimeLeft}
                    </td>
                        
                    <td>
                      ${llegada.busDistance}
                    </td>

                    <td>
                        ${llegada.busPositionType}
                        </td>
                    </tr>


            </c:forEach> 

        </table>
    </body>
</html>
