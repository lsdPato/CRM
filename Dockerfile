FROM eclipse-temurin:21.0.2_13-jre-alpine
COPY ./target/CRM-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-Xms256m","-Xmx1024m","-jar","/app.jar"]