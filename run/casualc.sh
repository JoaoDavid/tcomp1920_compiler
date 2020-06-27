#!/bin/bash

path=$1
fileComplete=${path##*/}
base=${fileComplete%.*}

java -jar ./casualc.jar $@
llc ${base}.ll
clang -o $base $base.s
