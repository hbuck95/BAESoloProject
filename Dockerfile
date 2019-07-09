FROM maven:latest as base
COPY . /build
WORKDIR /build
RUN mvn -Dmaven.test.skip=true clean package
FROM jboss/wildfly:latest
COPY --from=base /build/target/BAESoloProject.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080

