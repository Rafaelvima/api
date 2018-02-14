<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<?php
require 'vendor/autoload.php';

use GuzzleHttp\Client;
use GuzzleHttp\Exception\ClientException;
use GuzzleHttp\Exception\RequestException;

$client = new Client();
$header = array('headers' => array('ApiKey' => '447878d6ad3e4da7bc65bac030cd061e'));


$uri = 'http://localhost:8083/ApiCutreJava/rest/alumnos';

// Create connection
// Check connection
$alumno = new \stdClass;
if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (!isset($mensaje)) {
    $mensaje = null;
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
        $alumno->fecha_nacimiento = $fechaalu;
        $alumno->mayor_edad = $mayoralu;
        try {
            $response = $client->put($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                'query' => [
                    'alumno' => json_encode($alumno)
                ]]
            ]);
            //todo ok hasta aqui y bajaria
            $alumno = json_decode($response->getBody());
            echo $alumno->id . " " . $alumno->nombre . " alumno insertado correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    case "delete":
        $alumno->id = $idalu;
        try {
            $response = $client->delete($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                'query' => [
                    'alumno' => json_encode($alumno)
                ]]
            ]);
            $alumno = json_decode($response->getBody());
            echo "Alumno " . $alumno->nombre . " borrado correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }

        break;
    case "update":
        $alumno->id = $idalu;
        $alumno->nombre = $nombrealu;
        $alumno->fecha_nacimiento = $fechaalu;
        $alumno->mayor_edad = $mayoralu;
        try {
            $response = $client->post($uri, [
                'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                    'form_params' => [
                        'alumno' => json_encode($alumno)
                    ]
            ]]);
            $alumno = json_decode($response->getBody());
            echo "Alumno " . $alumno->nombre . " modificado correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    default :
        echo "<br>" . "GET" . "<br>";

        try {
            $response = $client->get($uri, [
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"]]);

            $alumnos = json_decode($response->getBody());

            foreach ($alumnos as $alumno) {
                echo $alumno->id . " " . $alumno->nombre . "<br>";
            }
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
                                <td><input type="button" value="cargar <?php echo $alumno->id ?>" 
                                           onclick="cargarAlumno('<?php echo $alumno->id ?>',
                                                                           '<?php echo $alumno->nombre ?>',
                                                                           ' <?php echo $alumno->fecha_nacimiento ?>',
                                                                           '<?php if ($alumno->mayor_edad == 1) echo 'checked' ?>')"/>
                                </td>
                                <td>
                                    <?php echo $alumno->nombre; ?>
                                </td>

                                <td>
                                    <?php echo $alumno->fecha_nacimiento; ?>
                                </td>
                                <td>
                                    <?php ?><input type="checkbox"  <?php if ($alumno->mayor_edad == 1) echo 'checked' ?>/> 
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

            <?php
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
}
?>
