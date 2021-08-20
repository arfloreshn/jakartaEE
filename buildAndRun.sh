#!/bin/sh
if [ $(docker ps -a -f name=Formularios | grep -w Formularios | wc -l) -eq 1 ]; then
  docker rm -f Formularios
fi
mvn clean package && docker build -t com.arfloreshn/Formularios .
docker run -d -p 9080:9080 -p 9443:9443 --name Formularios com.arfloreshn/Formularios
