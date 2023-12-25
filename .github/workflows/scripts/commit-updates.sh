#!/bin/bash
git config --local user.email "action@github.com"
git config --local user.name "GitHub Action"
git add pom.xml
git commit -m "ci: update pom.xml version to $RELEASE_VERSION"
git push origin HEAD:main
