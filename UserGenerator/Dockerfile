FROM gradle:7.4-jdk22-alpine AS build
WORKDIR /app
COPY build.gradle.kts .
COPY ../settings.gradle.kts .
COPY src ./src
RUN gradle clean build -x test

FROM build AS publish
RUN mkdir -p /export
RUN cp build/libs/*.jar /export/app.jar

FROM amazoncorretto:22-alpine-jre AS final
VOLUME /tmp

RUN addgroup -S spring && adduser -S spring -G spring
USER spring

WORKDIR /app
COPY --from=publish /export/app.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]