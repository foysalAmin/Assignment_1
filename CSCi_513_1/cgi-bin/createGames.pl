#!/usr/bin/perl
use CGI;

print "Content-type: text/html\n\n";

$query   = new CGI;
$asin  = $query->param('asin');
$game_title  = $query->param('game_title');
$price  = $query->param('price');
$developer  = $query->param('developer');
$developername =$query->param('developername');

 # Remove leading and trailing spacing.
 $asin =~ s/^\s*(\S*)\s*$/$1/;

 $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom CreateGames ";
 $cmd   .= "'$asin' " ; 
 $cmd   .= "'$game_title' " ;
 $cmd   .= "'$price' " ;
 $cmd   .= "'$developer' " ;
 $cmd   .= "'$developername' " ;  
 
 system($cmd);