FROM maven:alpine as build-stage
WORKDIR /app
COPY ./ /app/
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine
COPY --from=build-stage /app/target/cookbook-1.jar /target

VOLUME /tmp

CMD java -jar /target/cookbook-1.jar -Dspring.profiles.active=local

EXPOSE 9999

# docker run -d -p 9999:9999