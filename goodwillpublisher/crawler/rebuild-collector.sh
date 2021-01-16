#!/bin/bash

dir_arr=("build" "dist" "__pycache__")
for dir in "${dir_arr[@]}"
do
  if [ -d $dir ]; then
    rm -rf $dir
    echo "remove $dir"
  fi
done

if [ -f "collector.spec" ]; then
  rm collector.spec
  echo "remove collector.spec" 
fi

if [ -f "../src/main/resources/collector.exe" ]; then
  rm ../src/main/resources/collector.exe
  echo "remove ../src/main/resources/collector.exe"
fi

pyinstaller -F collector.py && cp dist/collector.exe ../src/main/resources/collector.exe

wait

cd dist && echo "Finished, Please execute."