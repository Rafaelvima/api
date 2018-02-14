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




// Create connection
// Check connection
$alumno = new \stdClass;
$asignatura = new \stdClass;
$nota = new \stdClass;
$uri = 'http://localhost:8083/ApiCutreJava/rest/alumnos';
$response = $client->get($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"]]);
            $alumnos = json_decode($response->getBody());
$uri = 'http://localhost:8083/ApiCutreJava/rest/asignaturas';
 $response = $client->get($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"]]);
            $asignaturas = json_decode($response->getBody());

if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (isset($_REQUEST['id_alumno'])) {
    $id_alumno = $_REQUEST['id_alumno'];
} else {
    $id_alumno = null;
}
if (isset($_REQUEST['id_asignatura'])) {
    $id_asignatura = $_REQUEST['id_asignatura'];
} else {
    $id_asignatura = null;
}
if (isset($_REQUEST['nota'])) {
    $vernota = $_REQUEST['nota'];
} else {
    $vernota = null;
}
$uri = 'http://localhost:8083/ApiCutreJava/rest/alumnos';
switch ($op) {
    case "ver":
        $nota->id_asignatura = $idasig;
        $nota->id_alumno = $idalu;
       try {
          
        $response = $client->get($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"]]);
    $nota = json_decode($response->getBody());

        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }

        break;
    case "guardar":

       $nota->id_asignatura = $idasig;
        $nota->id_alumno = $idalu;
        $nota->nota=$vernota;
        try {
            $response = $client->post($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                'form_params' => [
                    'nota' => json_encode($nota)
                ]]
            ]);
            $nota = json_decode($response->getBody());
            echo "Nota " . $nota->nota . " modificada correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        if (!($nota->nota == null)) {
            
        try {
            $response = $client->put($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                'query' => [
                    'nota' => json_encode($nota)
                ]]
            ]);
            //todo ok hasta aqui y bajaria
            $nota = json_decode($response->getBody());
            echo $nota->id_alumno . " " . $nota->id_asignatura ." nota= ". $nota->nota. " asignatura insertada correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        }
        break;
    case "borrar":
        $nota->id_asignatura = $idasig;
        $nota->id_alumno = $idalu;
        $nota->nota=$vernota;
        
        try {
            $response = $client->delete($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                'query' => [
                    'nota' => json_encode($nota)
                ]]
            ]);
            $nota = json_decode($response->getBody());
            echo "Nota " . $nota->nombre . " borrado correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }

}

?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        <script>
            function cargarAlumno(id) {
                document.getElementById("id_alumno").value = id;
            }
            function cargarAsignatura(id) {
                document.getElementById("id_asignatura").value = id;
            }
            function guardar() {
                document.getElementById("op").value = "guardar";
            }
            function borrar() {
                document.getElementById("op").value = "borrar";
            }
            function cargarNota() {
                document.getElementById("op").value = "ver";
            }

        </script>
    </head>
    <body>
        <h1>ALUMNOS</h1>
        <table border="1">
            <tr>
                <td>
                    <select id="alumno" onchange="cargarAlumno(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected>Selecciona un alumno</option>
                        <?php foreach ($alumnos as $alumno) { ?>
                            <option value="<?php echo $alumno['ID'] ?>" name="<?php echo $alumno['NOMBRE'] ?>"><?php echo $alumno['NOMBRE'] ?></option>
                        <?php } ?>
                    </select>
                </td>

            </tr>
        </table>
        <span>Asignatura: </span>
        <table border="1">
            <tr>
                <td>
                    <select id="asignatura" onchange="cargarAsignatura(this.value, this.options[this.selectedIndex].innerHTML)">
                        <option disabled selected>Selecciona una asignatura</option>
                        <?php foreach ($asignaturas as $asignatura) { ?>
                            <option value="<?php echo $asignatura['id'] ?>"><?php echo $asignatura['NOMBRE'] ?></option>
                        <?php } ?>
                    </select>
                </td>

            </tr> 

        </table>
        <form action ="notas.php?op=" method="get">
            <input type="hidden" id="nombre" name="nombre" size="12"/>
            id_alumno: <input type="text" id="id_alumno" name="id_alumno" value="<?php echo $id_alumno ?>" size="12"/>
            id_asignatura: <input type="text" id="id_asignatura" name="id_asignatura" value="<?php echo $id_asignatura ?>" s="12"/>
            nota: <input type="text" id="nota" name="nota" value="<?php echo $nota ?>" >

            <input type="hidden" id="op" name="op"/>
            <button value="seleccionar" onclick="cargarNota();"> cargar</button>
            <button value="update" onclick="guardar();"> guardar</button>
            <button value="delete" onclick="borrar();"> delete  </button>
        </form>
    </body>
</html>

 <?php
       
?>
