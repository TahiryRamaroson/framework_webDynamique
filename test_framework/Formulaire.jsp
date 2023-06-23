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
    <h1>Formulaire</h1>
        <form action="http://localhost:8081/test/Traitement" method="get">
            Nom: <input type="text" name="nom">
            <br>
            Age: <input type="number" name="age">
            <br>
            Date de naissance: <input type="date" name="naissance">
            <br>
            <input type="submit" value="Valider">
        </form>
</body>
</html>