FROM maven:3.8-openjdk-17 as maven
WORKDIR /app
COPY . /app
RUN mvn install

FROM openjdk:17.0.2-jdk
WORKDIR /app
COPY --from=maven /app/target/job4j_url_shortcut-1.0-SNAPSHOT.jar app.jar
CMD java -jar app.jar