#!/bin/bash

function restart() {
    echo "Stop and remove all containers"
    docker kill $(docker ps -a -q)
    sleep 2
    docker rm $(docker ps -a -q)

    echo "Remove data"
    sudo rm -rf grafana
    sudo rm -rf db

    echo "Creating a backup folder"
    mkdir grafana
    chmod 777 -R grafana

    echo "Start the service"
    (export USER=$USER && export PASS=$PASS && docker-compose up -d)

    echo "Install Grafana Plug-in"
    docker exec grafana grafana-cli plugins install grafana-piechart-panel
    docker restart grafana

    echo "Create demo database"
    curl -i -XPOST http://localhost:8086/query --data-urlencode "q=CREATE DATABASE demo"

    echo "Services are ready"
}

$@
