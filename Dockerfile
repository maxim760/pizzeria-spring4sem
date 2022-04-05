FROM openjdk:11

WORKDIR /app

COPY ./target/pizzeria-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "pizzeria-0.0.1-SNAPSHOT.jar"]
