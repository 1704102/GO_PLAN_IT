var MONTHSOFYEAR = ["Januari", "Februari", "March", "April", "May", "June", "Juli", "August", "September", "October", "November", "December"];
var DAYSOFWEEK= ["Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
var DATE = new Date();

function fillTable() {
    fillHours();
    fillHeader();
    console.log(Math.round(DATE.getTime() / 1000));
    $.ajax({
        type: 'POST',
        url: 'rest/agenda',
        dataType: 'json',
        contentType: 'application/json',
        processData: true,
        data: '{"date":' + DATE.getTime() + ',"timeOffset":"'+DATE.getTimezoneOffset().toString()+'"}',
        success: function(data){
            for (var day in data){
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
        $(".timeTable").append("<tr>" + "<td colspan='8' class='time'><div class='timehour'>" +  time + ":00 </div></td>" + "</tr>");
    }
    $(".timeTable").append("<tr>\" + \"<td colspan='8' class='time'></td>\" + \"</tr>" );
}

function fillHeader() {
    $("#tableHeader").empty()
    $("#tableHeader").append("<th>Time</th>");
    DATE.setDate(DATE.getDate() - DATE.getDay() + 1);
    console.log(DATE);
    for(var i = 0; i < 7; i++){
        $("#tableHeader").append("<th>" + DAYSOFWEEK[DATE.getDay()] + " " + (DATE.getDate()) + "</th>")
        DATE.setDate(DATE.getDate() + 1);
    }
    DATE.setDate(DATE.getDate() - 7);
}

function nextWeek() {
    DATE.setDate(DATE.getDate() + 7);
    fillHeader()
}

function previousWeek() {
    DATE.setDate(DATE.getDate() - 7);
    fillHeader()
}

function addTimeElement(task){
    console.log(task);
    $("#" + task.day).append("<div class='timeElement " + task.type +"' id='tE-"+ task.id +"'>"+ task.name +"</div>");
    positionTask(task);
}

function positionTask(task){
    console.log(task);
    var splitTimeB = task.timeB.split(":");
    var splitTimeE = task.timeE.split(":");

    var minutesFromZeroB = (parseInt(splitTimeB[0]) * 60) + (parseInt(splitTimeB[1]));
    var minutesFromZeroE = (parseInt(splitTimeE[0]) * 60) + (parseInt(splitTimeE[1]));
    var top = minutesFromZeroB * (28.850/60);
    var height = (minutesFromZeroE - minutesFromZeroB) * (29.250/60);

    console.log(height);
    console.log(top);

    $("#tE-" + task.id).css("height", height + "px");
    $("#tE-" + task.id).css("top", top + "px");
}