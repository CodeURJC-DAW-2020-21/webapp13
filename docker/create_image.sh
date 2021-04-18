#!/bin/bash
#
# BUILD THE DOCKER IMAGE FROM DOCKER HUB BY DOCKER COMPOSE
# PORTHUB  - DAW GROUP 13
# AUTHORS: CRISTIAN DE GRACIA NUERO, JOSÉ JUSTO TENA AGUDO, JOSÉ MANUEAL AGU
# 

echo "PORTHUB - DAW GROUP 13"
echo "Create app image via docker"
echo "To run this script you need to have installed docker"
echo "If you dont have it you can install docker from:" 
echo " -> https://docs.docker.com/engine/install/"
echo " -> $ sudo apt install snap"
echo "    $ sudo snap install docker"

echo "Are you ready to go? (Y/N)"
read GO
if [[ $GO == N || $GO == n ]]
then
    echo "Exiting..."
    exit 1
fi

echo

echo "Compiling Maven project"
cd ../backend
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
echo "Maven project compiled"

echo "Building docker image from Dockerfile"
cp target/*.jar ../docker
cd ../docker
sudo docker build -t porthub .
rm *.jar
echo "Image built sucessfully, if you want to run the app use"
echo "$ sudo docker-compose up"
 
exit 0

