# Ju-API-Backend

![GitHub](https://img.shields.io/github/license/dingxinliang88/Ju-API-Backend)	![size](https://img.shields.io/github/languages/code-size/dingxinliang88/Ju-API-Backend)	![commit](https://img.shields.io/github/commit-activity/w/dingxinliang88/Ju-API-Backend/master)

> API interface call platform, custom SDK for developers to use

## 简介

一个提供 API 接口供开发者调用的平台。

管理员可以接入并发布接口，统计分析各接口调用情况；用户可以注册登录并开通接口调用权限，然后可以浏览接口及在线调试，还能使用客户端 SDK 轻松在代码中调用接口。



## 目录结构

```sh
├── LICENSE
├── README.md
├── img	# 项目简介图片
├── juapi-backend	# api接口管理后台
├── juapi-common	# 公共类
├── juapi-gateway	# API网关
├── juzi-interface	# 测试用的模拟接口
└── juziapi-client-sdk	# 供开发者更方便使用的sdk
```

## 项目架构图

![structure](https://raw.githubusercontent.com/dingxinliang88/figure/master/img/structure.jpg)

## 技术栈

1. Java Spring Boot
2. MySQL 数据库
3. MyBatis-Plus 及 MyBatis X 自动生成
4. API 签名认证（Http 调用）
5. Spring Boot Starter（SDK 开发）
6. Dubbo 分布式（RPC、Nacos）
7. Swagger + Knife4j 接口文档生成
8. Spring Cloud Gateway 微服务网关
9. Hutool、Apache Common Utils、Gson 等工具库

## 项目亮点

1. 🌟🌟🌟**API 签名认证**🌟🌟🌟
2. 🌟🌟🌟**自定义SDK(Starter)**🌟🌟🌟
3. **RPC远程调用**

---

## TODO

1. 实现其他用户上传接口
   1. 需要提供一个机制（界面），让用户输入自己的接口 host（服务器地址）、接口信息，将接口信息写入数据库
   2. 可以在 interfaceInfo 表里加个 host 字段，区分服务器地址，让接口提供者更灵活地接入系统
   3. 将接口信息写入数据库之前，要对接口进行校验（比如检查他的地址是否遵循规则，测试调用），保证他是正常的
   4. 将接口信息写入数据库之前遵循咱们的要求（并且使用本项目开发的自定义sdk）
   5. 在接入接口时，平台需要测试这个接口，保证接口可用
2. 网关校验接口调用次数时需要考虑高并发问题，防止瞬时调用超频
3. 网关优化
   1. 增加限流
   2. 降级保护
   3. 提高性能
   4. 搭配`Nginx`使用

## 参与本项目

1. Fork本项目到你自己的仓库
2. 克隆到本地进行开发
   1. 新建功能分支：`feature-xxx`，其中`xxx`为功能名
   2. 修复bug分支：`fix-xxx`，其中`xxx`为bug简述
3. 按照分支类别提交Pull Request到本项目到`pr-feature`、`pr-fixbug`分支

> 有任何问题，欢迎提`issue`来提问😊

---

## 分支结构

- `master` => 主分支
- `dev` => 开发分支
- `pr-feature` => pr提交功能分支
- `pr-fixbug` => pr提交修复bug分支

## LICENSE

[Apache-2.0](https://www.apache.org/licenses/LICENSE-2.0)

---

## 联系作者

- 邮箱
  - 网易：d1741530592@163.com
  - 谷歌：dingxinliang1118@gmail.com
- QQ：1741530592
