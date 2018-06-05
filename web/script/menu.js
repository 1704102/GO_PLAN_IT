function toggleMenu(){
    if( $(".menuItem").css("display") ==  "none"){
        $(".menu").css("border-radius", "5%");
        $(".menu").css("width", "120px");
        $(".menu").css("height", "150px");
        $(".menu").css("padding-bottom", "10px");
        $(".menuItem").css("display", "block");
    }else{
        $(".menu").css("border-radius", "50%");
        $(".menuItem").css("display", "none");
        $(".menu").css("width", "80px");
        $(".menu").css("height", "80px");
        $(".menu").css("padding-bottom", "0px");
    }
}


function postCall(input, rest, dataType) {
    var output = "";
    $.ajax({
        type: 'POST',
        url: rest,
        dataType: dataType,
        async: false,
        contentType: 'application/json',
        processData: true,
        data: JSON.stringify(input),
        success: function(data) {
            output = data;
        }
    });
    return output;
}

function putCall(input, rest) {
    var output = "";
    $.ajax({
        type: 'PUT',
        url: rest,
        data: JSON.stringify(input),
        contentType: 'application/json'
    });
    return output;
}

function deleteCall(input, rest) {
    var output = "";
    $.ajax({
        type: 'DELETE',
        url: rest,
        data: JSON.stringify(input),
        contentType: 'application/json'
    });
    return output;
}
