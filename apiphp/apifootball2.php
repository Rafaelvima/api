<?php 
require 'vendor/autoload.php';
use GuzzleHttp\Client;
$hola=$_REQUEST['id'];
$client = new Client();
$uri = 'http://api.football-data.org/v1/competitions/'.$hola.'/teams';
$header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
$response = $client->get($uri, $header);
$json = json_decode($response->getBody());

?>
<html>
    <body>
        
        <table> <?php foreach ($json->teams as $equipo) {
    ?>
                <tr>
                <td>
                    <a href="http://localhost:8000/apifootball3.php?uri=<?php echo $equipo->_links->players->href.'&equipo='.$equipo->name?>">
                        <?php echo $equipo->name  ?>
                    </a>
                </td>
                <td>
                    <a href="http://localhost:8000/apifootball3.php?uri=<?php echo $equipo->_links->players->href.'&equipo='.$equipo->name?>">
                       <?php echo $equipo->code  ?>
                    </a>
                </td>
            </tr>
            <?php } ?>
        </table>

    </body>

</html>
<?php
