#!/usr/bin/env bash
./blog.py && cat out.adoc | npx downdoc -  > out.md
