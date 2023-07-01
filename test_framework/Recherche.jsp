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
    <h1>Recherche par nom</h1>
        <form action="http://localhost:8081/test/Search_name.do" method="get">
            Nom: <input type="text" name="nom">
            <input type="submit" value="Valider">
        </form>

    <h1>Recherche par age</h1>
        <form action="http://localhost:8081/test/Search_age.do" method="get">
            age: <input type="number" name="age">
            <input type="submit" value="Valider">
        </form>
</body>
</html>