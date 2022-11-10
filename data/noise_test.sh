#!/bin/bash

for f in *.dat; do 
    sp800_22_tests/sp800_22_tests.py $f; 
done

