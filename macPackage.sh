#!/bin/bash
# 默认参数值
APP_NAME="video-fx"
# jar包名
JAR_NAME="springboot-fx-video.jar"
# jvm参数
XMX="128m"
XMS="64m"

REM 输出默认参数
echo 默认参数:
echo APP_NAME=${APP_NAME}
echo JAR_NAME=${JAR_NAME}
echo XMX=${XMX}
echo XMS=${XMS}

# 解析命令行参数
while [[ $# -gt 0 ]]; do
  case "$1" in
    --jar-name)
      JAR_NAME="$2"
      shift 2
      ;;
    --xmx)
      XMX="$2"
      shift 2
      ;;
    --xms)
      XMS="$2"
      shift 2
      ;;
    --app-name)
      APP_NAME="$2"
      shift 2
      ;;
    *)
      echo "Unknown option: $1"
      exit 1
      ;;
  esac
done
# 1. 打包前先执行 Maven 打包命令
echo "Step 1: 开始执行Maven打包命令..."
mvn -DskipTests=true clean install
if [ $? -ne 0 ]; then
  echo "执行Maven打包命令失败. Exiting..."
  exit 1
fi
echo "执行Maven打包命令成功."

# 2. 创建 target/input 目录
echo "Step 2: 创建 target/input 目录..."
mkdir -p target/input
if [ $? -ne 0 ]; then
  echo "创建目录失败. Exiting..."
  exit 1
fi
echo "创建目录成功"

# 3. 复制 jar 包到 target/input 目录
echo "Step 3: 复制 jar 包到 target/input..."
cp target/${JAR_NAME} target/input/app.jar
if [ $? -ne 0 ]; then
  echo "复制jar包失败. Exiting..."
  exit 1
fi
echo "复制jar包成功"

# 4. 使用 jpackage 打包为 dmg
echo "Step 4: 开始打包DMG..."
jpackage \
  --input target/input \
  --dest target/ \
  --name ${APP_NAME} \
  --main-jar app.jar \
  --app-version 1.0.0 \
  --type dmg \
  --java-options "-XX:+UseZGC" \
  --java-options "-Dfile.encoding=UTF-8" \
  --java-options "-Xmx${XMX}" \
  --java-options "-Xms${XMS}"
if [ $? -ne 0 ]; then
  echo "打包DMG失败. Exiting..."
  exit 1
fi
echo "打包DMG成功，请查看target目录下."