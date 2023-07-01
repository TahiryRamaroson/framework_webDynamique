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
    <h1>Resultat des recherches</h1>
        <% 
        ArrayList<Emp> employe = (ArrayList<Emp>) request.getAttribute("lst");
        for(int i=0; i<employe.size(); i++){ %>
            <p><% out.println(employe.get(i).getNom()); %></p>
        <% } %>
</body>
</html>