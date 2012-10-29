<%@tag pageEncoding="UTF-8"%>
<%@attribute name="stylesheet" fragment="true" %>
<%@attribute name="javascript" fragment="true" %>
<%@attribute name="pageTitle" fragment="true" %>
<%@attribute name="pageContent" fragment="true" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title><jsp:invoke fragment="pageTitle" /></title>

    <!-- Client Side Library -->
    <script type="text/javascript" src="${_root}/_res/jquery/jquery-1.8.1.min.js"></script>

    <script type="text/javascript" src="${_root}/_res/twitboots/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${_root}/_res/twitboots/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${_root}/_res/twitboots/css/bootstrap-responsive.min.css" />

    <!-- Application -->
    <script type="text/javascript" src="${_root}/_res/apps/global.js"></script>
    <link rel="stylesheet" type="text/css" href="${_root}/_res/apps/global.css" />

    <jsp:invoke fragment="stylesheet" />
    <jsp:invoke fragment="javascript" />
</head>

<body>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
	                <span class="icon-bar"></span>
                </a>
                <a class="brand" href="${_root}">Dynamic Finder Example</a>
                <div class="nav-collapse collapse">
                <ul class="nav">
	                <li class="dropdown">
	                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-target="#">
	                        Employee Data <b class="caret"></b>
	                    </a>
	                    <ul class="dropdown-menu">
	                        <li><a id="mainMenu1" href="${_root}/employee/simple-list">Simple Search</a></li>
	                        <li><a id="mainMenu2" href="${_root}/employee/gmail-like-list">Gmail Like Search</a></li>
	                        <li><a id="mainMenu3" title="To be added" href="#">Dynamic Logic</a></li>
	                        <li><a id="mainMenu4" title="To be added" href="#">User Defined Search</a></li>
	                    </ul>
	                </li>
                </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="page-content">
    <jsp:invoke fragment="pageContent" />
    </div>
</body>

</html>