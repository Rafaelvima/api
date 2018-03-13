<?php
require 'vendor/autoload.php';

use GuzzleHttp\Client;
use GuzzleHttp\Exception\ClientException;

$client = new Client();
$cliente = new \stdClass;
$producto = new \stdClass;
$uric = 'http://localhost:8080/Examen2EVARecuperacion/rest/clientes';
$urip = 'http://localhost:8080/Examen2EVARecuperacion/rest/productos';
//$uris = 'http://localhost:8080/Examen2EVA/rest/restusuarios';

if (isset($_REQUEST["op2"])) {
    if (isset($_REQUEST['op2'])) {
        $op2 = $_REQUEST['op2'];
    } else {
        $op2 = null;
    }
    if (isset($_REQUEST['pass'])) {
        $passC = $_REQUEST['pass'];
    } else {
        $passC = null;
    }
    if (isset($_REQUEST['nombreC'])) {
        $nombreC = $_REQUEST['nombreC'];
    } else {
        $nombreC = null;
    }
    if (isset($_REQUEST['nombreP'])) {
        $nombreP = $_REQUEST['nombreP'];
    } else {
        $nombreP = null;
    }
    if (isset($_REQUEST['stock'])) {
        $stock = $_REQUEST['stock'];
    } else {
        $stock = 0;
    }
    if (isset($_REQUEST['saldo'])) {
        $saldo = $_REQUEST['saldo'];
    } else {
        $saldo = 0;
    }
    if (isset($_REQUEST['precio'])) {
        $precio = $_REQUEST['precio'];
    } else {
        $precio = 0;
    }
    switch ($op2) {
        case"allP":
            $cliente->name = $nombreC;
            $cliente->password = $passC;
            $cliente->saldo = 100;
            $response = $client->get($urip, ['query' => ['cliente' => json_encode($cliente)],
                                                'headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode != null) {

                foreach ($decode as $deco) {
                    echo "<h1>" . $deco->nombre . "</h1> " . $deco->stock . " -> " . $deco->precio . "</br>";
                }

                echo "Ole que buena aqui tiene sus cajas";
            } else {
                echo "error de algo no puedes ver tus cosas (login mal)";
            }
            break;
        case"allC":
            $response = $client->get($uric, ['headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode != null) {

                foreach ($decode as $deco) {
                    echo "<h1>" . $deco->name . "</h1> " . $deco->password . " -> " . $deco->saldo . "</br>";
                }

                echo "Ole que buena aqui tiene sus cajas";
            } else {
                echo "error de algo no puedes ver tus cosas (login mal)";
            }
            break;
        case"updateC":
            $cliente->name = $nombreC;
            $cliente->password = $passC;
            $cliente->saldo = $saldo;
            $response = $client->post($uric, ['query' => ['cliente' => json_encode($cliente)],
                                                'headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode > 0) {
                echo "Acrulizado";
            } else {
                echo "NO actualixad0";
            }
            break;
        case"insertC":
            $cliente->name = $nombreC;
            $cliente->password = $passC;
            $cliente->saldo = $saldo;
            $response = $client->put($uric, ['query' => ['cliente' => json_encode($cliente)],
                                                'headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode > 0) {
                echo "insertado";
            } else {
                echo "NO insertado";
            }
            break;
        case"deleteC":
            $cliente->name = $nombreC;
            $cliente->password = $passC;
            $cliente->saldo = $saldo;
            $response = $client->delete($uric, ['query' => ['cliente' => json_encode($cliente)],
                                                'headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode > 0) {
                echo "borrado";
            } else {
                echo "NO borrado";
            }
            break;
        case"insertP":
            $cliente->name = $nombreC;
            $cliente->password = $passC;
            $cliente->saldo = $saldo;
            $producto->nombre = $nombreP;
            $producto->stock = $stock;
            $producto->precio = $precio;
            $response = $client->put($urip, ['query' => ['cliente' => json_encode($cliente), 'producto' => json_encode($producto)],
                                                'headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode > 0) {
                echo "insertado";
            } else {
                echo "NO insertado";
            }
            break;
        case"updateP":
            $cliente->name = $nombreC;
            $cliente->password = $passC;
            $cliente->saldo = $saldo;
            $producto->nombre = $nombreP;
            $producto->stock = $stock;
            $producto->precio = $precio;
            $response = $client->post($urip, ['query' => ['cliente' => json_encode($cliente), 'producto' => json_encode($producto)],
                                                'headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode > 0) {
                echo "actualizado";
            } else {
                echo "NO actualizado";
            }
            break;
        case"deleteP":
            $cliente->name = $nombreC;
            $cliente->password = $passC;
            $cliente->saldo = $saldo;
            $producto->nombre = $nombreP;
            $producto->stock = $stock;
            $producto->precio = $precio;
            $response = $client->delete($urip, ['query' => ['cliente' => json_encode($cliente), 'producto' => json_encode($producto)], 'headers' => ['Api' => 'dentro']]);
            $decode = json_decode($response->getBody());
            if ($decode > 0) {
                echo "borrado";
            } else {
                echo "NO borrado";
            }
            break;
    }
}
?>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <title>Operaciones Cliente API REST</title>
    </head>
    <body>
        <div class="container"><br>
            <h1>Operaciones Cliente API REST</h1><br><hr><br>
            <div class="row">           
                <form action="index.php" method="post" id="formu" class="col-12">
                    <table>
                        <tr>
                            <td><label><b>Nombre Cliente</b></label></td>
                            <td><input type="text" id="nombreC" name="nombreC"></td>
                        </tr>
                        <tr>
                            <td><label><b>password</b></label></td>
                            <td><input type="text" id="pass" name="pass"></td>
                        </tr>
                        <tr>
                            <td><label><b>saldo</b></label></td>
                            <td><input type="text" id="saldo" name="saldo"></td>
                        </tr>
                        <tr>
                            <td><label><b>Nombre Producto</b></label></td>
                            <td><input type="text" id="nombreP" name="nombreP"></td>
                        </tr>
                        <tr>
                            <td><label><b>stock</b></label></td>
                            <td><input type="text" id="stock" name="stock"></td>
                        </tr>
                        <tr>
                            <td><label><b>precio Producto</b></label></td>
                            <td><input type="text" id="precio" name="precio"></td>
                        </tr>

                    </table><br>        

                    <button name="op2" value="allC" class="btn btn-success">All CLIENTES</button>
                    <button name="op2" value="allP" class="btn btn-success">All PRODUCTOS</button>
                    <button name="op2" value="updateC" class="btn btn-success">UPD CLIENTES</button>
                    <button name="op2" value="deleteC" class="btn btn-success">DEL CLIENTES</button>
                    <button name="op2" value="insertC" class="btn btn-success">ADD CLIENTES</button>
                    <button name="op2" value="updateP" class="btn btn-success">UPD PRODUCTO</button>
                    <button name="op2" value="deleteP" class="btn btn-success">DEL PRODUCTO</button>
                    <button name="op2" value="insertP" class="btn btn-success">ADD PRODUCTO</button>
                </form>
<?php
if (isset($mensaje)) {
    echo $mensaje;
}
?>
            </div>  
        </div>
    </body>
</html>