#!/bin/bash

docker ps | grep accounts-service | cut -d" " -f1 | xargs docker stop
