# Framework web dynamique ETU1849
 

## Pré-requis

Tout d'abord, vous devez avoir les librairies suivantes

- servlet-api.jar
- etu1849Framework.jar


## Installation

- Premièrement, les librairies requises dont on a parlé au dessus doit être présentes dans le dossier lib de votre application web ( ps: il est aussi conseillé de les mettre dans le classpath )

- Le web.xml de votre application web doit être configuré ainsi

```
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">

    <servlet>
        <servlet-name>FrontServlet</servlet-name>
        <servlet-class>etu1849.framework.servlet.FrontServlet</servlet-class>
        <init-param>
            <param-name>session</param-name>
            <param-value>user</param-value>
        </init-param>
    </servlet>

    <servlet-mapping>
        <servlet-name>FrontServlet</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>

</web-app>
```
- Du côté du modèle, assurez vous bien que vos classes trouvent les classes existantes dans les librairies ( etu1849Framework.jar )


## Utilisation et fonctionnalité

- Vous pouvez décidez si votre modèle sera un singleton ou non. Il suffit d'importer l'annotation Scope et d'annoter votre modèle comme l'exemple ci-dessous
```
import etu1849.framework.annotation.Scope;
```
```
@Scope("singleton")
public class Example {}
```

- Pour que vos fonctions aient la possibilité d'être appelé depuis l'url, il faut importer l'annotation Urls et annoter vos fonctions comme ceci (*** la valeur de l'url que vous metterez doit se terminer par .do)
```
import etu1849.framework.annotation.Urls;
```
```
@Urls( url = "/Calcul.do")
public void Addition(){
    System.out.println(1+1);
}
```

- Afin de générer les views que vous voulez, il suffit juste d'utiliser la classe ModelView. Vous n'avez qu'à instancer un nouveau ModelView et il faut juste préciser quelle page est associé à ce ModelView (*** Votre fonction devra retourner le ModelView )
```
import etu1849.framework.ModelView;
```
```
@Urls( url = "/Formulaire.do")
public ModelView getForm(int idForm){
    ModelView view = new ModelView("Formulaire.jsp");
    return view;
}
```
- Les attributs de vos modèles doivent porter le même nom que les champs dans les formulaires et les liens. C'est la même chose pour les arguments des fonctions. Par exemple
```
<input type="text" name="nom">
```
```
public class Emp {
    String nom;
}
```
```
@Urls( url = "/Traitement.do")
    public void getNom(String nom){
}
```

- Pour passer des données vers votre page, il y a la fonction addItem dans la classe ModelView. Il faut juste spécifier le nom avec lequel vous allez récupérer vos données (*** vous pouvez passer n'importe quel objet)
```
ModelView view = new ModelView("Traitement.jsp");
Emp temp = new Emp();
view.addItem("Employe", temp);
```

- Si vous voulez aussi envoyez des fichiers, il y a la classe Upload pour recevoir les fichiers envoyé
```
import etu1849.framework.Upload;
```

- Vous avez aussi la possibilité de limiter l'accès à des fonctions et des pages en utilisant des authentifications. Pour cela il faut importer l'annotation Auth et annoter la fonction
```
import etu1849.framework.annotation.Auth;
```
```
@Auth( profil = "Admin")
@Urls( url = "/Restricted.do")
public ModelView restricted (){
    ModelView view = new ModelView("Restricted.jsp");    
    return view;
}
```

- Pour ajouter des sessions aux ModelView, il y a la fonction addSession(key, value)

## Auteur
RAMAROSON Tahiry Henitsoa ETU1849

### Je tiens à remercier toutes les personnes qui m'ont aidé dans la réalisation de ce projet et tout particulièrement notre professeur et encadreur Mr Naina de nous avoir guidé afin de parfaire ce framework 