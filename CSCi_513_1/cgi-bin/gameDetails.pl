use CGI;

print "Content-type: text/html\n\n";

$query   = new CGI;
$asin  = $query->param('asin');

# Remove leading and trailing spacing.
$asin =~ s/^\s*(\S*)\s*$/$1/;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom GameDetails ";
$cmd   .= "'$asin' " ; 
system($cmd);