cd test_framework/code/
javac -d ../WEB-INF/classes/ *.java
cd ../
jar -cvf "C:/Program Files/Apache Software Foundation/Tomcat 8.5/webapps/test.war" ./*