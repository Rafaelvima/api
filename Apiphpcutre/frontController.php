<?php
require_once 'config\Config.php';
require_once 'src\controller\Nivel1.php';

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
use controller\ClassSession;



$clase = $_REQUEST["clase"];

$var = Config::CACA;

if ($clase == "nivel1")
{
$controller = new ClassSession();

}
else
{
//   $controller = new ClassSession2(); 
}


//call_user_func( array( $controller, "Index" ) );
$controller->index();