<%@page import="modele.Emp" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
</head>
<body>
    <h1>Resultat Upload</h1>
            <p><% out.println(request.getAttribute("upload")); %></p>
</body>
</html>