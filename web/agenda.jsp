<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Go-Plan-It</title>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/menu.js"></script>
    <script src="script/Agenda.js"></script>
</head>
<body>
<div class="header">
    <div class="menu">
        <img class="logo" src="css/images/logo/planet.png" attr="false" onclick="toggleMenu()">
        <div class="menuItem"><a href="index.jsp"><li>Home</li></a></div>
        <div class="menuItem"><a href="tasks&appointments.jsp"><li>Taken</li></a></div>
        <div class="menuItem"><a href="agenda.jsp"><li>Agenda</li></a></div>
    </div>
</div>

<div class="content">
    <div class="Agenda">
        <div id="utilHeader">
            <input type="button" onclick="previousWeek()" value="previous">
            <div id="month-year"></div>
            <input type="button" onclick="nextWeek()" value="next">
        </div>
        <div class="timeTable">
            <div id="tableHeader">
            </div><script>fillTable()</script>
        </div>
        <div class="overlay">
            <div class="day" id="0"></div>
            <div class="day" id="1"></div>
            <div class="day" id="2"></div>
            <div class="day" id="3"></div>
            <div class="day" id="4"></div>
            <div class="day" id="5"></div>
            <div class="day" id="6"></div>
        </div>
    </div>
</div>
</body>
</html>

