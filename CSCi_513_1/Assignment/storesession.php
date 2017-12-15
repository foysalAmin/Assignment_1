<?php
     //echo $_POST["username"];
     session_start();
     $_SESSION['username'] = $_POST['username'];
// Set session variables
    $_SESSION["favcolor"] = "green";
    $_SESSION["favanimal"] = "cat";
    echo "Session variables are set.";
     echo $_SESSION['username'] ;
    ?>