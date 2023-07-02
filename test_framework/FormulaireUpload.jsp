<%@page import="modele.Emp" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire</title>
</head>
<body>
    <h1>Formulaire Upload</h1>
        <form action="http://localhost:8081/test/Upload.do" method="post" enctype="multipart/form-data">
            Badge: <input type="file" name="badge" />
            <br>
            <input type="submit" value="Valider">
        </form>
</body>
</html>