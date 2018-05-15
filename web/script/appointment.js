function toggleDay(data) {
    console.log(data);
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
}

function openAddTask() {
    $("#addTask").css("display", "block")
}