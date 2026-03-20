FROM eclipse-temurin:17-jdk-alpine 
WORKDIR /app 
COPY . . 
RUN chmod +x gradlew 
RUN ./gradlew bootJar --no-daemon 
EXPOSE 8080 
CMD ["java", "-jar", "build/libs/Proiect_FootballPosition-0.0.1-SNAPSHOT.jar"] 
