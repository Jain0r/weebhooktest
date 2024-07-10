# Usar una imagen base con JDK 17 y maven
FROM maven:3-openjdk-17 AS build

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo

COPY . /app

# Ejecutar maven para construir el proyecto -DskipTests
RUN mvn clean package  

# Crear nueva imagen basada en jdk17
FROM eclipse-temurin:17

# Exponer el puerto que utilizara la app
EXPOSE 8080

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/weebhook-demo-0.0.1-SNAPSHOT.jar /app/weebhook-demo-0.0.1-SNAPSHOT.jar

# Establecer el punto de entrada para ejecutar la app
ENTRYPOINT ["java","-jar","/app/weebhook-demo-0.0.1-SNAPSHOT.jar"]