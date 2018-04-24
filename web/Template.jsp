<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Go-Plan-It</title>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/menu.js"></script>
</head>
<body>
<div class="header">
    <div class="menu">
        <img class="logo" src="css/images/logo/planet.png" attr="false" onclick="toggleMenu()">
        <div class="menuItem"><a href="index.jsp"><li>Home</li></a></div>
        <div class="menuItem"><a href="index.jsp"><li>Home</li></a></div>
        <div class="menuItem"><a href="index.jsp"><li>Home</li></a></div>
    </div>
    <div class="content">
        Dit is text
    </div>
    <script>
        var epoch = (new Date).getTime();
        $.ajax({
            type: "POST",
            url: "/rest/hello/create",
            data: epoch.toString(),
            dataType: "text",
            contentType: 'application/json'
        });
    </script>


</div>
</body>
</html>
