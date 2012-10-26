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

    <!-- Application -->
    <script type="text/javascript" src="${_root}/_res/apps/global.js"></script>
    <link rel="stylesheet" type="text/css" href="${_root}/_res/apps/global.css" />

    <!-- Twitter Bootstrap -->
    <script type="text/javascript" src="${_root}/_res/twitboots/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${_root}/_res/twitboots/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${_root}/_res/twitboots/css/bootstrap-responsive.min.css" />
    <jsp:invoke fragment="stylesheet" />
    <jsp:invoke fragment="javascript" />
</head>

<body>
    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand" href="${_root}/home">Finder4J Servlet Example</a>
            <ul class="nav">
                <li><a href="${_root}/employee/list">Employee</a></li>
                <li><a href="#">Link</a></li>
            </ul>
        </div>
    </div>

    <div class="page-content">
    <jsp:invoke fragment="pageContent" />
    </div>
</body>

</html>