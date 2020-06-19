# Pro-Cloud
**以下是Pro-Cloud架构简介**
#### 介绍(如果喜欢的话，请给个star)
   Pro-Cloud 是一个Security作为安全框架,采用前后端分离的模式. 基于OAuth2 的RBAC权限管理微服务系统. Pro-Cloud后端采用springcloud alibaba架构,集成Sentinel从流量控制、熔断降级、系统负载等多个维度保护服务的稳定性。
注册中心、配置中心选型Nacos，为工程瘦身的同时加强各模块之间的联动。使用OAuth2，实现了多终端认证系统，可控制子系统的token权限互相隔离。
使用SkyWalking链路最终技术,方便了解应用拓扑和慢服务监测等情况,prometheus+grafana+alertManager作为监控中心,它为actuator端点提供了良好的交互界面，并提供了额外的特性.
mybatisplus的使用,大大的节省了开发的工作量,让代码更易维护 前端基于layui开发,降低学习成本,快速上手,
#### [演示环境](http://119.23.8.73:1314/index.html)
服务器资源有限,监控模块没有部署 敬请谅解: [演示地址](http://119.23.8.73:1314/index.html)
#### Pro-Cloud架构图
![输入图片说明](https://images.gitee.com/uploads/images/2020/0323/221440_892a46d3_1236464.png "spring cloud 微服务.png")
#### 项目地址
[gitee项目地址链接](https://gitee.com/gitsc/pro-cloud)  
[github项目地址链接](https://github.com/pro-cloud/pro-cloud)
#### 相关工程
后台管理前端vue工程（pro-ui 待支持）：[码云地址](https://gitee.com/gitsc/pro-ui)  

#### 文档
详细请参考: [pro-cloud技术文档](https://www.kancloud.cn/aijm/pro-cloud/1340604)

#### dubbo的集成
dubbo的2.x版本由于高速发展,稳定性不够,但是是个趋势,项目pro-micro 是基于spring-cloud-alibaba的2.2.1最新版本建设,pro-cloud的的dubbo实现,暂不开源, 如果想要学习的话可以联系作者;

#### 软件架构
前台采用 vue.js 为核心框架;
后台基于 Spring Cloud alibaba2.2.1、Spring Security Oauth 2.0 开发企业级认证与授权，提供常见服务监控、链路追踪、日志分析、缓存管理、任务调度等实现，
nacos + Spring Cloud Oauth2 + Spring Cloud gateway +  Feign + mybatisplus等，各种组件注解开发，让代码简洁，通俗易通，以提高效率
```
Pro-Cloud
├── cloud-admin -- 系统基础模块
│   ├── cloud-admin-api   -- admin暴露的feign接口
│   └── cloud-admin-service -- admin模块的实现
├── cloud-auth  -- auth服务端 统一登录中心(支持单点登录和三方登录)
├─cloud-common   -- 系统公共模块
│  ├─cloud-common-cache  -- 缓存工具类+redis 分布式锁
│  ├─cloud-common-data  -- 对数据库操作工具类
│  ├─cloud-common-entity  -- 公共实体工具类
│  ├─cloud-common-job   -- 定时任务工具类
│  ├─cloud-common-mq    -- mq工具类
│  ├─cloud-common-oauth  -- oauth授权工具类
│  ├─cloud-common-oss    -- oss文件上传工具类
│  ├─cloud-common-security  -- 客户端安全工具类
│  ├─cloud-common-swagger -- swagger工具类
│  ├─cloud-common-util   -- 基础工具类
│  ├─cloud-common-websocket  -- websocket工具类
│  └─cloud-common-zk   -- zk分布式锁工具类
├─cloud-gateway   -- springcloud gateway 网关 
├─cloud-generator   -- 代码生成
├─cloud-message      -- 发送邮件短信模块
│  ├─cloud-message-api
│  └─cloud-message-service
├─cloud-monitor  -- 监控模块
├─cloud-oss  -- oss文件上传模块
│  ├─cloud-oss-api
│  └─cloud-oss-service
├─cloud-search  -- es收搜模块
├─cloud-sso-demo   -- 单点登录案例
├─cloud-transaction   -- 分布式事务
├─cloud-websocket   -- websocket案例
├─cloud-xxl-job   -- xxl-job案例
└── docs    -- pro-cloud文档
```
   
| 版本规划| 解决问题|
|:----: |:--------:|
| v0.5 | 微服务架构的搭建，基础数据，用户，角色，部门，微服务文件上传支持，在线监控等 |
| v0.6 | 定时任务处理xxl-job，分布式事物的解决，代码在线生成器 |
| v0.8 | vue-element-admin的集成和文档的完善 |
| v1.0 | 三方登录的集成, elk日志收集文档完善 当前版本|
 下一步规划  
 1. 处理sql文件,整理完整干净的sql文件
 2. 处理pro-ui 优化和一些bug
 3. 文档的整理
 
### 主要中间件区别
| pro-cloud 项目| pro-micro项目|
|:----: |:--------:|
| openfeign | dubbo |
| logback | log4j2|

### 代码区别:
pro-micro 对 pro-cloud的代码进行了优化,让代码可读性更高更易维护
1. 对auth模块的优化
2. 对admin中security的优化
3. pro-micro 实现灰度发布
 
 **如何参与代码贡献:** 请查阅doc 目录下的说明
#### 安装教程

1. 安装mysql redis idea工具
2. 导入代码
3. 使用skywalking 链路追踪

#### 使用说明

1. /auth/oauth/token 获取token 详细请参考docs目录下的接口调用demo(请用postman导入)
2. 先启动auth 统一登录中心，然后启动admin模块，统一管理后台
3. /code 获取验证码 同 1      
4. 生成代码接口示例：
generator/code?tableName=sys_user&moduleName=admin&comments=用户表     
5. 继承授权中心模块需要实现ProUserDetailsService接口（不实现只会走默认方法），如果定制发邮件需要重构SmsCodeSender接口
6. 继承data模块需要实现SystemService。获取当前用户id（不实现只会走默认方法）

#### 参与贡献

1. [Mybatis-Plus](https://mp.baomidou.com/)
2. [Spring Cloud Oauth2](https://spring.io/projects/spring-security-oauth)
3. [Nacos](https://nacos.io/zh-cn/docs/quick-start.html)
4. [hutool](https://www.hutool.cn/docs/#/)

#### 欢迎加群
[![加入QQ群](https://img.shields.io/badge/238254944-blue.svg)](https://jq.qq.com/?_wv=1027&k=57NNidS)

![输入图片说明](https://images.gitee.com/uploads/images/2020/0312/115300_6bd86d98_1236464.png "Pro-Cloud交流群群聊二维码.png")
#### Pro-Cloud建设

1. 官方地址 [www.eduvipx.cn](http://www.eduvipx.cn) 文档地址http://www.eduvipx.cn:8000/ 官方网站正在建设中…… 可以先查看文档
![输入图片说明](./docs/image/1.png "2.png")
![输入图片说明](./docs/image/2.png "屏幕截图.png")
![输入图片说明](./docs/image/3.png "屏幕截图.png")
![输入图片说明](./docs/image/4.png "屏幕截图.png")
![输入图片说明](./docs/image/5.png "屏幕截图.png")
![输入图片说明](./docs/image/6.png "屏幕截图.png")
