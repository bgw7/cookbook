FROM openjdk:8-jdk-alpine

VOLUME /tmp

ARG JAR_FILE=target/cookbook-1.jar

ADD ${JAR_FILE} cookbook.jar

CMD java -jar cookbook.jar -Dspring.profiles.active=local

EXPOSE 9999


# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=8888" , "-jar","/cookbook.jar"]



# docker run -d -e "SPRING_PROFILES_ACTIVE=local" -p 9999:9999 -t spring