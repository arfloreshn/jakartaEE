@echo off
call mvn clean package
call docker build -t com.arfloreshn/Formularios .
call docker rm -f Formularios
call docker run -d -p 9080:9080 -p 9443:9443 --name Formularios com.arfloreshn/Formularios