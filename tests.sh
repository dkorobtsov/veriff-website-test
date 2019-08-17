#!/usr/bin/env bash
docker run --network=host --rm -v "${PWD}/.gradle:/home/gradle/.gradle" -v "$PWD":/home/gradle/project -v "$PWD"/allure-results:/home/gradle/project/allure-results -w /home/gradle/project gradle:latest gradle --no-daemon --info test
