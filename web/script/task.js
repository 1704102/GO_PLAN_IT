function openTask(id) {
    $("#addTask").css("display", "block");
    var input = JSON.parse("{}");
    var date = new Date();
    input["id"] = id;
    input["timeOffset"] = date.getTimezoneOffset().toString();
    var data = putCall(input, "rest/task");
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

function getTasks() {
    var input = JSON.parse("{}");
    input["token"] = sessionStorage.getItem("token");
    fillTaskTable(postCall(input, 'rest/tasks', 'json'));
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
    putCall(data, "rest/task/data");


}

function deleteCheckTask() {
    var data = JSON.parse("{}");
    data["token"] =  sessionStorage.getItem("token");
    data["id"] = $("#task-id").text();
    deleteCall(data, "rest/task/check");
}
function deleteTask() {
    var data = JSON.parse("{}");
    data["token"] =  sessionStorage.getItem("token");
    data["id"] = $("#task-id").text();
    deleteCall(data, "rest/task");
}