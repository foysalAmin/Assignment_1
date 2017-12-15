use CGI;

print "Content-type: text/html\n\n";

$query   = new CGI;
$username  = $query->param('username');

# Remove leading and trailing spacing.


$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom CustomerDetails ";
$cmd   .= "'$username' " ; 
system($cmd);