#!/usr/bin/env bash
set -e

# =========================
# Maven 坐标信息
# =========================
GROUP_PATH="com/kingdee"
ARTIFACT_ID="k3cloud-webapi-sdk"
VERSION="8.2.0"

M2_REPO="/root/.m2/repository"
TARGET_DIR="$M2_REPO/$GROUP_PATH/$ARTIFACT_ID/$VERSION"
TARGET_JAR="$TARGET_DIR/${ARTIFACT_ID}-${VERSION}.jar"

SRC_JAR="/opt/kingdee/${ARTIFACT_ID}-${VERSION}.jar"

echo "🔍 检查金蝶 SDK 是否已存在于 Maven 本地仓库..."

if [ ! -f "$TARGET_JAR" ]; then
  echo "📦 未发现 SDK，开始安装到 Maven 本地仓库"

  mkdir -p "$TARGET_DIR"
  cp "$SRC_JAR" "$TARGET_JAR"

  echo "✅ SDK 已安装: $TARGET_JAR"
else
  echo "✅ SDK 已存在，跳过安装"
fi

echo "🚀 执行命令: $@"
exec "$@"
