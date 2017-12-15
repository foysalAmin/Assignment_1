#!/usr/bin/perl
use CGI;

print "Content-type: text/html\n\n";
$query   = new CGI;
$asin  = $query->param('asin');
$cost  = $query->param('cost');
$quantity  = $query->param('quantity');
$username  = $query->param('username');

 # Remove leading and trailing spacing.
 $asin =~ s/^\s*(\S*)\s*$/$1/;

 $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom PurchaseGame ";
 $cmd   .= "'$asin' " ; 
 $cmd   .= "'$cost' " ;
 $cmd   .= "'$quantity' " ;  
 $cmd   .= "'$username' " ; 
 
 system($cmd);