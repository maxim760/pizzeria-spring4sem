FROM adoptopenjdk:11-jre-hotspot

WORKDIR /server

COPY target/pizzeria-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "pizzeria-0.0.1-SNAPSHOT.jar"]