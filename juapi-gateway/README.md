# Ju-API-Gateway

> 网关层

**网关优点**： 统一进行操作，去处理一些问题



## 1、网关作用

1. 路由
2. 负载均衡
3. 统一鉴权
4. 统一处理跨域
5. 统一业务处理（缓存）
6. 访问控制
7. 发布控制
8. 流量染色
9. 统一接口保护
   1. 限制请求
   2. 信息脱敏
   3. 降级（熔断）
   4. 限流 学习令牌桶算法，学习露桶算法，学习一下RedislimitHandler
   5. 超时时间
   6. 重试（业务保护）
10. 统一日志
11. 统一文档



## 2、具体作用

**路由**

起到转发的作用，比如有接口A和接口B,网关会记录这些信息，根据用户访问的地址和参数，转发请求到对应的接口（服务器/集群）

用户a调用接口A

/a => 接口A
/b => 接口B

https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#gateway-request-predicates-factories



**负载均衡**

在路由的基础上可以转发到某一个服务器

/c => 服务A/ 集群A（随机转发到其中的某一个机器）

uri从固定地址改成b:xx



**统一鉴权**

判断用户是否有权限进行操作，无论访问什么接口，我都统一去判断权限，不用重复写



**统一处理跨域**

网关统一处理跨域，不用在每个项目单独处理

https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#cors-configuration



**统一业务处理**

把每个项目中都要做的通用逻辑放到上层（网关），统一处理，比如本项目的次数统计



**访问控制**

黑白名单，比如限制DDOS IP



**发布控制**

灰度发布，比如上线新接口，先给新接口分配 20%流量，老接口80% ,再慢慢调整比例

https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-weight-route-predicate-factory



**流量染色**

区分用户来源

给请求（流量）添加一些标识，一般是设置请求头中，添加新的请求头
https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-addrequestheader-gatewayfilter-factory

**全局染色**：https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#default-filters



**接口保护**

1. 限制请求

   https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#requestheadersiz-gatewayfilter-factory

2. 信息脱敏 

   https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-removerequestheader-gatewayfilter-factory

3. 降级（熔断） 进行兜底

   https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#fallback-headers

4. 限流   

   https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-requestratelimiter-gatewayfilter-factory

5. 超时时间    超时就中断

   https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#http-timeouts-configuration 

6. 重试（业务保护）：

   https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-retry-gatewayfilter-factory

   

**统一日志**

统一的请求，响应信息记录



**统一文档**

将下游项目的文档进行聚合，在一个页面统一查看

建议用：https://doc.xiaominfo.com/docs/middleware-sources/aggregation-introduction



**网关的分类**

-   **全局网关（接入层网关）**作用是负载均衡、请求日志等，不和业务逻辑绑定
-   **业务网关（微服务网关）**会有一些业务逻辑，作用是将请求转发到不同的业务/项目/接口/服务

参考文章：https://blog.csdn.net/qq_21040559/article/details/122961395



**实现**

1. **Nginx** （全局网关），**Kong网关**（API网关），  **编程成本相对较高**
2. **Spring Cloud Gateway**（取代了Zuul）性能高 可以用java代码来写逻辑  适于学习

网关技术选型：https://zhuanlan.zhihu.com/p/500587132



## 3、技术选型：Spring Cloud Gateway

官网：https://spring.io/projects/spring-cloud-gateway

官方文档：https://docs.spring.io/spring-cloud-gateway/docs/current/reference//html/