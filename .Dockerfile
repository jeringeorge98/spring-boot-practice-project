FROM openjdk:22-jdk

##metadata

ARG BUILD_DATE
ARG VCS_REF
LABEL org.label-schema.build-date=$BUILD_DATE \
          org.label-schema.name="Practice Project" \
          org.label-schema.description="Practice Springboot for production" \
          org.label-schema.url="" \
          org.label-schema.vcs-ref=$VCS_REF \
          org.label-schema.vcs-url="" \
          org.label-schema.version="latest" \
          org.label-schema.schema-version="latest"


EXPOSE 8080
# The application's jar file
ARG JAR_FILE=target/practice-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} practice-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/practice-0.0.1-SNAPSHOT.jar"]
