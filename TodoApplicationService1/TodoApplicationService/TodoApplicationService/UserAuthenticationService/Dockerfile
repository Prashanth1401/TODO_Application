FROM openjdk
WORKDIR usr/lib
EXPOSE 8085
ADD ./target/UserAuthenticationService-0.0.1-SNAPSHOT.jar /usr/lib/UserAuthenticationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserAuthenticationService-0.0.1-SNAPSHOT.jar"]