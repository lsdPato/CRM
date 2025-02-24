
FROM openjdk:21-jdk-slim
# Establece el directorio de trabajo dentro del contenedor
WORKDIR /root
# Copia el archivo JAR del proyecto al contenedor
COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root/.mvn
#Dependencias
RUN ./mvnw dependency:go-offline

#Copiar codigo fuente
COPY ./src /root/src

#Contruir nuestr aplicacion
RUN ./mvnw clean install

# Expone el puerto en el que corre la aplicación (ajustar según config)
EXPOSE 8082
# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/root/target/.jar"]