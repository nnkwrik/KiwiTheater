#!/bin/bash

mvn clean package -Dmaven.test.skip=true -U

docker build -t nnkwrik/kiwi-ssm .

#docker run -p 8080:8080 nnkwrik/kiwi-ssm

docker push nnkwrik/kiwi-ssm
