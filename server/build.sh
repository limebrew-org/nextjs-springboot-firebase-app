#!/bin/bash

BUILD_NUMBER=1

./mvnw -B clean -DskipTests -DBUILD_NUMBER=$BUILD_NUMBER

./mvnw -B package -DskipTests -DBUILD_NUMBER=$BUILD_NUMBER