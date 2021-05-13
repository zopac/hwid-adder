<?php

if(isset($_GET["hwid"])){
    $file = fopen("hwid.txt", "a");

    $content = file_get_contents("hwid.txt");

    if(strpos($content, $_GET["hwid"]) === false){
        fwrite($file, $_GET["hwid"]."\n");
    }
    fclose($file);
}


