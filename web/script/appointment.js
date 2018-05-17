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
    $("#addTask").css("display", "none")
    getTasks();
}

function openAddTask() {
    var data = JSON.parse("{}");
    data["token"] = sessionStorage.getItem("token");
    $.ajax({
        type: 'PUT',
        url: 'rest/task',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
            $("#task-id").append(data);
            openTask(data);
        }
    });



}function openTask(id) {
    $("#addTask").css("display", "block")
    var data = JSON.parse("{}");
    data["id"] = id;
    $.ajax({
        type: 'POST',
        url: 'rest/task/one',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
            console.log(data[0]);
            $("#task-id").empty();
            $("#task-id").append(id);
            $("#task-name").val(data[0].name);
            $("#task-date").val(data[0].date);
            $("#task-time").val(data[0].time);
        }
    });
}