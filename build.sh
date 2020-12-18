#!/bin/sh
set -ev
mvn clean package

echo "Spring batch build completed"
echo "Build & push"
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker build . -t snimmagadda/stacke-batch-mysql:"$TRAVIS_BUILD_NUMBER"
docker tag snimmagadda/stacke-batch-mysql:"$TRAVIS_BUILD_NUMBER" snimmagadda/stacke-batch-mysql:latest
docker push snimmagadda/stacke-batch-mysql:"$TRAVIS_BUILD_NUMBER" && docker push snimmagadda/stacke-batch-mysql:latest