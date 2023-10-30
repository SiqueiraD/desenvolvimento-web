FROM openjdk:17 as stage

WORKDIR /usr/webapps/bancoapp

CMD javac -cp /usr/webapps/bancoapp/WebContent/WEB-INF/lib/javax.servlet-api-4.0.0.jar:/usr/webapps/bancoapp/WebContent/WEB-INF/lib/jcl-over-slf4j-2.0.9.jar:/usr/webapps/bancoapp/WebContent/WEB-INF/lib/mysql-connector-java-8.0.11.jar:/usr/webapps/bancoapp/WebContent/WEB-INF/lib/* \
src/main/webapp/servlets/*.java \
src/main/webapp/service/*.java \
src/main/webapp/domain/gerenciamento/*.java \
src/main/webapp/domain/pagamento/*.java \
src/main/webapp/domain/seguranca/*.java \
&& jar cvf bancoapp.war -C /usr/webapps/bancoapp/WebContent . \
&& mv /usr/webapps/bancoapp/src/main/webapp/servlets/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/servlets \
&& mv /usr/webapps/bancoapp/src/main/webapp/service/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/service \
&& mv /usr/webapps/bancoapp/src/main/webapp/domain/gerenciamento/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/domain/gerenciamento \
&& mv /usr/webapps/bancoapp/src/main/webapp/domain/pagamento/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/domain/pagamento \
&& mv /usr/webapps/bancoapp/src/main/webapp/domain/seguranca/*.class -t /usr/webapps/bancoapp/WebContent/WEB-INF/classes/src/main/webapp/domain/seguranca \
&& cp -r /usr/webapps/bancoapp/* /usr/destination
