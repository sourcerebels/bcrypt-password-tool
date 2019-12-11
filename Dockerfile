FROM adoptopenjdk/openjdk11:alpine
ADD ["build/libs/password-tool-0.0.1-SNAPSHOT.jar", "/opt/password-tool/"]
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/password-tool/password-tool-0.0.1-SNAPSHOT.jar
EXPOSE 7778
