FROM openjdk:17 as stage

WORKDIR /usr/webapps/bancoapp

COPY ./bancoapp/WebContent/WEB-INF/lib/*.jar /usr/lib/
COPY ./bancoapp/ /usr/webapps/bancoapp/

RUN javac -cp /usr/lib/javax.servlet-api-4.0.0.jar:/usr/lib/jcl-over-slf4j-2.0.9.jar:/usr/lib/mysql-connector-java-8.0.11.jar:/usr/webapps/bancoapp/WebContent/WEB-INF/lib/* \
src/main/webapp/servlets/*.java \
src/main/webapp/service/*.java \
src/main/webapp/domain/gerenciamento/*.java \
src/main/webapp/domain/pagamento/*.java \
src/main/webapp/domain/seguranca/*.java

RUN cp -r /usr/webapps/bancoapp/src /usr/webapps/bancoapp/WebContent/WEB-INF/classes/ -v

RUN jar cvf bancoapp.war -C /usr/webapps/bancoapp/WebContent .
RUN jar tf bancoapp.war >> /usr/webapps/bancoapp/WebContent/notepad 

FROM tomcat:8-jre17

COPY ./Docker/server.xml /usr/local/tomcat/conf/server.xml

COPY --from=stage /usr/webapps/bancoapp/WebContent/ /usr/local/tomcat/webapps/bancoapp
COPY --from=stage /usr/webapps/bancoapp/bancoapp.war /usr/local/tomcat/webapps/bancoapp.war

ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"

EXPOSE 8080 8000

CMD ["catalina.sh", "run"]

# FROM openjdk:17
# WORKDIR /usr/webapps/bancoapp
# COPY ./bancoapp/ /usr/webapps/bancoapp/
# CMD java -jar WebContent/WEB-INF/lib/webapp-runner-9.0.80.0.jar bancoapp.war