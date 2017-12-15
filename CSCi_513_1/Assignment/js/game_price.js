$(document).ready(function(){
  
  $("form#priceUpdateForm").submit(function() { // Form is submitted

    var username = $('#username').attr('value'); // get username

    var asin ='';
    var price ='';
    $("input[name='price']").each(function() {
      asin=asin +$(this).attr('id')+',';
      price=price + $(this).val()+',';
    });

    asin=asin.substring(0,asin.length-1)
    price=price.substring(0,price.length-1)
    console.log(asin);


   $('div#loginResult').text("herere ");
    if ( asin && price ) { // values are not empty
      $.ajax({
        type: "GET",
        url: "http://people.aero.und.edu/~mamin/cgi-bin/updateGamePrice.cgi", // URL of the Perl script
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        // send username and password as parameters to the Perl script
        data: "asin=" + asin + "&price=" + price,
        // script call was *not* successful
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
          $('div#gameResult').text("responseText: " + XMLHttpRequest.responseText 
            + ", textStatus: " + textStatus 
            + ", errorThrown: " + errorThrown);
          $('div#gameResult').addClass("error");
        }, // error 
        // script call was successful 
        // data contains the JSON values returned by the Perl script
        success: function(data){
          if (data.error) { // script returned error
            $('div#gameResult').text("error: " + data.error);
            $('div#gameResult').addClass("error");
          } // if
          else { // login was successful
            $('div#gameResult').text("success: " + data.success );
            $('div#gameResult').addClass("success");
          } //else
        } // success
      }); // ajax
    } // if
    $('div#gameResult').fadeIn();
    return false;
  });



});
