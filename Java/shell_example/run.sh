#!/bin/bash
JAVA_HOME="/usr/lib/jvm/java-7-oracle/bin" 
PROJECT_DIR="src" 
cd ${PROJECT_DIR}
${JAVA_HOME}/javac -cp . com/demo/Main.java
${JAVA_HOME}/java -cp . com.demo.Main
cd ..
