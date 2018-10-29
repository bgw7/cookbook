FROM maven:alpine as build-stage
WORKDIR /app
COPY ./ /app/
RUN mvn package

FROM openjdk:8-jdk-alpine
COPY --from=build-stage /app/target/cookbook-1.jar target/

VOLUME /tmp

ARG JAR_FILE=target/cookbook-1.jar

ADD ${JAR_FILE} cookbook.jar

CMD java -jar cookbook.jar -Dspring.profiles.active=local

EXPOSE 9999

# docker run -d -p 9999:9999