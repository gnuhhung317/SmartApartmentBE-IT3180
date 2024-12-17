FROM eclipse-temurin:21

LABEL mentainer="duchungdz"

WORKDIR /apt

COPY target/*.jar /apt/apt.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "apt.jar"]