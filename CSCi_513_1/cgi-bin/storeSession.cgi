
    # Object initialization:
    use CGI::Session;
    $session = CGI::Session->new();

    $CGISESSID = $session->id();

    # Send proper HTTP header with cookies:
    print $session->header();

    # Storing data in the session:
    $session->param('f_name', 'Sherzod');