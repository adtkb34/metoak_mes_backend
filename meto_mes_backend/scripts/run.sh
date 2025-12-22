#!/usr/bin/env bash

# =====================================================
# 必要的环境变量检查
# =====================================================
check_env() {
    local missing=0

    for var in "${REQUIRED_ENV_VARS[@]}"; do
        if [ -z "${!var}" ]; then
            echo "❌ 环境变量未设置: $var"
            missing=1
        fi
    done

    if [ "$missing" -ne 0 ]; then
        exit 1
    fi

    echo "✅ 环境变量检查通过"
}

# =====================================================
# 初始化
# =====================================================
init() {
    SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
    PROJECT_DIR="$(cd "$SCRIPT_DIR/.." && pwd)"
    JAR_PATH=""
    PORT_CANDIDATES=(8081 8082)
    REQUIRED_ENV_VARS=(
        TEST_STAGE_DB_IP
        TEST_STAGE_DB_NAME
        TEST_STAGE_DB_USERNAME
        TEST_STAGE_DB_PASSWORD
        DEPLOY_STAGE_DB_IP
        DEPLOY_STAGE_DB_NAME
        DEPLOY_STAGE_DB_USERNAME
        DEPLOY_STAGE_DB_PASSWORD
    )

    echo "📁 脚本目录: $SCRIPT_DIR"
    echo "📦 项目目录: $PROJECT_DIR"
}

# =====================================================
# 用法说明
# =====================================================
usage() {
    cat <<EOF
Usage: $0 -m <mode>

Modes:
  test        只执行测试
  package     只执行打包
  deploy      只执行部署
  all         执行 test -> package -> deploy

Examples:
  $0 -m test
  $0 -m package
  $0 -m deploy
  $0 -m all
EOF
    exit 1
}

# =====================================================
# 参数解析
# =====================================================
parse_args() {
    MODE=""

    while getopts ":m:h" opt; do
        case $opt in
        m) MODE="$OPTARG" ;;
        h) usage ;;
        *) usage ;;
        esac
    done

    [ -z "$MODE" ] && usage
}

# =====================================================
# Stage: TEST
# =====================================================
run_test() {
    echo "🧪 [TEST] 开始测试..."

    cd "$PROJECT_DIR" || {
        echo "❌ 无法进入项目目录: $PROJECT_DIR"
        exit 1
    }

    if ! mvn test -Dspring.profiles.active=test; then
        echo "❌ [TEST] mvn test 执行失败"
        exit 1
    fi

    echo "🧪 [TEST] 测试完成"
}

# =====================================================
# Stage: PACKAGE
# =====================================================
run_package() {
    echo "🏗️  [PACKAGE] 开始构建..."

    cd "$PROJECT_DIR"
    local version
    version=$(git describe --tags --dirty --always | sed 's/^v//')
    export SOFTWARE_VERSION="$version"
    mvn versions:set -DnewVersion="$version"
    mvn clean package -Dspring.profiles.active=prod -DskipTests
    JAR_PATH="$PROJECT_DIR/target/$(ls target/*.jar | xargs -n 1 basename | head -n 1)"

    echo "🏗️  [PACKAGE] 构建完成"
}

# =====================================================
# Stage: DEPLOY
# =====================================================
run_deploy() {
    echo "🚀 [DEPLOY] 开始部署..."

    cd "$PROJECT_DIR"

    select_free_port
    start_new_app
    health_check
    stop_old_apps

    echo "🚀 [DEPLOY] 部署完成，当前服务端口: $DEPLOY_PORT"
}

select_free_port() {
    DEPLOY_PORT=""

    for port in "${PORT_CANDIDATES[@]}"; do
        if ! lsof -ti tcp:"$port" >/dev/null 2>&1; then
            DEPLOY_PORT="$port"
            echo "✅ 选择可用端口: $DEPLOY_PORT"
            return 0
        else
            echo "ℹ️ 端口 $port 已被占用"
        fi
    done

    echo "❌ 没有可用端口，候选端口均被占用: ${PORT_CANDIDATES[*]}"
    exit 1
}

start_new_app() {
    echo "🚀 启动新版本 (port=$DEPLOY_PORT)"
    CMD="nohup java -jar \"$JAR_PATH\" \
    --server.port=$DEPLOY_PORT \
    --spring.profiles.active=prod \
    > app_${DEPLOY_PORT}.out 2>&1 &"

    echo "🚀 执行启动命令："
    echo "$CMD"

    # 真正执行
    eval "$CMD"

    NEW_PID=$!
    echo "🆕 新进程 PID=$NEW_PID"
}

health_check() {
    echo "⏳ 对端口 $DEPLOY_PORT 进行健康检查..."

    LOCAL_IP=$(ip route get 1.1.1.1 | awk '{print $7}')
    for i in {1..20}; do
        if curl -fs "http://$LOCAL_IP:$DEPLOY_PORT/api/mes/v1/version" >/dev/null; then
            echo "✅ 服务健康 (port=$DEPLOY_PORT)"
            return
        fi
        sleep 1
    done

    echo "❌ 健康检查失败，停止新进程"
    kill "$NEW_PID" || true
    exit 1
}

stop_old_apps() {
    for port in "${PORT_CANDIDATES[@]}"; do
        if [ "$port" != "$DEPLOY_PORT" ]; then
            OLD_PID=$(lsof -ti tcp:"$port" || true)
            if [ -n "$OLD_PID" ]; then
                echo "🛑 停止旧服务 port=$port PID=$OLD_PID"
                kill "$OLD_PID" || true
            fi
        fi
    done
}

# =====================================================
# 执行调度
# =====================================================
dispatch() {
    case "$MODE" in
    test)
        run_test
        ;;
    package)
        run_package
        ;;
    deploy)
        run_deploy
        ;;
    all)
        run_test
        run_package
        run_deploy
        ;;
    *)
        echo "❌ 未知模式: $MODE"
        usage
        ;;
    esac
}

# =====================================================
# 主入口
# =====================================================
main() {
    parse_args "$@"
    init
    check_env
    dispatch

    echo "✅ pipeline 执行完成 (mode=$MODE)"
}

main "$@"
