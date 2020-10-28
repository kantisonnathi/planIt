DROP DATABASE IF EXISTS tracktrigger;

create DATABASE tracktrigger;

grant all on tracktrigger.* to tracktriggerservice@'%' identified by 'passw0rd';
#--GRANT ALL PRIVILEGES ON *.* TO profileservice'@'localhost' IDENTIFIED BY 'passw0rd' WITH GRANT OPTION;

flush privileges;