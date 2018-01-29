<?php
require 'vendor/autoload.php';
use GuzzleHttp\Client;
$client = new Client();
$uri = $_REQUEST['uri'];
$equipo =$_REQUEST['equipo'];
$header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
$response = $client->get($uri, $header);
$json = json_decode($response->getBody());

?>
<html>
    <body>
        <table> JUGADORES DEL <?php echo $equipo;?>
            <?php foreach ($json->players as $jugador) {
    ?>
                <tr>
                <td>
                        <?php echo 'nombre: '.$jugador->name  ?>
                   
                </td>
                <td>
                     <?php echo 'position: '.$jugador->position  ?>
                </td>
                <td>
                        <?php echo 'dorsal: '.$jugador->jerseyNumber  ?>
                   
                </td>
            </tr>
            <?php } ?>
        </table>

    </body>

</html>
<?php
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

