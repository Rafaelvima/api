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


$uri = 'http://localhost:8083/ApiCutreJava/rest/asignaturas';

// Create connection
// Check connection
$asignatura = new \stdClass;
if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (isset($_REQUEST['idasignatura'])) {
    $idasig = $_REQUEST['idasignatura'];
} else {
    $idasig = null;
}
if (isset($_REQUEST['nombre'])) {
    $nombreasig = $_REQUEST['nombre'];
} else {
    $nombreasig = null;
}
if (isset($_REQUEST['curso'])) {
    $cursoasig = ($_REQUEST['curso']);
} else {
    $cursoasig = null;
}
if (isset($_REQUEST['ciclo'])) {
    $cicloasig = ($_REQUEST['ciclo']);
} else {
    $cicloasig = null;
}
switch ($op) {
    case "insert":
        $asignatura->id = $idasig;
        $asignatura->nombre = $nombreasig;
        $asignatura->curso = $cursoasig;
        $asignatura->ciclo = $cicloasig;
        try {
            $response = $client->put($uri, [
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
            ]);
            //todo ok hasta aqui y bajaria
            $asignatura = json_decode($response->getBody());
            echo $asignatura->id . " " . $asignatura->nombre . " asignatura insertada correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    case "update":
        $asignatura->id = $idasig;
        $asignatura->nombre = $nombreasig;
        $asignatura->curso = $cursoasig;
        $asignatura->ciclo = $cicloasig;
        try {
            $response = $client->post($uri, [
                'form_params' => [
                    'asignatura' => json_encode($asignatura)
                ]
            ]);
            $asignatura = json_decode($response->getBody());
            echo "Asignatura " . $asignatura->nombre . " modificada correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    case "delete":
        $asignatura->id = $idasig;
        try {
            $response = $client->delete($uri, [
                'query' => [
                    'asignatura' => json_encode($asignatura)
                ]
            ]);
            $asignatura = json_decode($response->getBody());
            echo "Alumno " . $asignatura->nombre . " borrado correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }

        break;
    default :
        echo "<br>" . "GET" . "<br>";

        try {
            $response = $client->get($uri);

            $asignaturas = json_decode($response->getBody());

            foreach ($asignaturas as $asignatura) {
                echo $asignatura->id . " " . $asignatura->nombre . "<br>";
            }
            ?>
            <html>
                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>JSP Page</title>
                    <script>

                        function cargarAsignatura(id, nombre, curso, ciclo) {
                            document.getElementById("nombre").value = nombre;
                            document.getElementById("idasignatura").value = id;
                            document.getElementById("curso").value = curso;
                            document.getElementById("ciclo").value = ciclo;

                        }
                    </script>
                    <script>
                        function updForm() {
                            document.getElementById("op").value = "update";
                        }
                        function delForm() {
                            document.getElementById("op").value = "delete";
                        }
                        function insForm() {
                            document.getElementById("op").value = "insert";
                        }
                    </script>

                </head>
                <body>

                    <h1>ASIGNATURAS</h1>
                    <table border = "1">
                        <?php
                        foreach ($asignaturas as $asignatura) {
                            ?>
                            <tr>
                                <td><input type="button" value="cargar <?php echo $asignatura->id ?>" 
                                           onclick="cargarAlumno('<?php echo $asignatura->id ?>',
                                                               '<?php echo $asignatura->nombre ?>',
                                                               ' <?php echo $asignatura->curso ?>',
                                                               '<?php echo $asignatura->ciclo ?>')"/>
                                </td>
                                <td>
                                    <?php echo $asignatura->nombre; ?>
                                </td>

                                <td>
                                    <?php echo $asignatura->curso; ?>
                                </td>
                                <td>
                                    <?php echo $asignatura->ciclo; ?>
                                </td>
                                
                            </tr>
                        <?php } ?> <?php
                        echo "FIN";
                        ?>

                        <!-- -->

                    </table>
                    <form action ="asignaturas.php?op=" method="get">
                        <input type="hidden" id="idasignatura" name="idasignatura" />
                        <input type="text" id="nombre" name="nombre" size="12"/>
                        <input type="text" id="curso" name="curso" size="12"/>
                        <input type="text"  name="ciclo" id="ciclo"/>
                        <input type="hidden" id="op" name="op"/>
                        <button value="update" onclick="updForm();"> actualizar</button>
                        <button value="delete" onclick="delForm();"> delete</button>
                        <button value="insert" onclick="insForm();"> insertar</button>
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
