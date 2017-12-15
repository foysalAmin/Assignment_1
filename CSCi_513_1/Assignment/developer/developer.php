<?php
     echo $_POST["username"];
     $number_passed=20;//this is calculated earlier in the code
     $number_total=100;//also calculated earlier in the code
     $date=date('m/d/y');
     $username= "mamin";  //username here
     $password= "df56po9m"; //password here
     $database= "//oracle1.aero.und.edu::1521/cs513.aero.und.edu"; //database connection string here

     $connection=oci_connect( $username, $password, $database);

     $sql= "INSERT into developers (name) values ('"+ $_POST["username"]+"')";

     $st= oci_parse($connection, $sql);
     oci_execute($st);
    ?>