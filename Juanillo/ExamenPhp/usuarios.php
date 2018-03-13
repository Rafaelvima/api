<html>

<head>
    <script>
        function actualizar(usuario, password)
        {
            document.getElementById("actualizar_name").value = usuario;
            document.getElementById("actualizar_password").value = password;
        }
        function borrar(usuario)
        {
            document.getElementById("borrar_name").value = usuario;
        }
    </script>

</head>
<body>


<?php
 
 require 'vendor/autoload.php';

    use GuzzleHttp\Client;
    use GuzzleHttp\Exception\ClientException;
    $client = new Client();
    
    $uri = 'http://localhost:8083/Examen2EVA/rest/RestUsuaros';
    //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
    $response = $client->request('GET', $uri);
    $usuarios = json_decode($response->getBody());
?>
    <h2>Lista de usuarios</h2>
    <table border="1">
    <tr><th>Usuario</th><th>Password</th><th>Actualizar</th><th>Borrar</th></tr>
<?php
    foreach ($usuarios as $usuario)
    {
        echo "<tr><td>".$usuario->name."</td><td>".$usuario->password."</td><td><button onclick=\"actualizar('".$usuario->name."', '".$usuario->password."')\">actualizar</button></td><td><button onclick=\"borrar('".$usuario->name."')\">borrar</button></td></tr>";
    }
 ?>
    </table><br<br>
    
    <h2>Insertar usuarios</h2>
    <form action="usuariosDAO.php" method="get">
        Nombre: <input type="text" name="name" value="" /><br>
        Pass: <input type="password" name="password" value="" /><br>
        <input type="hidden" name="op" value="INSERT" /><br>
        <input type="submit" value="enviar" /><br><br>
    </form>
    
    <h2>Actualizar usuarios</h2>
    Cargar un usuario y poner una nueva password:<br>
    <form action="usuariosDAO.php" method="get">
        Nombre: <input type="text" name="name" id="actualizar_name" value="" /><br>
        Pass: <input type="password" name="password" id="actualizar_password" value="" /><br>
        <input type="hidden" name="op" value="UPDATE" /><br>
        <input type="submit" value="actualizar" /><br><br>
    </form>
    
    <h2>Borrar usuarios</h2>
    Cargar un usuario y borrar: <br>
    <form action="usuariosDAO.php" method="get">
        Nombre: <input type="text" name="name" id="borrar_name" value="" /><br>
        <input type="hidden" name="op" value="DELETE" /><br>
        <input type="submit" value="borrar" /><br><br>
    </form>
    
</body> 
</html> 