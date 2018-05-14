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
_______________<input type="button" onclick="previousWeek()" value="previous">
<input type="button" onclick="nextWeek()" value="next">
<div class="content">
    <div class="Agenda">
        <table class="timeTable">
            <tr id="tableHeader">
            </tr>
            <script>fillTable()</script>
        </table>
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
<%--<script>addTimeElement(JSON.parse('{"id":"task1","type":"task","day":1,"timeB":"20:30","timeE":"23:15"}'))</script>--%>
<%--<script>addTimeElement('{"id":"task2","type":"appointment","day":1,"timeB":"10:00","timeE":"24:00"}')</script>--%>
<%--<script>addTimeElement('{"id":"task3","type":"task","day":2,"timeB":"12:15","timeE":"14:00"}')</script>--%>
<%--<script>addTimeElement('{"id":"task4","type":"task","day":2,"timeB":"14:30","timeE":"16:30"}')</script>--%>
</body>
</html>

