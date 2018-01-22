<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*

    $uri = 'http://api.football-data.org/v1/competitions';
    $reqPrefs['http']['method'] = 'GET';
    $reqPrefs['http']['header'] = 'X-Auth-Token: f91c48721aa14ab9ba9f12ad3ee1b8c0';
    $stream_context = stream_context_create($reqPrefs);
    $response = file_get_contents($uri, false, $stream_context);
    $competiciones = json_decode($response);
    
    foreach ( $competiciones as $competicion)
    {
        echo $competicion->caption . " <br>";
    }
           
*/
    require 'vendor/autoload.php';
    use GuzzleHttp\Client;  
    $client = new Client();

    $uri = 'http://api.football-data.org/v1/competitions';
    $header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
    $response = $client->get($uri, $header);          
    $json = json_decode($response->getBody());  
    
    
    var_dump($json);?>
<html>
    <body>
        <table>
            <tr>
                <td>
                    <button id="id" name="id" value=<?php $competicion->id ?>></button>
                </td>
            </tr>
        </table>
            
    </body>
    
</html>
     foreach ( $json as $competicion)
    {
        echo $competicion->id . " ". $competicion->caption ." <br>";
    }
    /*
     $uri = 'http://api.football-data.org/v1/competitions/?season=2017';
    $header = array('headers' => array('X-Auth-Token' => 'f91c48721aa14ab9ba9f12ad3ee1b8c0'));
    $response = $client->get($uri, $header);          
    $json = json_decode($response->getBody());  
    var_dump($json);
 */