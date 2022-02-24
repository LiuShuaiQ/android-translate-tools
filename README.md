# Android Translate Tool

Organize strings.xml under Android resources into an excel table tool.

[中文文档](./README-zh.md)

## How to build

build the package:
```shell script
./gradlew build
```

run the project:
```shell script
./gradlew run
```

the output is:
```shell script
usage: TranslateTool
 -d,--dir <arg>      Set translate resource dir
 -h,--help           Print this usage information.
 -o,--output <arg>   Set output file path
 -v,--version        Print version information.
```


## How to use

You can download dist in this directory:
[dist](https://github.com/LiuShuaiQ/android-translate-tools/tree/main/dist)

1. Download and unzip to the directory
2. Run the startup script in the bin folder

example:
```shell script
./TranslateTools -d <XXX/AndroidProject/app/src/main/res>
```

-d,--dir  
set translate resource dir, e.g. /AndroidProject/app/src/main/res