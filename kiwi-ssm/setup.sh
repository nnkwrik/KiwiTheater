#!/bin/bash


catalina.sh run &

sleep 10

cd /usr/local/tomcat/webapps

rm -rf ROOT

#直接从http://xxx.xxx.xxx.x:8080/ 访问应用
mv kiwi-ssm ROOT

echo 'current webapps/ROOT, you can access application by http://xxx.xxx.xxx.x:8080/'
ls -l ROOT

tail -f /dev/null

