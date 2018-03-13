<html>
    <head>
        <script>
            function consultar_caja(nombre)
            {
                document.getElementById("nombre_consulta").value = nombre;
            }
        </script>
    </head>
    
    
<body>
<?php
require 'vendor/autoload.php';
if (isset($_GET['name'])){$name = $_GET['name'];} else {$name=null;}
if (isset($_GET['password'])){$password = $_GET['password'];} else {$password=null;}

use GuzzleHttp\Client;
use GuzzleHttp\Exception\ClientException;

$client = new Client();

$uri = 'http://localhost:8083/Examen2EVA/rest/RestLogin';

$usuario = new \stdClass;


$usuario->name = $name;
$usuario->password = $password;

//$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
$request = $client->request('GET', $uri, ['query' => ['usuario' => json_encode($usuario)]]);
$alumnos = json_decode($request->getBody());
if ($alumnos == 1)
{ 
    $uri = 'http://localhost:8083/Examen2EVA/rest/RestCajas';
  //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
    $response = $client->request('GET', $uri, ['query' => ['usuario' => json_encode($usuario)]]);
    $cajas = json_decode($response->getBody());
    ?>
        <h2>Lista de cajas</h2>
        <table border="1">
        <tr><th>Caja</th><th>Consultar</th></tr>
    <?php
        foreach ($cajas as $caja)
        {
            echo "<tr><td>".$caja->nombre."</td><td><button onclick=\"consultar_caja('".$caja->nombre."')\">consultar_caja</button></td></tr>";
        }
    ?>
        </table><br<br>
        <form action="cajasDAO.php">
            Introduce nombre de la caja o cárgala:<br> 
            <input type="text" name="nombre" id="nombre_consulta" value="" /><br>
            <input type="hidden" name="op" value="POST" /><br>
            <input type="submit" value="enviar" />
        </form>
        
        <h2>Introducir nueva caja</h2>
        <form action="cajasDAO.php">
            Introduce nombre de la caja: <input type="text" name="nombre" value="" /><br>
            <input type="hidden" name="op" value="INSERT" /><br>
            <input type="hidden" name="funcionalidad" value="add_caja" /><br>
            <input type="hidden" name="name" value="<?php echo $name; ?>" />
            <input type="hidden" name="password" value="<?php echo $password; ?>" />
            <input type="submit" value="enviar" />
        </form>
        
        <h2>Añadir una cosa a una caja</h2>
        <form action="cajasDAO.php">
            Introduce nombre de la caja: <input type="text" name="nombre" value="" /><br>
            Introduce la cosa: <input type="text" name="cosa" value="" /><br>
            Introduce la cantidad: <input type="number" name="cantidad" value="" /><br>
            <input type="hidden" name="funcionalidad" value="add_cosa" /><br>
            <input type="hidden" name="op" value="INSERT" /><br>
            <input type="submit" value="enviar" />
        </form>
        
        <h2>Añadir cantidad a una cosa</h2>
        <form action="cajasDAO.php">
            Introduce nombre de la caja: <input type="text" name="nombre" value="" /><br>
            Introduce la cosa: <input type="text" name="cosa" value="" /><br>
            Introduce la cantidad: <input type="number" name="cantidad" value="" /><br>
            <input type="hidden" name="funcionalidad" value="add_cantidad" /><br>
            <input type="hidden" name="op" value="INSERT" /><br>
            <input type="submit" value="enviar" />
        </form>
        
    

<?php }
else
{
    echo "Login fallido";
}
?>

</body>
</html>
