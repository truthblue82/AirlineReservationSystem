#!/usr/bin/env bash

PREFIX=truthblue82
NAME=flightmanagement-gateway
IMAGE_NAME=$PREFIX/$NAME

./gradlew bootJar -x test
#docker build -t $IMAGE_NAME .
#docker run -p 8443:8443 -e SERVER_PORT=8443 -e EUREKA_PORT=8761 $IMAGE_NAME