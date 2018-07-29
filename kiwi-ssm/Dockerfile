FROM tomcat:9.0-jre8-alpine

MAINTAINER nnkwrik 16t2133h@shinshu-u.ac.jp

COPY target/*.war /usr/local/tomcat/webapps

COPY setup.sh /usr/local/tomcat/webapps

CMD ["bash", "/usr/local/tomcat/webapps/setup.sh"]


