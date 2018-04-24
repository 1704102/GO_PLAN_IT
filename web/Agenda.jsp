<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Go-Plan-It</title>
    <link type="text/css" rel="stylesheet" href="css/template.css">
    <script src="script/jquery-3.2.1.min.js"></script>
    <script src="script/menu.js"></script>
</head>
<body>
<div class="header">
    <div class="menu">
        <img class="logo" src="css/images/logo/planet.png" attr="false" onclick="toggleMenu()">
        <div class="menuItem"><a href="index.jsp"><li>Home</li></a></div>
        <div class="menuItem"><a href="index.jsp"><li>Home</li></a></div>
        <div class="menuItem"><a href="index.jsp"><li>Home</li></a></div>
    </div>
</div>
<div class="content">
    <div class="Agenda">
        <table class="timeTable">
            <tr>
                <th>Time</th>
                <th>Maandag</th>
                <th>Dinsdag</th>
                <th>Woensdag</th>
                <th>Donderdag</th>
                <th>Vrijdag</th>
                <th>Zaterdag</th>
                <th>Zondag</th>
            </tr>
            <script>
                for(var i = 0; i < 25; i++){
                    var time;
                    if(i < 10){
                        time = "0" + i;
                    }else{
                        time = i;
                    }
                    $(".timeTable").append("<tr>" + "<td colspan='8' class='time'><div class='timehour'>" +  time + ":00 </div></td>" + "</tr>");
                }
                $(".timeTable").append();
            </script>
        </table>
        <div class="overlay">
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
            <div class="day"></div>
        </div>
    </div>
</div>
</body>
</html>

