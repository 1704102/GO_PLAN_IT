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
        <div class="menuItem"><a href="index.jsp"><li>Taken</li></a></div>
        <div class="menuItem"><a href="index.jsp"><li>Help</li></a></div>
    </div>
</div>
<div class="content">
    <div class="Agenda">
        <table class="timeTable">
            <tr>
                <th>Time</th>
                <th>Maandag</th>
                <th>Dinsdag</th>
                <th>Woensdag</th>
                <th>Donderdag</th>
                <th>Vrijdag</th>
                <th>Zaterdag</th>
                <th>Zondag</th>
            </tr>
            <script>fillTable()</script>
        </table>
        <div class="overlay">
            <div class="day" id="1"></div>
            <div class="day" id="2"></div>
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
        </div>
    </div>
</div>
<script>addTimeElement('{"id":"task1","type":"task","day":1,"timeB":"02:30","timeE":"08:00"}')</script>
<script>addTimeElement('{"id":"task2","type":"appointment","day":2,"timeB":"06:00","timeE":"23:00"}')</script>
</body>
</html>

