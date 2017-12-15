$(document).ready(function(){
  $("form#gameForm").submit(function() { // loginForm is submitted
   
    var asin = $('#asin').attr('value'); // get username
    var game_title = $('#game_title').attr('value'); // get username
    var price = $('#price').attr('value'); // get password
    var developer= getCheckedBoxes("developers");
   
    var developername= getDeveloperName("developers");
  
    console.log(developer);

    $('div#loginResult').text("herere ");
    if ( asin && game_title && price ) { // values are not empty
      $.ajax({
        type: "GET",
        url: "http://people.aero.und.edu/~mamin/cgi-bin/createGames.cgi", // URL of the Perl script
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        // send username and password as parameters to the Perl script
        data: "asin=" + asin + "&game_title=" + game_title + "&price=" + price +"&developer=" + developer+"&developername="+developername,
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
          console.log(data);
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
    else {
      $('div#gameResult').text("enter fullname , username and password");
      $('div#gameResult').addClass("error");
    } // else
    $('div#gameResult').fadeIn();
    return false;
  });



});
function getCheckedBoxes(chkboxName) {
  var checkboxes = document.getElementsByName(chkboxName);
  var checkboxesChecked='';
  // loop over them all
  for (var i=0; i<checkboxes.length; i++) {
     // And stick the checked ones onto an array...
     if (checkboxes[i].checked) {
        checkboxesChecked=checkboxesChecked + checkboxes[i].value +',';
      }
  }
  // Return the array if it is non-empty, or null
  return  checkboxesChecked.substring(0, checkboxesChecked.length-1) ;
}
function getDeveloperName(chkboxName) {
  var checkboxes = document.getElementsByName(chkboxName);
  var checkboxesChecked='';
  // loop over them all
  for (var i=0; i<checkboxes.length; i++) {
     // And stick the checked ones onto an array...
     if (checkboxes[i].checked) {
        checkboxesChecked=checkboxesChecked + checkboxes[i].id +',';
      }
  }
  // Return the array if it is non-empty, or null
  return  checkboxesChecked.substring(0, checkboxesChecked.length-1) ;
}