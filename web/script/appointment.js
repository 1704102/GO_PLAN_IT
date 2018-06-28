function getAppointments() {
    var input = JSON.parse("{}");
    input["token"] = sessionStorage.getItem("token");
    fillAppointmentTable(postCall(input, 'rest/appointments', 'json'));
}

function addAppointment() {
    var data = JSON.parse("{}");
    data["name"] = $("#appointment-name").val();
    data["token"] = sessionStorage.getItem("token");
    data["timeB"] = $("#appointment-timeB").val();
    data["timeE"] = $("#appointment-timeE").val();
    data["date"] = $("#appointment-date").val();
    data["repeating"] = getRepeating();

    putCall(data, 'rest/appointment');
    sleep(1000);
    getAppointments();
}

function alterAppointment() {
    var data = JSON.parse("{}");
    data["name"] = $("#appointment-name").val();
    data["id"] = parseInt($("#appointment-id").val());
    data["timeB"] = $("#appointment-timeB").val();
    data["timeE"] = $("#appointment-timeE").val();
    data["date"] = $("#appointment-date").val();
    data["repeating"] = getRepeating();

    putCall(data, 'rest/appointment/alter');
    sleep(1000);
    getAppointments();
}

function deleteAppointment(appointment) {
    if (confirm("Are you sure?")) {
        input = JSON.parse("{}");
        input["id"] = appointment.id;
        deleteCall(input, 'rest/appointment')
        sleep(500);
        getAppointments();
    }
}