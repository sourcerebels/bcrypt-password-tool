FROM openjdk:11
ADD ["build/libs/bcrypt-password-tool-0.0.1-SNAPSHOT.jar", "/opt/bcrypt-password-tool/"]
ENTRYPOINT exec java $JAVA_OPTS -jar /opt/bcrypt-password-tool/bcrypt-password-tool-0.0.1-SNAPSHOT.jar
EXPOSE 7778
