#!/usr/bin/perl
use CGI;

print "Content-type: text/html\n\n";

$query   = new CGI;
$asin  = $query->param('asin');
$price  = $query->param('price');

 $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom  UpdateGamePrice ";
 $cmd   .= "'$asin' " ; 
 $cmd   .= "'$price' " ;

 system($cmd);