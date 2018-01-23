<?php
require 'vendor/autoload.php';
use GuzzleHttp\Client;
$client = new Client();
$uri = 'http://api.football-data.org/v1/competitions/{id}/temas';
$header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
$response = $client->get($uri, $header);
$json = json_decode($response->getBody());
var_dump($json);
?>
<html>
    <body>
        <table> <?php foreach ($json as $equipo) {
    ?>
                <tr>
                <td>
                    <a href="http://localhost:8000/apifootball.php?id=<?php echo $equipo->id ?>">
                        <?php echo $equipo->name  ?>
                    </a>
                </td>
                <td>
                    <a href="http://localhost:8000/apifootball.php?id=<?php echo $equipo->id ?>">
                        <?php echo $equipo->name ?>
                    </a>
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

