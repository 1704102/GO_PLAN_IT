<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Go-Plan-It</title>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <link type="text/css" rel="stylesheet" href="css/overview.css">
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/appointment.js"></script>
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
    <div id="overview"></div>
    <div id="addTask"></div>
    <div id="addAppointment">
        <div id="appointmentInput">
            <div>
                <div>Name</div>
                <div><input id="name" type="text"></div>
            </div>
            <div>
                <div>timeB</div>
                <div><input id="timeB" type="time"></div>
            </div>
            <div>
                <div>timeE</div>
                <div><input id="timeE" type="time"></div>
            </div>
            <input id="checkbox" type="checkbox" onchange="toggleRepeating()" value="repeating" checked>
            <div id="notRepeating">
                date<input id="date" type="date">
            </div>
            <div id="repeating">
                <div>
                    <div class="day" id="Monday" foo="false" onclick="toggleDay(this)"><div>M</div></div>
                    <div class="day" id="Tuesday" foo="false" onclick="toggleDay(this)"><div>T</div></div>
                    <div class="day" id="Wednesday" foo="false" onclick="toggleDay(this)"><div>W</div></div>
                    <div class="day" id="Thursday" foo="false" onclick="toggleDay(this)"><div>T</div></div>
                    <div class="day" id="Friday" foo="false" onclick="toggleDay(this)"><div>F</div></div>
                    <div class="day" id="Saturday" foo="false" onclick="toggleDay(this)"><div>S</div></div>
                    <div class="day" id="Sunday" foo="false" onclick="toggleDay(this)"><div>S</div></div>
                </div>
            </div>
            <input type="button" value="send" onclick="addAppointment()">


        </div>
    </div>
</div>
<script>
</script>
</body>
</html>

