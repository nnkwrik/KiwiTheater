FROM java:8-alpine

MAINTAINER nnkwrik 16t2133h@shinshu-u.ac.jp

ADD target/*-jar-with-dependencies.jar app.jar

EXPOSE 2333

#ENTRYPOINT ["java","-jar","/app.jar"]

ENTRYPOINT ["java","-cp","/app.jar","im.ImWebsocketServer"]

