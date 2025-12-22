#!/usr/bin/env bash
set -e

# 切到项目根目录（非常重要）
ROOT_DIR=$(cd "$(dirname "$0")/.." && pwd)

IMAGE_NAME="mes-mvn-kingdee"

echo "📦 Build context: $ROOT_DIR"
echo "🐳 Image: $IMAGE_NAME"

docker build \
  -f "$ROOT_DIR/scripts/Dockerfile" \
  -t "$IMAGE_NAME" \
  "$ROOT_DIR"
