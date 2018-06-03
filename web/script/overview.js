function fillTaskTable(data) {
    $("#taskTable").empty();
    $("#taskTable").append("  <div class=\"task\"><img src=\"css/images/button/add.png\" onclick=\"openAddTask()\" width=50px  height=50px></div>");
    for(var i in data){
        var task = data[i];
        var i = task.id;
        $("#taskTable").append(
            "<div class='task' onclick='openTask(" + i + ")'>" +
            "<div style='display: table'>" + task.name + "</div>" +
            "<div class='hidden'>" + task.id + "</div>" +
            "</div>");

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


function toggleDay(data) {
    if($(data).attr("foo") == "false") {
        $(data).attr("foo", "true");
    }else{
        $(data).attr("foo", "false");
    }
}

function toggleRepeating() {
    if($("#repeating").css("display") == "none"){
        $("#repeating").css("display", "block");
        $("#notRepeating").css("display", "none");
        $("#date").val("");
    }else {
        $("#repeating").css("display", "none");
        $("#notRepeating").css("display", "block");
        resetRepeating()
    }
}

function getRepeating() {
    var s = "";
    $(".day").each(function () {
        if($(this).attr("foo") == "true"){
            s += $(this).attr("id") + ",";
        }
    });
    s = s.substring(0, s.length - 1);
    return s;
}

function resetRepeating() {
    $(".day").each(function () {
        if($(this).attr("foo") == "true"){
            $(this).attr("foo", "false");
        }
    });
}

function closeAddAppointmnt() {
    $("#addAppointment").css("display", "none")
}

function openAddAppointment() {
    $("#addAppointment").css("display", "block")
}
function closeAddTask() {
    $("#addTask").css("display", "none");
    if ($("#task-name").val() == ""){
        deleteCheckTask();
    }
    sleep(200);
    getTasks();
}

function openAddTask() {
    var data = JSON.parse("{}");
    data["token"] = sessionStorage.getItem("token");
    $.ajax({
        type: 'POST',
        url: 'rest/task',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
            $("#task-id").append(data);
            openTask(data);
        }
    });
}

function addSubTask() {
    $("#subTasks").append(
        "<tr class='subTask'>" +
        "<td><input class='name' type='text'></td>" +
        "<td><input class='hours' type='text'></td>" +
        "<td><input class='done' type='checkbox'></td>" +
        "<td><img src=\"css/images/button/trash.jpg\" width='25px' height='25px'></td>" +
        "</tr>"
    );
}

function addSubTaskWithData(data) {
    console.log(data.done);
    var checked = "";
    if (data.done == true){
        console.log("checked");
        checked = "checked";
    }
    $("#subTasks").append(
        "<tr class='subTask'>" +
        "<td><input class='name' type='text' value='" + data.name + "'></td>" +
        "<td><input class='hours' type='text' value='" + data.hours + "'></td>" +
        "<td><input class='done' type='checkbox' " + checked + "></td>" +
        "<td><img src=\"css/images/button/trash.jpg\" width='25px' height='25px'></td>" +
        "</tr>"
    );
}