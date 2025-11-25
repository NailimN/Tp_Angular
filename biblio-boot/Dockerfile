FROM maven:3.9.11-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY ./src ./src

RUN mvn package

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/target/*.war biblio-boot.war

CMD [ "java", "-jar", "bilbio-boot.war" ]

EXPOSE 8080
