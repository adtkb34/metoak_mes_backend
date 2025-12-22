FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /workspace

# 1️⃣ 把 jar 拷进镜像（只是暂存）
# <jarpath> 替换为你的实际路径
COPY 3rdparty/k3cloud-webapi-sdk-8.2.0.jar /opt/kingdee/k3cloud-webapi-sdk-8.2.0.jar
COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]
