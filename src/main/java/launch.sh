#!/bin/sh
find * -name "*.java" > sources.txt
javac @sources.txt
java avaj.Simulator.Main ../../test/examples/scenario.txt
