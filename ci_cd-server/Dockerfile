# Use OpenJDK 21 base image
FROM openjdk:21-jdk-slim

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

# Set working directory
WORKDIR /home/app

# Copy Maven files and build application
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Copy built JAR to new image and set entry point
FROM openjdk:21-jdk-slim
WORKDIR /home/app
COPY --from=0 /home/app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
