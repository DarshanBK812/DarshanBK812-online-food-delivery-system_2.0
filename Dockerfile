FROM openjdk:17-jdk

COPY target/food-app.jar .

EXPOSE 8080

ENTRYPOINT ["java" , "-jar" , "food-app.jar"]