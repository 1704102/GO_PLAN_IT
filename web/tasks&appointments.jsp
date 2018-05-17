<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Go-Plan-It</title>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <link type="text/css" rel="stylesheet" href="css/overview.css">
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/appointment.js"></script>
    <script src="script/menu.js"></script>
    <script src="script/overview.js"></script>
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

            <div id="taskTableContainer">
                <div id="taskTable">
                </div>
            </div>

            <div id="appointmentTableContainer" style="width: 100%;">
                <div><img src="css/images/button/edit.jpg" onclick="openAddAppointment()" width="30px" height="30px"></div>
                <table id="appointmentTable"></table>
            </div>
            <script>
                selectTasks()
            </script>
        </div>
    </div>

</div>
<div id="addAppointment" class="overlay">
    <div id="appointmentInput" class="inputContainer">
        <div style="border-bottom: 1px solid black; text-align: center; font-size: 30px;">Appointment   <img src="css/images/button/remove.png" width="30px" height="30px;" onclick="closeAddAppointmnt()"></div>
        <div class="input">
            <div>Name</div>
            <div><input type="text" id="appointment-name"></div>
        </div>
        <div class="input">
            <div>timeB</div>
            <div><input id="appointment-timeB" type="time"></div>
        </div>
        <div class="input">
            <div>timeE</div>
            <div><input id="appointment-timeE" type="time"></div>
        </div>
        <div id="checkbox" onclick="toggleRepeating()">check</div>
        <div id="notRepeating">
            date<input id="appointment-date" type="date">
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
        <div class="button"><input id="sendAppointment" type="button" value="send" onclick="addAppointment()"></div>
    </div>
</div>
<div id="addTask" class="overlay">
    <div id="addTaskInput" class="inputContainer">
        <div style="border-bottom: 1px solid black; text-align: center; font-size: 30px;">Task   <img src="css/images/button/remove.png" width="30px" height="30px;" onclick="closeAddTask()"></div>
        <div id="task-id" style="display: none"></div>
        <div>
            <div>name</div>
            <input type="text" id="task-name">
        </div>
        <div>
            <div>deadline</div>
        </div>
        <div>
            <div>Date</div>
            <input type="date" id="task-date">
            <div>Time</div>
            <input type="time" id="task-time">
        </div>
        <div><input type="button" value="add" onclick="addTask()"></div>
    </div>
</div>
<script>
</script>
</body>
</html>

