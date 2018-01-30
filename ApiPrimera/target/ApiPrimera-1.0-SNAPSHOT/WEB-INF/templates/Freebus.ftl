<#ftl strip_whitespace = true>


<#assign charset="UTF-8">
<#assign title="Example">
<#assign content>
This is content
</#assign>
<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>
    <body>
        <table border="1">
            
            <#list llegadas as llegada  >
            <tr>
                <td>
                    info
                    </td>
                <td>
                    ${llegada.stopId}
                </td>
                 <td>
                     <script>
                         var mins=Math.floor(${llegada.busTimeLeft}/60);
                             var seg=  ${llegada.busTimeLeft}-mins*60;
                                 
                         if(${llegada.busTimeLeft}>900){
                             document.write("queda mucho tiempo: mas de 15 mins " );
                                 
                        }
                            else document.write("minutos:"+mins+" segundos:"+seg);
                            </script>
                </td>
                  <td>
                    ${llegada.busDistance}
                </td>
            </tr>
                    
        </#list>
            
            </table>


        </body>
    </html>
