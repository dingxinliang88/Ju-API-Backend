# Ju-API-Client-SDK

> 供开发者更方便使用接口的源码包

## Starter开发

参考文章：https://juejin.cn/post/6844903919601057805

### 大致流程

1. 新建一个Maven项目，在pom.xml文件中定义好所需依赖；
2. 新建配置类，写好配置项和默认值，使用`@ConfigurationProperties`指明配置项前缀；
3. 新建自动装配类，使用`@Configuration`和`@Bean`来进行自动装配；
4. 新建`spring.factories`文件，用于指定自动装配类的路径；
5. 将starter安装到maven仓库，让其他项目能够引用；