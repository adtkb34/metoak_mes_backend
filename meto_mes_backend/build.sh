#!/bin/bash
# =============================================================
# ☕ Java Spring Boot 打包脚本（支持自定义输出路径）
# 用法:
#   ./build_java.sh <项目路径> [打包名] [压缩名] [输出目录]
#
# 示例:
#   ./build_java.sh ./java
#   ./build_java.sh ./java mes_java
#   ./build_java.sh ./java mes_java mes_java_release
#   ./build_java.sh ./java mes_java mes_java_release ./release
# =============================================================

# ---------- 帮助 ----------
if [[ "$1" == "-h" || "$1" == "--help" ]]; then
  echo "☕ 用法: $0 <项目路径> [打包名] [压缩名] [输出目录]"
  echo "示例:"
  echo "  $0 ./java"
  echo "  $0 ./java mes_java"
  echo "  $0 ./java mes_java mes_java_release"
  echo "  $0 ./java mes_java mes_java_release ./release"
  echo ""
  echo "说明:"
  echo "  - 若未指定打包名，默认: spring_app_YYYYMMDD_HHMM"
  echo "  - 若未指定压缩名，则与打包名一致"
  echo "  - 若未指定输出目录，默认在当前项目路径下生成"
  exit 0
fi

# ---------- 参数 ----------
PROJECT_PATH=$1
PACKAGE_NAME=${2:-spring_app_$(date +%Y%m%d_%H%M)}
ARCHIVE_NAME=${3:-$PACKAGE_NAME}
OUTPUT_DIR=${4:-.}

# ---------- 校验 ----------
if [ -z "$PROJECT_PATH" ]; then
  echo "❌ 缺少项目路径。使用 -h 查看帮助。"
  exit 1
fi

if [ ! -d "$PROJECT_PATH" ]; then
  echo "❌ 无效的路径: $PROJECT_PATH"
  exit 1
fi

cd "$PROJECT_PATH" || { echo "❌ 无法进入目录: $PROJECT_PATH"; exit 1; }

# ---------- 构建 ----------
echo "🚀 [1/4] 清理旧构建..."
rm -rf target build

echo "🏗 [2/4] 构建 Spring 项目..."
if [ -f "pom.xml" ]; then
  mvn clean package -DskipTests || { echo "❌ Maven 构建失败"; exit 1; }
  JAR_FILE=$(find target -name "*.jar" | head -n 1)
elif [ -f "build.gradle" ]; then
  ./gradlew clean bootJar -x test || { echo "❌ Gradle 构建失败"; exit 1; }
  JAR_FILE=$(find build/libs -name "*.jar" | head -n 1)
else
  echo "❌ 未检测到 pom.xml 或 build.gradle"
  exit 1
fi

if [ ! -f "$JAR_FILE" ]; then
  echo "❌ 未生成 jar 文件"
  exit 1
fi

# ---------- 压缩 ----------
echo "📦 [3/4] 压缩输出..."
mkdir -p "$OUTPUT_DIR"

# 临时目录用于打包
TEMP_DIR="${OUTPUT_DIR}/${PACKAGE_NAME}_tmp"
mkdir -p "$TEMP_DIR"

cp "$JAR_FILE" "${TEMP_DIR}/${PACKAGE_NAME}.jar"

tar -czf "${OUTPUT_DIR}/${ARCHIVE_NAME}.tar.gz" -C "$OUTPUT_DIR" "$(basename $TEMP_DIR)" || {
  echo "❌ 压缩失败"; rm -rf "$TEMP_DIR"; exit 1;
}

# 删除临时目录
rm -rf "$TEMP_DIR"

# ---------- 完成 ----------
ABS_PATH=$(cd "$OUTPUT_DIR" && pwd)/${ARCHIVE_NAME}.tar.gz
echo "✅ [4/4] Java 打包完成: ${ABS_PATH}"
