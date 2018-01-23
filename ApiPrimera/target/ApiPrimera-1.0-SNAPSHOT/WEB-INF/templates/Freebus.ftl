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
                    ${llegada.busTimeLeft}
                     <script>
                         if(${llegada.busTimeLeft}>200){
                             document.write("tiempo muy grande "+ ${llegada.busTimeLeft} );
                                 
                        }
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
