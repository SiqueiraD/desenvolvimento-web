FROM openjdk:17 as stage

WORKDIR /usr/webapps/bancoapp

COPY ./bancoapp/WebContent/WEB-INF/lib/*.jar /usr/lib/
# COPY ./bancoapp/WebContent/WEB-INF/web.xml /usr/local/tomcat/conf/
COPY ./bancoapp/ /usr/webapps/bancoapp/

RUN javac -cp /usr/lib/javax.servlet-api-4.0.0.jar:/usr/lib/jcl-over-slf4j-2.0.9.jar:/usr/webapps/bancoapp/WebContent/WEB-INF/lib/* \
src/main/webapp/servlets/*.java \
src/main/webapp/classes/*.java \
src/main/webapp/controllers/*.java 

RUN cp -r /usr/webapps/bancoapp/src /usr/webapps/bancoapp/WebContent/WEB-INF/classes/ -v
# cp -r /usr/local/tomcat/webapps/bancoapp/src /usr/local/tomcat/webapps/bancoapp/WEB-INF/classes/src/

RUN jar cvf bancoapp.war -C /usr/webapps/bancoapp/WebContent .
RUN jar tf bancoapp.war >> /usr/webapps/bancoapp/WebContent/notepad 

FROM tomcat:8-jre17

COPY ./Docker/server.xml /usr/local/tomcat/conf/server.xml

COPY --from=stage /usr/webapps/bancoapp/WebContent/ /usr/local/tomcat/webapps/bancoapp
COPY --from=stage /usr/webapps/bancoapp/bancoapp.war /usr/local/tomcat/webapps/bancoapp.war

CMD ["catalina.sh", "run"]