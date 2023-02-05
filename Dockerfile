FROM maven:3.8.6-openjdk-11-slim AS MAVEN_BUILD

COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package -DskipTests

FROM openjdk:11-jre

ARG JAR_FILE=target/opcgdb_api*.jar

COPY --from=MAVEN_BUILD /tmp/${JAR_FILE} /usr/local/bin/app.jar

WORKDIR /usr/local/bin/

ENV SERVER_PORT 8080

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java -jar app.jar"]
