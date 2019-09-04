FROM tomcat:8.5-jdk8-adoptopenjdk-hotspot
MAINTAINER vadim_pogorelkin
COPY /target/spring-mvc.war /usr/local/tomcat/webapps/spring-mvc.war
COPY mysql-connector-java-8.0.17.jar $CATALINA_HOME/lib