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
            "<td><img class='delete' src='css/images/button/remove.png' width='20' height='20'></td>" +
            "</tr>");
    }
}

function getAppointments() {
    $.ajax({
        type: 'POST',
        url: 'rest/appointment/all',
        dataType: 'text',
        contentType: 'application/json',
        data: '{"token":"'+ sessionStorage.getItem("token") +'"}',
        success: function(data){
            fillAppointmentTable(JSON.parse(data));
        }
    });
}

function getTasks() {
    $.ajax({
        type: 'POST',
        url: 'rest/task',
        dataType: 'text',
        contentType: 'application/json',
        data: '{"token":"'+ sessionStorage.getItem("token") +'"}',
        success: function(data){
            fillTaskTable(JSON.parse(data));
        }
    });
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
            "<td><img class='delete' src='css/images/button/remove.png' onclick='deleteAppointment(" + JSON.stringify(appointment) + ")' width='20' height='20'></td>" +
            "</tr>");
    }
}

function selectTasks() {
    $("#taskTableContainer").css("display", "block");
    $("#taskSelect").attr("selectedDiv", "true");
    $("#appointmentTableContainer").css("display", "none")
    $("#appointmentSelect").attr("selectedDiv", "false");

   getTasks();
}
function selectAppointment() {
    $("#taskTableContainer").css("display", "none");
    $("#taskSelect").attr("selectedDiv", "false");
    $("#appointmentTableContainer").css("display", "table")
    $("#appointmentSelect").attr("selectedDiv", "true");

    getAppointments();
}

function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds){
            break;
        }
    }
}
function deleteTask(data) {
    console.log(data);
}


function deleteAppointment(appointment) {
    if (confirm("Are you sure?")) {
        $.ajax({
            type: 'DELETE',
            url: 'rest/appointment',
            dataType: 'text',
            contentType: 'application/json',
            data: '{"id":"'+ appointment.id +'"}'
        });
        sleep(500);
        getAppointments();
    }
}


function addAppointment() {
    var data = JSON.parse("{}");
    data["name"] = $("#appointment-name").val();
    data["token"] = sessionStorage.getItem("token");
    data["timeB"] = $("#appointment-timeB").val();
    data["timeE"] = $("#appointment-timeE").val();
    data["date"] = $("#appointment-date").val();
    data["repeating"] = getRepeating();
    console.log(data);
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'PUT',
        url: 'rest/appointment',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
        }
    });
    sleep(1000);
    getAppointments();
}

function addTask() {
    var data = JSON.parse("{}");
    data["name"] = $("#task-name").val();
    data["token"] = sessionStorage.getItem("token");
    data["date"] = $("#task-date").val();
    data["time"] = $("#task-time").val();
    console.log(data);
    console.log(JSON.stringify(data));
    $.ajax({
        type: 'PUT',
        url: 'rest/task',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
        }
    });
    getTasks();
}