#!/bin/bash

docker build -t nnkwrik/kiwi-mysql .

#docker run nnkwrik/kiwi-mysql

docker push nnkwrik/kiwi-mysql
