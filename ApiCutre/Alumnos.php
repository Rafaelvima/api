<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<?php
require 'vendor/autoload.php';

use GuzzleHttp\Client;

$client = new Client();

$uri = 'http://localhost:8080/Apicutre/rest/cutre';

// Create connection
// Check connection
$alumno = new \stdClass;
if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (isset($_REQUEST['id'])) {
    $idalu = $_REQUEST['id'];
} else {
    $idalu = null;
}
if (isset($_REQUEST['nombre'])) {
    $nombrealu = $_REQUEST['nombre'];
} else {
    $nombrealu = null;
}
if (isset($_REQUEST['fecha'])) {
    $format = new DateTime($_REQUEST['fecha']);
    $fechaalu = $format->format('Y-m-d');
} else {
    $fechaalu = null;
}
if (isset($_REQUEST['mayor'])) {
    if (empty($_REQUEST['mayor'])) {
        $mayoralu = 0;
    } elseif ($_REQUEST['mayor'] == "on") {
        $mayoralu = 1;
    } else {
        $mayoralu = 0;
    }
} else {
    $mayoralu = 0;
}
switch ($op) {
    case "insert":
        $alumno->id = $idalu;
        $alumno->nombre = $nombrealu;
        $alumno->fecha = $fechaalu;
        $alumno->mayor = $mayoralu;
        try {
            $response = $client->put($uri, [
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
            ]);
            //$alumno = json_decode($response->getBody());  
            echo $alumno->id . " " . $alumno->nombre;
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    case "delete":
        $alumno->id = $idalu;
        try {
            $response = $client->delete($uri, [
                'query' => [
                    'alumno' => json_encode($alumno)
                ]
            ]);
            //$alumno = json_decode($response->getBody());  
//echo $alumno->id . " " . $alumno->nombre;
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    case "update":
        $alumno->id = $idalu;
        $alumno->nombre = $nombrealu;
        $alumno->fecha = $fechaalu;
        $alumno->mayor = $mayoralu;
        try {
            $response = $client->post($uri, [
                'form_params' => [
                    'alumno' => json_encode($alumno)
                ]
            ]);
            //$alumno = json_decode($response->getBody());  
//echo $alumno->id . " " . $alumno->nombre;
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    default :
        echo "<br>" . "GET" . "<br>";

        try {
            $uri = 'http://localhost:8080/Apicutre/rest/cutre';
//$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
            $response = $client->get($uri);
            $alumno = json_decode($response->getBody());
            echo $alumno->id . " " . $alumno->nombre;
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
}

//insertar
?>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>

            function cargarAlumno(id, nombre, fecha_nacimiento, mayor) {
                document.getElementById("nombre").value = nombre;
                document.getElementById("idasignatura").value = id;
                document.getElementById("fecha").value = fecha_nacimiento;
                document.getElementById("mayor").checked = mayor;
            }
        </script>
    </head>
    <body>

        <table border = "1">
<?php
foreach ($alumnos as $alumno) {
    ?>
                <tr>
                    <td><input type="button" value="cargar <?php echo $alumno['ID'] ?>" 
                               onclick="cargarAlumno('<?php echo $alumno['ID'] ?>',
                                                   '<?php echo $alumno['NOMBRE'] ?>',
                                                   ' <?php echo $alumno['FECHA_NACIMIENTO'] ?>',
                                                   '<?php if ($alumno['MAYOR_EDAD'] == 1) echo 'checked' ?>')"/>
                    </td>
                    <td>
                               <?php echo $alumno['NOMBRE']; ?>
                    </td>

                    <td>
    <?php echo $alumno['FECHA_NACIMIENTO']; ?>
                    </td>
                    <td>
                        <?php ?><input type="checkbox"  <?php if ($alumno['MAYOR_EDAD'] == 1) echo 'checked' ?>/> 
                    </td>
                </tr>


<?php } ?> <?php
echo "FIN";
?>
            <!-- -->

        </table>
        <form action ="alumnos.php?op=op" method="get">
            <input type="hidden" id="idasignatura" name="id" />
            <input type="text" id="nombre" name="nombre" size="12"/>
            <input type="text" id="fecha" name="fecha" size="12"/>
            <input type="checkbox"  name="mayor" id="mayor"/>
            <button name="op" id="op" value="update"> actualizar</button>
            <button name="op" id="op" value="delete"> delete</button>
            <button name="op" id="op" value="insert"> insertar</button>
        </form>
    </body>
</html>
