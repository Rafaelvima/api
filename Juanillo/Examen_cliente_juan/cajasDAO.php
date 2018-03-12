<?php
require 'vendor/autoload.php';

use GuzzleHttp\Client;
use GuzzleHttp\Exception\ClientException;

$client = new Client();

$uri = 'http://localhost:8080/Examen2EVA/rest/RestCajas';
//$header = array('headers' => array('X-Auth-Token' => '447878d6ad3e4da7bc65bac030cd061e'));


if (isset($_GET['nombre'])){$nombre = $_GET['nombre'];} else {$nombre=null;}
if (isset($_GET['op'])){$op = $_GET['op'];} else {$op=null;}
if (isset($_GET['name'])){$name = $_GET['name'];} else {$name=null;}
if (isset($_GET['password'])){$password = $_GET['password'];} else {$password=null;}
if (isset($_GET['cosa'])){$cosa = $_GET['cosa'];} else {$cosa=null;}
if (isset($_GET['cantidad'])){$cantidad = $_GET['cantidad'];} else {$cantidad=null;}

if (isset($_GET['funcionalidad'])){$funcionalidad = $_GET['funcionalidad'];} else {$funcionalidad=null;}

$caja = new \stdClass;
$usuarios = new \stdClass;
$cosa1 = new \stdClass;

switch ($op) 
{
    case "INSERT":
        
        if ($funcionalidad == "add_caja")
        {
            $caja->nombre = $nombre;
            $usuarios->name = $name;
            $usuarios->password = $password;

            $request = $client->request('PUT', $uri, ['query' => ['caja' => json_encode($caja), 'usuario' =>json_encode($usuarios), 'funcionalidad' => "add_caja"]]);
            $cajas = json_decode($request->getBody());

            if ($cajas == 1)
            {
                echo "La caja se ha introducido correctamente";
            }
            else
            {
                echo "Ha habido un error al introducir la caja, puede que la caja ya exista";
            }
        }
        else if ($funcionalidad == "add_cosa")
        {
            $caja->nombre = $nombre;
            $cosa1->nombre = $cosa;
            $cosa1->cantidad = $cantidad;
            
            $request = $client->request('PUT', $uri, ['query' => ['caja' => json_encode($caja), 'cosa' => json_encode($cosa1), 'funcionalidad' => 'add_cosa']]);
            $cosas = json_decode($request->getBody());

            if ($cosas == 1)
            {
                echo "La cosa se ha introducido correctamente";
            }
            else
            {
                echo "Ha habido un error al introducir la cosa, puede que la cosa ya exista";
            }
        }
        else if ($funcionalidad == "add_cantidad")
        {
            $caja->nombre = $nombre;
            $cosa1->nombre = $cosa;
            $cosa1->cantidad = $cantidad;
            
            $request = $client->request('PUT', $uri, ['query' => ['caja' => json_encode($caja), 'cosa' => json_encode($cosa1), 'funcionalidad' => 'add_cantidad']]);
            $cosas = json_decode($request->getBody());

            if ($cosas == 1)
            {
                echo "Se ha actualizado la cantidad de cosas";
            }
            else
            {
                echo "Ha habido un error al actualizar la cantidad de cosas, puede que la cosa no exista";
            }
        }
        
        
        break;
    case "POST":
        $caja->nombre = $nombre;
        $caja->cosas= null;
        
        $request = $client->request('POST', $uri, ['query' => ['caja' => json_encode($caja)]]);
        $cajas = json_decode($request->getBody());
        
        echo $cajas->nombre;
        
        break;
}