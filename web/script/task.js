function openTask(id) {
    $("#addTask").css("display", "block")
    var data = JSON.parse("{}");
    var date = new Date();
    data["id"] = id;
    data["timeOffset"] = date.getTimezoneOffset().toString();
    $.ajax({
        type: 'PUT',
        url: 'rest/task',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
            var date = data[0].deadline;
            console.log(data[0]);
            $("#task-id").empty();
            $("#task-id").append(id);
            $("#task-name").val(data[0].name);
            $("#task-date").val(date);
            $("#task-time").val(data[0].time);
            $("#subTasks").find(".subTask").remove();
            if(data[0].tasks.length == 0){
                addSubTask();
            }
            for(var subtask in data[0].tasks){
                addSubTaskWithData(data[0].tasks[subtask]);
            }
        }
    });
}

function getTasks() {
    $.ajax({
        type: 'PUT',
        url: 'rest/tasks',
        dataType: 'text',
        contentType: 'application/json',
        data: '{"token":"'+ sessionStorage.getItem("token") +'"}',
        success: function(data){
            fillTaskTable(JSON.parse(data));
        }
    });
}

function saveTask() {
    var data = JSON.parse("{}");
    data["id"] = $("#task-id").text();
    data["name"] = $("#task-name").val();
    data["token"] = sessionStorage.getItem("token");
    data["description"] = "empty";
    data["date"] = $("#task-date").val();
    data["time"] = $("#task-time").val();

    var subtasks = JSON.parse("[]");

    $(".subTask").each(function () {
        var subTask = JSON.parse("{}");
        subTask["name"] = $(this).find(".name").val();
        subTask["hours"] = $(this).find(".hours").val();
        if ($(this).find(".done").is(':checked')){
            subTask["done1"] = true;
        }else{
            subTask["done1"] = false;
        }

        subtasks.push(subTask);
    });

    data["subTasks"] = subtasks;

    console.log(data);

    $.ajax({
        type: 'PUT',
        url: 'rest/task/data',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function(data){
        }
    });
}

function deleteCheckTask() {
    var data = JSON.parse("{}");
    data["token"] =  sessionStorage.getItem("token");
    data["id"] = $("#task-id").text();
    $.ajax({
        type: 'DELETE',
        url: 'rest/task/check ',
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(data){
        }
    });
}
function deleteTask() {
    var data = JSON.parse("{}");
    data["token"] =  sessionStorage.getItem("token");
    data["id"] = $("#task-id").text();
    $.ajax({
        type: 'DELETE',
        url: 'rest/task ',
        dataType: 'text',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(data){
        }
    });
}