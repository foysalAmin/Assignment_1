#!/usr/bin/perl
use CGI;

print "Content-type: text/html\n\n";


$query   = new CGI;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Reset ";
system($cmd);

