#!/bin/bash

# This script would be responsible for echoing the environment variable
# declarations to $GITHUB_ENV.

echo "DEFAULT_BRANCH=main" >> $GITHUB_ENV
echo "NODE_VERSION=20.x" >> $GITHUB_ENV
echo "PYTHON_VERSION=3.9" >> $GITHUB_ENV
echo "JAVA_VERSION=17" >> $GITHUB_ENV

# ... any additional variables ...
