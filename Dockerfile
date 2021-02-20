# Build
FROM openjdk:8-jdk-alpine as build
COPY /build.gradle settings.gradle gradlew ./
COPY /gradle gradle
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

# Run
FROM openjdk:8-jdk-alpine
ENV ARTIFACT_PATH=build/libs/*.jar
COPY --from=build $ARTIFACT_PATH app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-Duser.timezone=Asia/Seoul","-Dreactor.netty.http.server.accessLogEnabled=true","-jar","app.jar"]
