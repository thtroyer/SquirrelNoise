#!/bin/bash

for f in *.dat; do
  echo "Testing file: $f"
  sp800_22_tests/sp800_22_tests.py $f;
  echo "Finished file: $f"
  echo "-----------------"
  echo "-----------------"
  echo "-----------------"
done

