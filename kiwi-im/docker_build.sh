#!/bin/bash

#打包 包括依赖的jar
mvn assembly:assembly -Dmaven.test.skip=true -U

docker build -t nnkwrik/kiwi-im .

#docker run  nnkwrik/kiwi-im


#直接命名,减少一次tag的操作

docker push nnkwrik/kiwi-im