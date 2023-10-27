FROM openjdk:17 as stage

WORKDIR /usr/webapps/bancoapp

CMD javac -cp /usr/webapps/bancoapp/WebContent/WEB-INF/lib/javax.servlet-api-4.0.0.jar:/usr/webapps/bancoapp/WebContent/WEB-INF/lib/jcl-over-slf4j-2.0.9.jar:/usr/webapps/bancoapp/WebContent/WEB-INF/lib/* \
src/main/webapp/servlets/*.java \
src/main/webapp/classes/*.java \
src/main/webapp/controllers/*.java \
&& jar cvf bancoapp.war -C /usr/webapps/bancoapp/WebContent . \
&& mv /usr/webapps/bancoapp/src/main/webapp/servlets/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/servlets \
&& mv /usr/webapps/bancoapp/src/main/webapp/classes/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/classes \
&& mv /usr/webapps/bancoapp/src/main/webapp/controllers/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/controllers \
&& cp -r /usr/webapps/bancoapp/* /usr/destination
