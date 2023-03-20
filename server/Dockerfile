FROM docker.oaplatform.org/oap-jvm:jdk-17-35-debian-buster-slim-36

WORKDIR /app

COPY ./credentials/service-account.json /app/service-account.json

ARG JAR_FILE=target/course-app-*.jar

COPY ${JAR_FILE} /app/limebrew/app.jar

ENTRYPOINT ["java", "-jar","/app/limebrew/app.jar"]

EXPOSE 8080