#!/bin/bash
export CLASSPATH=$CLASSPATH:mysql-connector-java-5.1.15-bin.jar
javac *.java
java Main < input
