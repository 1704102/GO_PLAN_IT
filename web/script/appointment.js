function toggleDay(data) {
    console.log(data);
    if($(data).attr("foo") == "false") {
        $(data).attr("foo", "true");
    }else{
        $(data).attr("foo", "false");
    }
}

function toggleRepeating() {
    if($("#checkbox").is(":checked")){
        $("#repeating").css("display", "block");
        $("#notRepeating").css("display", "none");
        $("#date").val("");
    }else {
        $("#repeating").css("display", "none");
        $("#notRepeating").css("display", "block");
        resetRepeating()
    }
}

function addAppointment() {
    var data = JSON.parse("{}");
    data["name"] = $("#name").val();
    data["token"] = sessionStorage.getItem("token");
    data["timeB"] = $("#timeB").val();
    data["timeE"] = $("#timeE").val();
    data["date"] = $("#date").val();
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