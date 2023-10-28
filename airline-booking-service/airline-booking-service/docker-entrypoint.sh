#!/bin/sh

echo "Waiting for MySQL to start..."
./wait-for-it.sh mysql:3306 -- echo "MySQL is up and running"

echo "Starting the server..."
java org.springframework.boot.loader.JarLauncher