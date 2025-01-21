## javafx整合springboot
这个项目的初衷是为了为了降低javafx的开发难度

同时解决了linux和mac的打包问题，我通过编写了两个脚本，分别对应到mac的打包和win的打包，并且约定好对应的参数，直接一键打包即可

这个项目还在初始阶段，后续有时间我将会和大家一起持续优化这个项目

最终的目标就是达到桌面端的vue效果，让大家快速开发出桌面端应用

## 打包成exe

需要jdk17
win打包需要安装winx,下载地址为:https://github.com/wixtoolset/wix3

### 参数说明

--name 打包成对应app的名称
--main-jar 打包成对应app的jar包名称，需要和打包后的jar的名称保持一致
--app-version 打包成对应app的版本号
--java-options 就是jvm参数了
垃圾回收器建议使用zgc，内存控制会相比默认的gc优秀很多

## 项目打包命令
mac打包dmg
1、放开脚本权限
```shell
chmod +x macPackage.sh
```
2、执行打包命令
```shell
./macPackage.sh
```
win打包msi
```shell
package.bat
```

