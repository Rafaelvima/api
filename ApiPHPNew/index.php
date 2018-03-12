<?php
require 'vendor/autoload.php';
use GuzzleHttp\Client;
$client = new Client();
$user = new \stdClass;
$uri = 'http://localhost:8083/DefinitivaApiServer/rest/users';

if (isset($_REQUEST["op"])) {
    
    $op = $_REQUEST["op"];   
    $usern = $_REQUEST["user"];
    $userp = $_REQUEST["pass"];
    
    $user->us_nom = $usern;
    $user->us_pass = $userp;

    
    switch ($op){
        case "insert":
            try {
                $response = $client->put($uri,
                [
                    'query' => ['user' => json_encode($user)]
                    //'headers' => ['apiKey' => 'holaQueTalEstaEsMiPrimeraKey']                  
                ]);
                
                $decode=json_decode($response->getBody());
                if($decode != null){
                    $mensaje = "Operacion realizada con exito";
                } else {
                    $mensaje= "Operacion no realizada correctamente";
                }

            } catch (Exception $exception) {
                if ($exception->getCode() == 409) {
                    $mensaje = "Numero de cuenta incorrecto";
                }else{
                    $mensaje = "Error y explosion total";
                }
            }
            break;
            case "update":
            try {
                $response = $client->post($uri,
                [
                    'query' => ['user' => json_encode($user)]
                    //'headers' => ['apiKey' => 'holaQueTalEstaEsMiPrimeraKey']                  
                ]);
                
                $decode=json_decode($response->getBody());
                if($decode != null){
                    $mensaje = "Operacion realizada con exito";
                } else {
                    $mensaje= "Operacion no realizada correctamente";
                }

            } catch (Exception $exception) {
                if ($exception->getCode() == 409) {
                    $mensaje = "Numero de cuenta incorrecto";
                }else{
                    $mensaje = "Error y explosion total";
                }
            }
            break;
            case "delete":
            try {
                $response = $client->delete($uri,
                [
                    'query' => ['user' => json_encode($user)]
                    //'headers' => ['apiKey' => 'holaQueTalEstaEsMiPrimeraKey']                  
                ]);
                
                $decode=json_decode($response->getBody());
                if($decode != null){
                    $mensaje = "Operacion realizada con exito";
                } else {
                    $mensaje= "Operacion no realizada correctamente";
                }

            } catch (Exception $exception) {
                if ($exception->getCode() == 409) {
                    $mensaje = "Numero de cuenta incorrecto";
                }else{
                    $mensaje = "Error y explosion total";
                }
            }
            break;
            case"all":
                 $response = $client->get($uri);
            $decode=json_decode($response->getBody());
                if($decode != null){
                     foreach ($decode as $deco)
                     {
                         echo "<h1>".$deco->us_nom."</h1><h1>".$deco->us_pass."</h1>";
                          }
                
                    $mensaje = "Operacion realizada con exito";
                } else {
                    $mensaje= "Operacion no realizada correctamente";
                }

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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <title>Operaciones Cliente API REST</title>
        <style>
            body{
                text-align:center !important;
                font-family:"Comic Sans MS", cursive, sans-serif!important;
                background-color:beige;
            }
            table{
                text-align: center;
                margin-left: auto;
                margin-right: auto
            }
            h1{
                font-size:50px;
            }
            th{
                font-size:30px;
            }
        </style>
    </head>
    <body>
        <div class="container"><br>
            <h1>Operaciones Cliente API REST</h1><br><hr><br>
            <div class="row">           
                <form action="index.php" method="post" id="formulariocliente" class="col-12">
                    <table>
                        <tr>
                            <td><label><b>user</b></label></td>
                            <td><input type="text" id="user" name="user"></td>
                        </tr>
                        <tr>
                            <td><label><b>pass</b></label></td>
                            <td><input type="text" id="pass" name="pass"></td>
                        </tr>
                       
                    </table><br>        
                    <button name="op" value="insert" class="btn btn-success">Ingresar al cliente</button>
                     <button name="op" value="update" class="btn btn-success">update al cliente</button>
                      <button name="op" value="delete" class="btn btn-success">delete al cliente</button>
                    <button name="op" value="all" class="btn btn-success">all al cliente</button>
                </form>
                <?php
                
                if(isset($mensaje)){
                    echo $mensaje;
                }
                ?>
            </div>  
        </div>
    </body>
</html>