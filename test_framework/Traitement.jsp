<%@page import="modele.Emp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <% 
        Emp one = (Emp)request.getAttribute("Empl"); %>
         <p><% out.println(one.getNom()); %></p>
         <p><% out.println(one.getAge()); %></p>
         <p><% out.println(one.getNaissance()); %></p>      
</body>
</html>