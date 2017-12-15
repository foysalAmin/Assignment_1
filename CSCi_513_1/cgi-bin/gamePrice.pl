#!/usr/bin/perl
use CGI;

#print "Content-type: text/html\n\n";

$query = new CGI;
$action = $query->param( 'action' );


if($action eq "Submit")
{
	$cmd = "/usr/bin/java -Djava.security.egd=file:/dev/./urandom RemoveDev";
	
	foreach $key ($query->param())
	{  
		if($key ne "action")
		{
			if($query->param($key) eq "on")
			{
				$cmd .= " " . $key;
			}
		}
	}
	system($cmd);
}
my $referrer = $ENV{HTTP_REFERER};
print $query->redirect($referrer);