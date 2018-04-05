<%--
  Created by IntelliJ IDEA.
  User: marti
  Date: 5-4-2018
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="jquery-3.2.1.min.js"></script>
  </head>
  <body>
<script>
    $.ajax({
        type: "POST",
        url: "rest/hello/create",
        data: '{"param1":"Michael", "param2":"Jordan"}',
        contentType: "application/json",
        succes: alert("product is gewijzigd")
    });
</script>
  </body>
</html>
