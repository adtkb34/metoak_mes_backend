#!/usr/bin/env bash
set -e

docker run --rm -it \
  -v ~/.m2:/root/.m2 \
  -v $(pwd):/workspace \
  mes-mvn-kingdee \
  mvn -v