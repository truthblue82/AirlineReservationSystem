#!/usr/bin/env bash

cd ./eureka-service/eureka-service
./build.sh
wait
cd ../../

cd ./gateway
./build.sh
wait
cd ../

cd ./authourization-server
./build.sh
wait
cd ../

cd ./airline-booking-service/airline-booking-service
./build.sh
wait
cd ../../

docker compose -f airline-docker-compose.yml up -d