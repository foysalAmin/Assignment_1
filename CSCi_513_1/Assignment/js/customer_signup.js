$(document).ready(function(){
  $("form#loginForm").submit(function() { // loginForm is submitted
     var fullname = $('#fullname').attr('value'); // get username
    var username = $('#username').attr('value'); // get username
    var password = $('#password').attr('value'); // get password
 
    $('div#loginResult').text("herere ");
    if ( username && password) { // values are not empty
      $.ajax({
        type: "GET",
        url: "http://people.aero.und.edu/~mamin/cgi-bin/customerSignUp.cgi", // URL of the Perl script
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        // send username and password as parameters to the Perl script
        data: "username=" + username + "&password=" + password+"&fullname=" + fullname,
        // script call was *not* successful
        error: function(XMLHttpRequest, textStatus, errorThrown) { 
          $('div#loginResult').text("responseText: " + XMLHttpRequest.responseText 
            + ", textStatus: " + textStatus 
            + ", errorThrown: " + errorThrown);
          $('div#loginResult').addClass("error");
        }, // error 
        // script call was successful 
        // data contains the JSON values returned by the Perl script
        success: function(data){
          if (data.error) { // script returned error
            $('div#loginResult').text("error: " + data.error);
            $('div#loginResult').addClass("error");
          } // if
          else { // login was successful
            $('div#loginResult').text("success: " + data.success );
            $('div#loginResult').addClass("success");
          } //else
        } // success
      }); // ajax
    } // if
    else {
      $('div#loginResult').text("enter fullname , username and password");
      $('div#loginResult').addClass("error");
    } // else
    $('div#loginResult').fadeIn();
    return false;
  });
});
