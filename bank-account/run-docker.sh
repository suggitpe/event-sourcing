#!/bin/bash

docker build -t accounts-service .

docker run -d -p 8801:8801 --net=host accounts-service
