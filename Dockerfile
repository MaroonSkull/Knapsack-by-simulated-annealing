FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app/*.jar"]

