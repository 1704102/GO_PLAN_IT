function fillTable() {
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

function addTimeElement(element){
    var task = JSON.parse(element);
    $("#" + task.day).append("<div class='timeElement " + task.type +"' id='"+ task.id +"'>"+ task.typeX +"</div>");
    positionTask(task);
}

function positionTask(task){
    var splitTimeB = task.timeB.split(":");
    var splitTimeE = task.timeE.split(":");

    var minutesFromZeroB = (parseInt(splitTimeB[0]) * 60) + (parseInt(splitTimeB[1]));
    var minutesFromZeroE = (parseInt(splitTimeE[0]) * 60) + (parseInt(splitTimeE[1]))
    var top = minutesFromZeroB * (28.75/60);
    var height = (minutesFromZeroE - minutesFromZeroB) * (29.125/60);

    $("#" + task.id).css("height", height + "px");
    $("#" + task.id).css("top", top + "px");
}