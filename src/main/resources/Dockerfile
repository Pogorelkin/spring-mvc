FROM mysql:latest as builder
# That file does the DB initialization but also runs mysql daemon, by removing the last line it will only init
# RUN ["sed", "-i", "s/exec \"$@\"/echo \"not running $@\"/", "/usr/local/bin/docker-entrypoint.sh"]
MAINTAINER Pog
# needed for intialization
ENV MYSQL_ROOT_PASSWORD=password \
MYSQL_PASSWORD=password \
MYSQL_DATABASE=springtaskDB

ADD db-script.sql /docker-entrypoint-initdb.d/
COPY db-script.sql /var/lib/mysql
EXPOSE 3306