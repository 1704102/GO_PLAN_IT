function getAppointments() {
    $.ajax({
        type: 'PUT',
        url: 'rest/appointments',
        dataType: 'text',
        contentType: 'application/json',
        data: '{"token":"'+ sessionStorage.getItem("token") +'"}',
        success: function(data){
            fillAppointmentTable(JSON.parse(data));
        }
    });
}

function addAppointment() {
    var data = JSON.parse("{}");
    data["name"] = $("#appointment-name").val();
    data["token"] = sessionStorage.getItem("token");
    data["timeB"] = $("#appointment-timeB").val();
    data["timeE"] = $("#appointment-timeE").val();
    data["date"] = $("#appointment-date").val();
    data["repeating"] = getRepeating();
    $.ajax({
        type: 'POST',
        url: 'rest/appointment',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
        }
    });
    sleep(1000);
    getAppointments();
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