FROM openjdk:20-ea-17-slim as build

LABEL maintainer="Zakara Shahen <secret@gmail.com>"

WORKDIR app
ARG JAVA_PATH=target/*.jar
COPY ${JAVA_PATH} app.jar

RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:20-ea-17-slim

VOLUME /tmp
WORKDIR app

COPY --from=build app/dependencies/          ./
COPY --from=build app/spring-boot-loader/    ./
COPY --from=build app/snapshot-dependencies/ ./
COPY --from=build app/application/           ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]