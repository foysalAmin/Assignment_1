#!/usr/bin/perl
use CGI;

print "Content-type: text/html\n\n";

$query   = new CGI;
$fullname  = $query->param('fullname');
$username  = $query->param('username');
$password  = $query->param('password');

 # Remove leading and trailing spacing.
 $username =~ s/^\s*(\S*)\s*$/$1/;

 $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom CustomerSignUp ";
 $cmd   .= "'$fullname' " ; 
 $cmd   .= "'$username' " ; 
 $cmd   .= "'$password' " ;
 system($cmd);

