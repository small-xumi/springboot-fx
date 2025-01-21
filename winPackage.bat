@echo off
REM 默认参数值，这个参数是可以自定义的，修改成你想要打包后的msi的名称
set APP_NAME=video-fx
REM jar包名，这个是springboot打包后的名称，需要和<artifactId>springboot-fx</artifactId>的名称一致
set JAR_NAME=springboot-fx.jar
REM jvm参数，这个参数跟你的实际使用场景来配置定义
set XMX=128m
set XMS=64m

REM 输出默认参数
echo 默认参数:
echo app名称=%APP_NAME%,jvm参数: XMX=%XMX%,XMS=%XMS%

REM 解析命令行参数
:parse_args
if "%1"=="" goto end_args
if "%1"=="--jar-name" (
    set JAR_NAME=%2
    shift
    shift
    goto parse_args
)
if "%1"=="--xmx" (
    set XMX=%2
    shift
    shift
    goto parse_args
)
if "%1"=="--xms" (
    set XMS=%2
    shift
    shift
    goto parse_args
)
if "%1"=="--app-name" (
    set APP_NAME=%2
    shift
    shift
    goto parse_args
)
echo Unknown option: %1
exit /b 1

:end_args

REM 1. 打包前先执行 Maven 打包命令
echo Step 1: 开始执行Maven打包命令...
call mvn -DskipTests=true clean install
if %errorlevel% neq 0 (
    echo 执行Maven打包命令失败. Exiting...
    exit /b 1
)
echo 执行Maven打包命令成功.

REM 2. 创建 target/input 目录
echo Step 2: 创建 target/input 目录...
if not exist "target\input" (
    mkdir target\input
    if %errorlevel% neq 0 (
        echo 创建目录失败. Exiting...
        exit /b 1
    )
)
echo 创建目录成功

REM 3. 复制 jar 包到 target/input 目录
echo Step 3: 复制 jar 包到 target/input...
copy target\%JAR_NAME% target\input\app.jar
if %errorlevel% neq 0 (
    echo 复制jar包失败. Exiting...
    exit /b 1
)
echo 复制jar包成功

REM 4. 使用 jpackage 打包为 dmg
echo Step 4: 开始打包DMG...
call jpackage ^
  --input target\input ^
  --dest target ^
  --name %APP_NAME% ^
  --main-jar app.jar ^
  --app-version 1.0.0 ^
  --type msi ^
  --java-options "-XX:+UseZGC" ^
  --java-options "-Dfile.encoding=UTF-8" ^
  --java-options "-Xmx%XMX%" ^
  --java-options "-Xms%XMS%"
if %errorlevel% neq 0 (
    echo 打包msi失败. Exiting...
    exit /b 1
)
echo 打包msi成功，请查看target目录下.

exit /b 0