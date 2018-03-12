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


$uri = 'http://localhost:8083/Examen2EVA';

// Create connection
// Check connection
$user = new \stdClass;
if (isset($_REQUEST['op'])) {
    $op = $_REQUEST['op'];
} else {
    $op = null;
}
if (!isset($mensaje)) {
    $mensaje = null;
}

if (isset($_REQUEST['name'])) {
    $username = $_REQUEST['name'];
} else {
    $username = null;
}
if (isset($_REQUEST['password'])) {
    $userpass = $_REQUEST['password'];
} else {
    $userpass = null;
}

switch ($op) {
    case "insert":
        $user->name = $username;
        $user->password = $userpass;
        try {
            $response = $client->put($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                'query' => [
                    'user' => json_encode($user)
                ]]
            ]);
            //todo ok hasta aqui y bajaria
            $user = json_decode($response->getBody());
            echo $user->id . " " . $user->nombre . " user insertado correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }
        break;
    case "delete":
        $user->name = $username;
        try {
            $response = $client->delete($uri,[
            'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                'query' => [
                    'user' => json_encode($user)
                ]]
            ]);
            $user = json_decode($response->getBody());
            echo "User " . $user->name . " borrado correctamente";
        } catch (RequestException $exception) {

            echo $exception->getCode();
            $error = json_decode($exception->getResponse()->getBody());
            echo $error->mensaje;
        }

        break;
    case "update":
        $user->id = $username;
        $user->nombre = $userpass;
        try {
            $response = $client->post($uri, [
                'header' => ["ApiKey" => "447878d6ad3e4da7bc65bac030cd061e"], [
                    'form_params' => [
                        'user' => json_encode($user)
                    ]
            ]]);
            $user = json_decode($response->getBody());
            echo "User " . $user->name . " modificado correctamente";
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

            $users = json_decode($response->getBody());

            foreach ($users as $user) {
                echo $user->name . " " . $user->password . "<br>";
            }
            ?> 
            <html>
                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                    <title>JSP Page</title>
                    <script>

                        function cargarUser(name,password) {
                            document.getElementById("name").value = name;
                            document.getElementById("password").value = password;
                        }
                    </script>
                </head>
                <body>

                    <table border = "1">
                        <?php
                        foreach ($users as $user) {
                            ?>
                            <tr>
                                <td><input type="button" value="cargar <?php echo $user->id ?>" 
                                           onclick="cargarUser(
                                                                           '<?php echo $user->name ?>',
                                                                           ' <?php echo $user->password ?>')"/>
                                </td>
                                <td>
                                    <?php echo $user->name; ?>
                                </td>

                                <td>
                                    <?php echo $user->password; ?>
                                </td>
                                
                            </tr>


                        <?php } ?> <?php
                        echo "FIN";
                        ?>
                        <!-- -->

                    </table>
                    <form action ="users.php?op=op" method="get">
                      
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
