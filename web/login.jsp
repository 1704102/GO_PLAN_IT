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
<div class="content">
    <div id="loginMenu">
        <div id="loginInput">
            <div><div>Username</div><input id="username" type="text"></div>
            <div><div>Password</div><input id="password" type="password"></div>
            <div><input type="button" value="login" onclick="login()"></div>
            <div id="error"><div>username or password incorrect</div></div>
        </div>
    </div>

    <script>
        function login() {
            $.ajax({
                type: 'POST',
                url: 'rest/login',
                dataType: 'text',
                contentType: 'application/json',
                data: '{"username":"' + $("#username").val() + '","password":"'+$("#password").val()+'"}',
                success: function(data){
                    console.log(data);
                    if(data == "error"){
                        $("#username").css("border", "1px solid red");
                        $("#password").css("border", "1px solid red");
                        $("#error").css("display", "block");
                    }else{
                        sessionStorage.setItem("token", data);
                        window.location.href = '/agenda.jsp';
                    }
                }
            });
        }
    </script>
</div>
</body>
</html>

