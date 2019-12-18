FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/automation

# Adding .jar under target from host into this image
ADD target/selenium-docker.jar       selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs 				     libs

# in case of another dependency like .csv / .json / .xls
# Adding suite files
ADD book-flight-module.xml           book-flight-module.xml
ADD search-module.xml                search-module.xml

# Adding health check script to make sure hub is in ready state
# ADD healthcheck.sh                   healthcheck.sh
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

# ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST org.testng.TestNG $MODULE
ENTRYPOINT sh healthcheck.sh


