FROM openjdk as compiler
WORKDIR /bancoapp
COPY ./bancoapp/ /bancoapp/
RUN javac -cp bancoapp/WEB-INF/lib/javax.servlet-api-4.0.0.jar bancoapp/WEB-INF/lib/jcl-over-slf4j-2.0.9.jar bancoapp/src/main/webapp/*.java bancoapp/src/main/webapp/*/*.java
RUN cp bancoapp/src/main/webapp/*/*.class bancoapp/WEB-INF/classes/
RUN jar cvf bancoapp.war -C bancoapp .

FROM tomcat:9.0.1-jre8-alpine

COPY --from=compiler ./bancoapp/bancoapp.war /usr/local/tomcat/webapps/bancoapp.war

CMD ["catalina.sh", "run"]