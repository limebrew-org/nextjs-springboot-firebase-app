#!/bin/bash

BUILD_NUMBER=1
IMAGE_NAME=animus_api_image

./mvnw -B clean -DskipTests -DBUILD_NUMBER=$BUILD_NUMBER

./mvnw -B package -DskipTests -DBUILD_NUMBER=$BUILD_NUMBER

#? Clean Build Docker Images
if [[ "$(docker images -q $IMAGE_NAME:latest 2> /dev/null)" == "" ]]
then
  echo "Image not found"
else
  echo "Image found"
  make compose_dev_down_v2
  docker system prune -a --force
  docker volume prune --force
fi

##? Build and Run Docker
make compose_dev_up_v2