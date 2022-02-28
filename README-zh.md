# Android项目翻译资源整理工具

将Android项目的资源文件夹中`strings.xml`文件整理到excel中

## 功能介绍

- 将Android项目的strings.xml资源整理到excel表格
- 将excel表格解析成Android可用的strings.xml资源

## 如何构建

构建安装包:
```shell script
./gradlew build
```

运行项目:
```shell script
./gradlew run
```

输出为:
```shell script
usage: TranslateTool
 - <arg>             d/decode
 -d,--dir <arg>      Set translate resource dir
 -h,--help           Print this usage information.
 -o,--output <arg>   Set output file path
 -v,--version        Print version information.
```

## 如何使用

你可以在到这个目录进行下载:
[dist](https://github.com/LiuShuaiQ/android-translate-tools/tree/main/dist)

1. 下载解压到目录，进行使用
2. 在bin文件夹下运行启动脚本即可

### strings.xml -> excel

使用实例:
```shell script
./TranslateTools -d <XXX/AndroidProject/app/src/main/res>
```

-d,--dir  
设置Android需要翻译的resource文件夹, e.g. /AndroidProject/app/src/main/res

### excel -> strings.xml

```shell script
./TranslateTools d -d XXX.xlsx
```

