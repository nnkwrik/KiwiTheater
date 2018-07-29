use mysql;

select host, user from user;


grant all on *.* to 'root'@'%' identified by '1234' with grant option;


flush privileges;


