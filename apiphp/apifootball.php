<?php

require 'vendor/autoload.php';

use GuzzleHttp\Client;

$client = new Client();

$uri = 'http://api.football-data.org/v1/competitions';
$header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
$response = $client->get($uri, $header);
$json = json_decode($response->getBody());

?>
<html>
    <body>
        <table> <?php foreach ($json as $competicion) {
    ?>
                <tr>
                <td>
                    <a href="http://localhost:8000/apifootball2.php?id=<?php echo $competicion->id ?>">
                        <?php echo $competicion->id ?>
                    </a>
                </td>
                <td>
                    <a href="http://localhost:8000/apifootball2.php?id=<?php echo $competicion->id ?>">
                        <?php echo $competicion->caption ?>
                    </a>
                </td>
            </tr>
            <?php } ?>
        </table>

    </body>

</html>
<?php
/*
$uri = 'http://api.football-data.org/v1/competitions/?season=2017';
$header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
$response = $client->get($uri, $header);          
$json = json_decode($response->getBody());  
var_dump($json);
*/
