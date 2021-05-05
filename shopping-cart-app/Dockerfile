FROM gradle:jdk11 as builder

COPY /app/build/libs/app.jar /app/build/libs/app.jar
WORKDIR /
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/build/libs/app.jar"]