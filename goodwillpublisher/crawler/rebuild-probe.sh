#!/bin/bash

dir_arr=("build" "dist" "__pycache__")
for dir in "${dir_arr[@]}"
do
  if [ -d $dir ]; then
    rm -rf $dir
    echo "remove $dir"
  fi
done

if [ -f "probe.spec" ]; then
  rm probe.spec
  echo "remove probe.spec" 
fi

if [ -f "../src/main/resources/probe.exe" ]; then
  rm ../src/main/resources/probe.exe
  echo "remove ../src/main/resources/probe.exe"
fi

pyinstaller -F probe.py && cp dist/probe.exe ../src/main/resources/probe.exe

wait

cd dist && echo "Finished, Please execute."