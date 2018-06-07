<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Go-Plan-It</title>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <link type="text/css" rel="stylesheet" href="css/login.css">
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/menu.js"></script>
    <script src="script/Agenda.js"></script>
</head>
<body>
<div class="header">
</div>
<script>
    if (sessionStorage.getItem("token") != undefined){

        var input = JSON.parse("{}");
        input["token"] = sessionStorage.getItem("token");

        var output = postCall(input, 'rest/login/check', 'text');
        if(output == "true"){
            window.location.href = '/agenda.jsp';
        }else{
            sessionStorage.removeItem("token");
        }

    }
</script>
<div class="content">
    <div id="loginMenu">
        <div style="margin: auto; width: 160px;"><img src="css/images/logo/planet.png" width="150px" height="150px"></div>
        <div id="loginInput">
            <div><div>Username</div><input id="username" type="text"></div>
            <div><div>Password</div><input id="password" type="password"></div>
            <div><input type="button" value="login" onclick="login()"></div>
            <div id="error"><div>username or password incorrect</div></div>
        </div>
    </div>

    <script>
        function login() {
            var input = JSON.parse("{}");
            input['username'] = $("#username").val();
            input['password'] = $("#password").val();

            var data = postCall(input, 'rest/login', 'text').toString();
            if(data == "error"){
                $("#username").css("border", "1px solid red");
                $("#password").css("border", "1px solid red");
                $("#error").css("display", "block");
            }else{
                sessionStorage.setItem("token", data);
                window.location.href = '/agenda.jsp';
            }
        }
    </script>
</div>
</body>
</html>

