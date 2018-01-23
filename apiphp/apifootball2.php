<?php 
require 'vendor/autoload.php';
use GuzzleHttp\Client;
$client = new Client();
$uri = 'http://api.football-data.org/v1/competitions/{id}/leagueTable';
$header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
$response = $client->get($uri, $header);
$json = json_decode($response->getBody());
var_dump($json);
?>
<html>
    <body>
        <table> <?php foreach ($json as $tablaliga) {
    ?>
                <tr>
                <td>
                    <a href="http://localhost:8000/apifootball.php?id=<?php echo $tablaliga->id ?>">
                        <?php echo $tablaliga->rank  ?>
                    </a>
                </td>
                <td>
                    <a href="http://localhost:8000/apifootball.php?id=<?php echo $tablaliga->id ?>">
                        <?php echo $tablaliga->team  ?>
                    </a>
                </td>
            </tr>
            <?php } ?>
        </table>

    </body>

</html>
<?php
