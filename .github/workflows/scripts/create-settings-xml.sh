#!/bin/bash

cat <<EOF > $HOME/.m2/settings.xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <servers>
    <server>
      <id>airshipcraft-releases</id>
      <username>${{ secrets.SONATYPE_USERNAME }}</username>
      <password>${{ secrets.SONATYPE_PASSWORD }}</password>
    </server>
    <server>
      <id>airshipcraft-snapshots</id>
      <username>${{ secrets.SONATYPE_USERNAME }}</username>
      <password>${{ secrets.SONATYPE_PASSWORD }}</password>
    </server>
  </servers>
</settings>
EOF
