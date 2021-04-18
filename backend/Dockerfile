FROM openjdk:11-jre
COPY target/*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "porthub-0.0.1-SNAPSHOT.jar" ]