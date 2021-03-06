FROM maven:3.6.1-jdk-12

WORKDIR /app

ADD ./pom.xml /app
RUN mvn dependency:resolve

ADD ./src/ /app/src
RUN mvn install

FROM openjdk:12.0.2-jdk-oracle

WORKDIR /app

COPY --from=0 /app/target/ /app

EXPOSE 8080
EXPOSE 27017

CMD ["java","-jar","otus-spring-library-1.0.jar"] 
#CMD ["ls"] 