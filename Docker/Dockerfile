FROM openjdk:17 as stage

WORKDIR /usr/bancoapp

RUN mkdir /usr/bancoapp

COPY ../bancoapp/WebContent/WEB-INF/lib/*.jar /usr/lib/
# COPY ./bancoapp/WebContent/WEB-INF/web.xml /usr/local/tomcat/conf/
COPY ../bancoapp /usr/bancoapp/

RUN javac -cp /usr/lib/javax.servlet-api-4.0.0.jar:/usr/lib/jcl-over-slf4j-2.0.9.jar:/usr/bancoapp/WebContent/WEB-INF/lib/* \
src/main/webapp/servlets/*.java \
src/main/webapp/classes/*.java \
src/main/webapp/controllers/*.java 

RUN cp -r /usr/bancoapp/src /usr/bancoapp/WebContent/WEB-INF/classes/ -v
# cp -r /usr/local/tomcat/webapps/bancoapp/src /usr/local/tomcat/webapps/bancoapp/WEB-INF/classes/src/

RUN jar cvf bancoapp.war -C /usr/bancoapp/WebContent .
RUN jar tf bancoapp.war >> /usr/bancoapp/WebContent/notepad 

CMD cp /usr/bancoapp /usr/destination