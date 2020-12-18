#!/bin/sh

mvn clean package

echo "Spring batch build completed"
echo "Build & push"

docker build . -t snimmagadda/stacke-batch-mysql && docker push snimmagadda/stacke-batch-mysql