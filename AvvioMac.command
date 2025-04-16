#!/bin/bash

cd /Users/marcobarlera/Documents/Programmazione/Java/theKnife/TheKnife

# Compilazione
javac -cp "lib/json-simple-1.1.1.jar" -d bin -Xlint:unchecked src/*.java

# Esecuzione forzando UTF-8
java -Dfile.encoding=UTF-8 -cp "lib/json-simple-1.1.1.jar:bin" src.Main