FROM tomcat:8-jre17

# COPY ./Docker/server.xml /usr/local/tomcat/conf/server.xml

# COPY --from=stage /usr/webapps/bancoapp/WebContent/ /usr/local/tomcat/webapps/bancoapp
# COPY --from=stage /usr/webapps/bancoapp/bancoapp.war /usr/local/tomcat/webapps/bancoapp.war

CMD cp /Docker/server.xml /usr/local/tomcat/conf/server.xml \
&& cp /usr/webapps/bancoapp/bancoapp.war /usr/local/tomcat/webapps/bancoapp.war \
&& cp -r /usr/webapps/bancoapp/WebContent/ /usr/local/tomcat/webapps/bancoapp