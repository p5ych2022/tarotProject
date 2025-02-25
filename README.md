##### 框架设计
版本信息:
- springboot:2.7.18;
- jdk: 17
- vue：2.5.2

框架：
- 后端 Java springboot
- 前端 vue2
- 数据库 MySQL(使用 MyBatis 映射)

后端采用`SpringBoot`框架进行开发。 主要包括以下几个模块：

- `config`: 包含 Spring Security配置、web mvc配置、AI 接口配置
- `controller`: 控制层，负责接收请求，调用服务层处理业务逻辑，并返回响应结果
- `service`：服务层，负责处理业务逻辑，调用数据访问层进行数据操作
- `mapper`: 映射器，包含 MyBatis 映射器接口，便于与数据库进行交互。这些映射器用于直接从服务层执行数据库操作
- `DTO`: 数据传输对象，封装数据并将其从一个应用程序层发送到另一个应用程序层
- `filter`: 主要用于 JWT 处理的身份验证过滤器
- `model`: User 和 TarotCard
- `security`: 一下加密和JWT的util

前端采用`vue`进行开发。主要有以下几个路由：
- `login`
- `register`
- `resetPassword`
- `tarot`


