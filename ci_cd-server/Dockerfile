# Build Stage
FROM openjdk:21-jdk-slim AS build

# Install Maven
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz && \
    tar xzvf apache-maven-3.8.4-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.4 /opt/maven && \
    ln -s /opt/maven/bin/mvn /usr/bin/mvn

# Set environment variables
ENV MAVEN_HOME=/opt/maven
ENV PATH=$MAVEN_HOME/bin:$PATH

WORKDIR /home/app

# Copy Maven files and build application
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM openjdk:21-jdk-slim
WORKDIR /home/app
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
