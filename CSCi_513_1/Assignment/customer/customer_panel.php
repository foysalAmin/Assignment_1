<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
     <title>Developer Addition</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!--[if IE]><link rel="stylesheet" type="text/css" media="screen, projection" 
      href="http://www.blueprintcss.org/blueprint/ie.css"><![endif]-->
    <script type="text/javascript"
      src="//code.jquery.com/jquery-1.4.4.min.js"></script>
<style type="text/css">
    object { width: 600px; height: 500px;}
    </style>


  </head>
  <body>
        <?php
     //echo $_POST["username"];
    session_start();
    $username= $_SESSION['username'] ;
    ?>
   <input type="hidden" id="username" name="username" value="<?php echo $username; ?>" />

  <nav class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Game Store</a>
    </div>
    <ul class="nav navbar-nav">
    <li><a href="../index.html">Home</a></li>
      <li><a href="../developer/developer_login.html">Developer</a></li>
      <li><a href="../admin/admin_login.html">Admin</a></li>
     </ul>
    </div>
  </nav>

  <div class="container">
  <div class="row">
  <div class="col-md-3">
  <!--  <input type= "button" value= "List All Data" class="btn btn-default btn-block"  onclick= "populateData(event)" /> -->
   <input type= "button" value= "Search And Purchased Games" class="btn btn-default btn-block" onclick= "populateData(event)" />  
   <input type= "button" value= "List All Data" id="alldata" class="btn btn-default btn-block"  onclick= "populateData(event)" />
   <a href="logout.php"><input type="button" value="Log Out" id="logout" class="btn btn-default btn-block"  /></a>
    
  </div>
    <div class="col-md-9">
      <div id="data" class= "" align="center" >
	 </div> 
   </div>
  </div>
  
  </div>
			

  </body>
</html>

<script>
var username=$("#username").val();
   if(!username){
     window.location.href='http://people.aero.und.edu/~mamin/Assignment/index.html';
   }


/*$("#logout").click(function(){

      alert("remove cookie");
      var cookies = document.cookie.split(";");

      for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=; expires=Thu, 18 Dec 2013 12:00:00 UTC; path=/";
    }
    window.location.href='../index.html'

});*/

var targetDiv = document.getElementById('data');

    targetDiv.innerHTML = '<object type="text/html" data="list_data.html" ></object>';

    var htmlContent = ' ';

    function populateData(event){
      switch(event.target.value){
	    
	    
        case 'Search And Purchased Games':{
         htmlContent = '<object type="text/html" data="search_game.php" ></object>';
          break;
        }
	 case 'List All Data':{
         htmlContent = '<object type="text/html" data="http://people.aero.und.edu/~mamin/Assignment/admin/customer_details.html?id='+ username+'"></object>';
          break;
        }

		
        case 'Delete Developers':{
          htmlContent = '<object type="text/html" data="remove_developer.html" ></object>';
         break;
        }
      }
      targetDiv.innerHTML = htmlContent;
    }

</script>