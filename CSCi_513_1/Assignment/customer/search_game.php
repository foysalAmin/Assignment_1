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
  <script type="text/javascript" src="../js/game_price.js"></script>
  <script type="text/javascript"
  src="//code.jquery.com/jquery-1.4.4.min.js"></script>
  <style type="text/css">
  #loginContent { width: 350px; margin: 100px auto; }
  button[type] { margin: 0.5em 0; }
  </style>
</head>
<body>

  <div class="container">
    <?php
     //echo $_POST["username"];
    session_start();
    $username= $_SESSION['username'] ;
    ?>
    <div id="gameResult" style="display:normal;">
    </div>
    <div id="gameResult"></div>

    <form id ="priceUpdateForm">
      <input name="game_name" id="game_name" class="form-control" type="text" placeholder="Enter Developers Name"></input>
      <button type="button" id="search_button" class="btn btn-primary"> Search
      </button>
    </form>
    <form id="purchaseFrom">
      <input type="hidden" id="username" name="username" value="<?php echo $username; ?>" />
      <table style="width:100%">
       <thead id="head">
       </thead>
       <tbody id="gamedata">

       </tbody>
     </table>
     <button type="button" id="purchase" class="btn btn-default" value="Pruchase">Pruchase</button>
     <form>
     </div>
   </body>
   </html>


   <script>
   var username=$("#username").val();
   if(!username){
     window.location.href='http://people.aero.und.edu/~mamin/Assignment/index.html';
   }

   $("#purchaseFrom").hide();

   $("#search_button").click(function(){

    var name=$("#game_name").val();

    var xmlhttp = new XMLHttpRequest();
    var url = "http://people.aero.und.edu/~mamin/cgi-bin/searchGame.cgi?name="+$.trim(name);
    xmlhttp.onreadystatechange = function()
    {
      if ( this.readyState == 4 && this.status == 200)
      {
        getAllGames( this.responseText );      
      }  
    }
    xmlhttp.open ( "GET", url, true);
    xmlhttp.send();

  })


   function getAllGames( response )
   {
    var arr = JSON.parse( response )
    var i;
    var out = "";
    if( arr.length == 0)
    {
      out += "<p>No Found</p>";
    }
    else
    {
      $("#purchaseFrom").show();
      var header="<tr><th>SELECT</th><th>TITLE</th><th>PRICE</th><th>NO OF ITEM</th></tr>";
      for(i = 0; i < arr.length; i++)
      {
        out +="<tr><td><input type='checkbox' name='asin' class='asin' value='"+arr[i].ASIN+"'></td><td>"+arr[i].TITLE+"</td><td> <input name='price' id='price"+arr[i].ASIN+"' value='"+arr[i].PRICE+ "'class='form-control' readonly/></td><td><input type='number' id='"+arr[i].ASIN+"' value=0 name='amount' class='form-control' id='amount'/></td></tr>" 
        console.log(arr[i].TITLE);
      }
    }

    document.getElementById("head").innerHTML = header;
    document.getElementById("gamedata").innerHTML = out;
  }
  $("#purchase").click(function() { 

    var asinarr = [];
    var quantintyarr =[];
    var costarr=[];
    var temp;
    var temp2;
    $(".asin:checked").each(function() {
      asinarr.push(this.value);
      temp='#'+this.value;
      quantintyarr.push($(temp).val());
      temp2='#price'+this.value;
      costarr.push($(temp2).val()*$(temp).val());

    });
    var asin=asinarr.join(",");
    var quantity=quantintyarr.join(",");
    var cost=costarr.join(",");
    console.log(asin+quantity+cost + $("#username").val());


     $.ajax({
        type: "POST",
        url: "http://people.aero.und.edu/~mamin/cgi-bin/purchaseGame.cgi", // URL of the Perl script

        // send firstname and name as parameters to the Perl script
        data: "asin=" + asin + "&quantity=" + quantity + "&cost=" + cost +"&username="+ $("#username").val(),
        // script call was *not* successful

        // script call was *not* successful
        error: function() { 
           // alert("script call was not successful");
        }, 

        // script call was successful 
        // perl_data should contain the string returned by the Perl script 
        success: function(data){
           $('div#gameResult').text("Purchase Game " + data);
           window.location.href='http://people.aero.und.edu/~mamin/Assignment/admin/customer_details.html?id='+username
            //alert("Your name is: " +data )
        }
    });   

/*    $.ajax({
        type:"GET",
        url: "http://people.aero.und.edu/~mamin/cgi-bin/purchaseGame.cgi", // URL of the Perl script
        contentType: "application/json; charset=utf-8",
        // send username and password as parameters to the Perl script
        data: "asin=" + asin + "&quantity=" + quantity + "&cost=" + cost +"&username="+ $("#username").val(),
        // script call was *not* successful
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
          $('div#gameResult').text("responseText: " + XMLHttpRequest.responseText 
            + ", textStatus: " + textStatus 
            + ", errorThrown: " + errorThrown);
          $('div#gameResult').addClass("error");
          
          console.log(textStatus + errorThrown  )
         // alert(textStatus);
        }, // error 
        // script call was successful 
        // data contains the JSON values returned by the Perl script
        success: function(data){
          alert(data);
          console.log(data);
          if (data.error) { // script returned error
            $('div#gameResult').text("error: " + data.error);
            $('div#gameResult').addClass("error");
          } // if
          else { // login was successful
            console.log(data.success )

            $('div#gameResult').text("success: " + data.success );
            $('div#gameResult').addClass("success");
          } //else
        } // success
      });*/

          /*      var xmlhttp = new XMLHttpRequest();


                var url = "http://people.aero.und.edu/~mamin/cgi-bin/purchaseGame.cgi?asin="+asin+"&quantity="+quantity+"&cost="+cost;
                 xmlhttp.onreadystatechange = function()
                 {
                   if ( this.readyState == 4 && this.status == 200)
                 {
                      cosole.log( "ki hoilo" );      
                 }  
                }
                 xmlhttp.open ( "GET", url, true);
                xmlhttp.send();
                */
//                console.log(asin + quantity + cost);


});
  </script>
