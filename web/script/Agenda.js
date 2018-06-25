var MONTHSOFYEAR = ["Januari", "Februari", "March", "April", "May", "June", "Juli", "August", "September", "October", "November", "December"];
var DAYSOFWEEK= ["Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
var DATE = new Date();
function sleep(milliseconds) {
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}
function setCurrentTime() {
    var now = new Date();
    if(now.getDate() == DATE.getDate()){
        var day111 = now.getDay() - 1;
        $("#" + day111).css("background-color", '#ff06');
        if($("#timeDiv").length == 0 ){
            $("#" + day111).append("<div id='timeDiv'></div>");
        }
        $("#timeDiv").css("top", ((now.getHours() * 60) + now.getMinutes()) * (30  /60) - 5.0 + "px");
        setTimeout(arguments.callee, 30000);
    }else{
        var day111 = now.getDay() - 1;
        $("#" + day111).css("background-color", 'rgba(181, 181, 181, 0.18)');
    }

}

    function fillTable() {
    setCurrentTime();
    fillHours();
    fillHeader();
    fillDays();


}

function fillDays() {

    $.ajax({
        type: 'POST',
        url: 'rest/agenda',
        dataType: 'json',
        contentType: 'application/json',
        processData: true,
        data: '{"date":' + DATE.getTime() + ',"timeOffset":"'+DATE.getTimezoneOffset().toString()+'", "token": "' + sessionStorage.getItem("token") + '"}',
        success: function(data){
            console.log(data);
            for (var day in data){
                console.log(data[day]);
                addTimeElement(data[day])
            }
        }
    });
}
function fillHours() {
    for(var i = 1; i < 24; i++){
        var time;
        if(i < 10){
            time = "0" + i;
        }else{
            time = i;
        }
        $(".timeTable").append("<div>" + "<div class='time'><div class='timehour'>" +  time + ":00 </div></div>" + "</div>");
    }
    $(".timeTable").append("<div>" + "<div class='time'><div class='timehour'> </div></div>" + "</div>");
}

function fillHeader() {
    $("#tableHeader").empty()
    $("#tableHeader").append("<div>Time</div>");
    DATE.setDate(DATE.getDate() - DATE.getDay() + 1);
    for(var i = 0; i < 7; i++){
        $("#tableHeader").append("<div>" + DAYSOFWEEK[DATE.getDay()] + " " + (DATE.getDate()) + "</div>")
        DATE.setDate(DATE.getDate() + 1);
    }
    DATE.setDate(DATE.getDate() - 7);

    $("#month-year").empty();
    $("#month-year").append(MONTHSOFYEAR[DATE.getMonth()] + " " + DATE.getFullYear());
}

function nextWeek() {
    DATE.setDate(DATE.getDate() + 7);
    fillHeader();
    emptyDays();
    fillDays();
}

function previousWeek() {
    DATE.setDate(DATE.getDate() - 7);
    fillHeader();
    emptyDays();
    fillDays();
}

function emptyDays() {
    $("#0").empty();
    $("#1").empty();
    $("#2").empty();
    $("#3").empty();
    $("#4").empty();
    $("#5").empty();
    $("#6").empty();
    setCurrentTime();
}

function addTimeElement(task){
    var preId = "";
    if (task.type == "task"){
        preId = "tE-";
    }else {
        preId = "aE-"
    }
    $("#" + task.day).append("<div class='timeElement "+ task.type + " " + preId + task.id + "'>"+ task.name +"</div>");
    positionTask(task, preId);
}

function positionTask(task, preId){
    var splitTimeB = task.timeB.split(":");
    var splitTimeE = task.timeE.split(":");

    var minutesFromZeroB = (parseInt(splitTimeB[0]) * 60) + (parseInt(splitTimeB[1]));
    var minutesFromZeroE = (parseInt(splitTimeE[0]) * 60) + (parseInt(splitTimeE[1]));

    var top = minutesFromZeroB * (30  /60) - 5.5;
    var height = (minutesFromZeroE - minutesFromZeroB) * (30/60);

    $("."+ preId + task.id).each(function () {
        $(this).css("height", height + "px");
        $(this).css("top", top + "px");
    })
}