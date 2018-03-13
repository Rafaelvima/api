<?php
require 'vendor/autoload.php';

use GuzzleHttp\Client;
use GuzzleHttp\Exception\ClientException;

$client = new Client();

$uri = 'http://localhost:8083/Examen2EVA/rest/RestUsuaros';
//$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));


if (isset($_GET['name'])){$name = $_GET['name'];} else {$name=null;}
if (isset($_GET['password'])){$password = $_GET['password'];} else {$password=null;}
if (isset($_GET['op'])){$op = $_GET['op'];} else {$op=null;}

$usuario = new \stdClass;

switch ($op) 
{
    case "UPDATE":
        $usuario->name = $name;
        $usuario->password = $password;
        
        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->request('POST', $uri, ['query' => ['usuario' => json_encode($usuario)]]);
        $usuarios = json_decode($request->getBody());
        if ($usuarios == 1)
        {
            echo "Se ha actualizado el usuario correctamente";
        }
        else
        {
            echo "Ha habido un error al actualizar el usuario.";
        }
        break;
    
    case "DELETE":
        $usuario->name = $name;

        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->delete($uri, ['query' => ['usuario' => json_encode($usuario), 'borrar' => "no"]]);
        $usuarios = json_decode($request->getBody());
        
        if ($usuarios == 2)
        {
            echo "No se ha borrado porque hay datos enlazados, pulsa el botón pasa borrar también los datos enlazados<br>";
            echo "<form action='usuariosDAO.php' methot='get'><input type='hidden' name='op' value='DELETE_CASCADE' /><input type='hidden' name='name' value='".$name."' /><input type='submit' value='Confirmar borrado'></form>";
        }
        else if ($usuarios == 0)
        {
            echo "El usuario que quieres borrar no existe";
        }
        else
        {
             echo "Se ha borrado";
        }
        break;

    case "DELETE_CASCADE":
        $usuario->name = $name;

        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->delete($uri, ['query' => ['usuario' => json_encode($usuario), 'borrar' => "si"]]);
        $usuarios = json_decode($request->getBody());
        
        if ($usuarios == 0)
        {
            echo "Ha habido un eror";
        }
        else
        {
             echo "Se ha borrado";
        }
        break;

    case "INSERT":
        
        $usuario->name = $name;
        $usuario->password = $password;
        
        //$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));
        $request = $client->request('PUT', $uri, ['query' => ['usuario' => json_encode($usuario)]]);
        $alumnos = json_decode($request->getBody());
        if ($alumnos == 1)
        {
            echo "Se ha introducido el usuario correctamente";
        }
        else
        {
            echo "Ha habido un error al introducir el usuario. Puede que esté repetido";
        }
        break;
}

