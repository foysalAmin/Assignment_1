$(document).ready(function(){
  $("form#developerRegister").submit(function() { // Form is submitted
    var username = $('#username').attr('value'); // get username
 
    if ( username ) { // values are not empty
      $.ajax({
        type: "GET",
        url: "http://people.aero.und.edu/~mamin/cgi-bin/developerRegister.cgi", // URL of the Perl script
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        // send username and password as parameters to the Perl script
        data: "username=" + username,
        // script call was *not* successful
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
          $('div#developerRegisterResult').text("responseText: " + XMLHttpRequest.responseText 
            + ", textStatus: " + textStatus 
            + ", errorThrown: " + errorThrown);
          $('div#developerRegisterResult').addClass("error");
        }, // error 
        // script call was successful 
        // data contains the JSON values returned by the Perl script
        success: function(data){
          if (data.error) { // script returned error
            $('div#developerRegisterResult').text("error: " + data.error);
            $('div#developerRegisterResult').addClass("error");
          } // if
          else { // login was successful
             $('div#developerRegisterResult').text("success: " + data.success );
             $('div#developerRegisterResult').addClass("success");
			 $('div#developerRegisterResult').fadeOut();
          } //else
        } // success
      }); // ajax
    } // if
    else {
      $('div#developerRegisterResult').text("enter developer name");
      $('div#developerRegisterResult').addClass("error");
    } // else
    $('div#developerRegisterResult').fadeIn();
    return false;
  });
});
