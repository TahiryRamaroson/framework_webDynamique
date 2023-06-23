cd test_framework/code/
javac -d ../WEB-INF/classes/ *.java
cd ../../
mkdir temp
xcopy "test_framework" "./temp/" /E
R