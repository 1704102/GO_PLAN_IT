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
    <div id="overview">
        <div id="overviewMenu">
            <div id="taskSelect" selectedDiv="true" onclick="selectTasks()">tasks</div><div id="appointmentSelect" selectdDiv="false" onclick="selectAppointment()">appointments</div>
        </div>
        <div id="overviewTable">
            <table id="taskTable"></table>
            <table id="appointmentTable"></table>
            <script>
                selectTasks()


                function fillTaskTable(data) {
                    $("#taskTable").empty();
                    $("#taskTable").append(
                        "<tr>" +
                        "<th>name</th>" +
                        "<th>description</th>" +
                        "<th>deadline</th>" +
                        "<th>edit</th>" +
                        "<th>remove</th>");
                    + "</tr>"
                    for(var i in data){
                        var task = data[i];
                        $("#taskTable").append(
                            "<tr>" +
                            "<td style='display: none'>" + task.id + "</td>" +
                            "<td>" + task.name + "</td>" +
                            "<td>" + task.description + "</td>" +
                            "<td>" + task.date + " " + task.time + "</td>" +
                            "<td><img src='css/images/button/edit.jpg' width='20' height='20'></td>" +
                            "<td><img src='css/images/button/remove.png' width='20' height='20'></td>" +
                            "</tr>");
                    }
                }

                function fillAppointmentTable(data) {
                    $("#appointmentTable").empty();
                    $("#appointmentTable").append(
                        "<tr>" +
                        "<th>name</th>" +
                        "<th>date</th>" +
                        "<th>timeB</th>" +
                        "<th>timeE</th>" +
                        "<th>edit</th>" +
                        "<th>remove</th>"
                    + "</tr>");
                    for(var i in data){
                        var appointment = data[i];
                        $("#appointmentTable").append(
                            "<tr>" +
                            "<td style='display: none'>" + appointment.id + "</td>" +
                            "<td>" + appointment.name + "</td>" +
                            "<td>" + appointment.date + "</td>" +
                            "<td>" + appointment.timeB + "</td>" +
                            "<td>" + appointment.timeE + "</td>" +
                            "<td><img src='css/images/button/edit.jpg' width='20' height='20'></td>" +
                            "<td><img src='css/images/button/remove.png' width='20' height='20'></td>" +
                            "</tr>");
                    }
                }

                function selectTasks() {
                    $("#taskTable").css("display", "table");
                    $("#taskSelect").attr("selectedDiv", "true");
                    $("#appointmentTable").css("display", "none")
                    $("#appointmentSelect").attr("selectedDiv", "false");

                    $.ajax({
                        type: 'POST',
                        url: 'rest/task',
                        dataType: 'text',
                        contentType: 'application/json',
                        data: '{"token":"'+ sessionStorage.getItem("token") +'"}',
                        success: function(data){
                            console.log(data);
                            fillTaskTable(JSON.parse(data));
                        }
                    });
                }

                function selectAppointment() {
                    $("#taskTable").css("display", "none");
                    $("#taskSelect").attr("selectedDiv", "false");
                    $("#appointmentTable").css("display", "table")
                    $("#appointmentSelect").attr("selectedDiv", "true");

                    $.ajax({
                        type: 'POST',
                        url: 'rest/appointment/all',
                        dataType: 'text',
                        contentType: 'application/json',
                        data: '{"token":"'+ sessionStorage.getItem("token") +'"}',
                        success: function(data){
                            console.log(data);
                            fillAppointmentTable(JSON.parse(data));
                        }
                    });
                }
            </script>
        </div>
    </div>
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

