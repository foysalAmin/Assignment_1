#!/usr/bin/perl
use CGI;

print "Content-type: text/html\n\n";


$query   = new CGI;
$username  = $query->param('username');

 # Remove leading and trailing spacing.
 $username =~ s/^\s*(\S*)\s*$/$1/;

 $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom DeveloperRegister ";
 $cmd   .= "'$username' " ;  
 
 system($cmd);

