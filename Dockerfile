FROM openjdk:17 as stage

WORKDIR /usr/local/tomcat/webapps/bancoapp

COPY ./bancoapp/WEB-INF/lib/*.jar /usr/local/tomcat/lib/
COPY ./bancoapp/WEB-INF/web.xml /usr/local/tomcat/conf/
COPY ./bancoapp/ /usr/local/tomcat/webapps/bancoapp/

RUN javac -cp /usr/local/tomcat/lib/javax.servlet-api-4.0.0.jar:/usr/local/tomcat/lib/jcl-over-slf4j-2.0.9.jar:/usr/local/tomcat/webapps/bancoapp/WEB-INF/lib/* \
/usr/local/tomcat/webapps/bancoapp/src/main/webapp/servlets/*.java \
/usr/local/tomcat/webapps/bancoapp/src/main/webapp/classes/*.java 
# RUN cp /usr/local/tomcat/webapps/bancoapp/src/main/webapp/*/*.class /usr/local/tomcat/webapps/bancoapp/WEB-INF/classes/

RUN cp -r `ls -A | grep -v "WEB-INF"` /usr/local/tomcat/webapps/bancoapp/WEB-INF/classes/ -v
# cp -r /usr/local/tomcat/webapps/bancoapp/src /usr/local/tomcat/webapps/bancoapp/WEB-INF/classes/src/

RUN jar cvf bancoapp.war -C /usr/local/tomcat/webapps/bancoapp .
RUN jar tf bancoapp.war >> notepad 

FROM tomcat:8-jre17
COPY Docker/server.xml /usr/local/tomcat/conf/server.xml
COPY --from=stage /usr/local/tomcat/webapps/bancoapp/bancoapp.war /usr/local/tomcat/webapps/bancoapp.war

CMD ["catalina.sh", "run"]