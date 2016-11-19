#!/bin/bash

if [ "$1" = "new" ]
then
    echo '1. Cleaning docker...'
    docker-compose down
    docker rmi manager_user manager_config-server manager_cassandra-sidecar manager_eureka
    echo '2. Cleaning target diretories...'
    mvn clean
    echo '3. Building packages...'
    mvn package
    echo '4. Running docker solution...'
    docker-compose up -d
else
    echo '1. Running docker solution...'
    docker-compose up -d
fi

exit 0