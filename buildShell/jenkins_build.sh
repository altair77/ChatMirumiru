#!/bin/sh -xe

gradlew test
gradlew checkstyleMain
gradlew findbugsMain
gradlew cobertura
gradlew build