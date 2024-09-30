FROM amazoncorretto:17-alpine
EXPOSE 7000
ADD build/libs/plataformae-*.jar plataformae.jar
ENTRYPOINT ["java", "-jar", "plataformae.jar"]