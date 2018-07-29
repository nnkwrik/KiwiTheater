FROM mysql:5.7

MAINTAINER nnkwrik 16t2133h@shinshu-u.ac.jp


ENV MYSQL_ALLOW_EMPTY_PASSWORD yes
ENV MYSQL_ROOT_PASSWORD 1234
ENV MYSQL_ROOT_HOST '%'

COPY mysql.cnf /etc/mysql/conf.d/mysql.cnf

#将所需文件放到容器中
COPY setup.sh /mysql/setup.sh
COPY schema.sql /mysql/schema.sql
COPY privileges.sql /mysql/privileges.sql

#设置容器启动时执行的命令
CMD ["bash", "/mysql/setup.sh"]
