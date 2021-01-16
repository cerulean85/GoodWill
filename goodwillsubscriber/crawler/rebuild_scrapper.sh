#!/bin/bash

dir_arr=("build" "dist" "__pycache__")
for dir in "${dir_arr[@]}"
do
  if [ -d $dir ]; then
    rm -rf $dir
    echo "remove $dir"
  fi
done

if [ -f "scrapper.spec" ]; then
  rm scrapper.spec
  echo "remove scrapper.spec" 
fi

if [ -f "../src/main/resources/scrapper.exe" ]; then
  rm ../src/main/resources/scrapper.exe
  echo "remove ../src/main/resources/scrapper.exe"
fi

pyinstaller -F scrapper.py && cp dist/scrapper.exe ../src/main/resources/scrapper.exe
cp parse-rule-donga.yaml ../src/main/resources/parse-rule-donga.yaml

wait

cd dist && echo "Finished, Please execute."