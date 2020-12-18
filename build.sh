#!/bin/sh

mvn clean package

echo "Spring batch build completed"
echo "Build & push"
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker build . -t snimmagadda/stacke-batch-mysql && docker push snimmagadda/stacke-batch-mysql