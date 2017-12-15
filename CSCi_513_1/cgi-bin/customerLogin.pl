#!/usr/bin/perl
use CGI;
# Object initialization:
#use CGI::Session;
#$session = CGI::Session->new();
#$session->param('username',  $query->param('username'));

print "Content-type: text/html\n\n";

$query   = new CGI;
$username  = $query->param('username');
$password  = $query->param('password');

 # Remove leading and trailing spacing.
 $username =~ s/^\s*(\S*)\s*$/$1/;

 $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom CustomerLogin ";
 $cmd   .= "'$username' " ; 
 $cmd   .= "'$password' " ;
 system($cmd);