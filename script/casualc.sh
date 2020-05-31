#!/bin/bash

path=$1
fileComplete=${path##*/}
base=${fileComplete%.*}

java -jar ./casualc.jar $1
llc ${base}.ll
clang -o $base $base.s
