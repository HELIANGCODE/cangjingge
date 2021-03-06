# 小说平台——藏经阁
## 功能简述
功能按照4个角色进行划分。

### 未登录者
1. 查找小说
2. 查看小说的基本数据（名称、简介、章节目录）

### 读者
1. **包含未登录者功能**
2. 查看章节内容
3. 书架添加
4. 翻页（下一章、上一章）
5. 阅读、发布书评

### 作者
1. **包含读者功能**
2. 小说新建
3. 小说信息修改
4. 章节新增
5. 章节修改

### 管理员
1. **包含读者功能**
2. 章节修改
3. 删除书评

## 后端微服务模块
后端提供服务的模块是下面4个：

### 模块authorization：用户授权与鉴权
1. 登录：使用用户名，密码获得令牌，令牌缓存至Redis
2. 刷新令牌：使用登录时获取的刷新令牌更新访问令牌
3. 鉴权：检查访问中headers中Authorization所携带的令牌是否有效，用户是否具有访问权限

### 模块user：用户
1. 书架：CRD关注的图书列表
2. 注册：使用用户名，密码进行注册

### 模块fiction：小说
1. 查找小说：名称匹配
	* 权限：所有人
2. 获取小说信息：名称、简介、目录、发布时间等
	* 权限：所有人
3. 获得小说章节内容
    * 权限：基本用户及以上
4. 新建小说
	* 权限：基本用户及以上
5. 小说内容编辑、新增
	* 权限：作者及以上

### 模块fiction-review：书评
1. 书评CRD
	* 权限：读者权限及以上
	* 读者可删除自己的书评，管理员可以删除所有人的

除此之外，还有另外4个模块提供了基础服务。

### 模块common：公共函数
1. entity：系统返回给前端的数据实体
2. exception：包含系统中统一的基础异常BusinessException，以及统一的异常处理器RestExceptionHandler
3. response：包含统一返回至前端的标准实体，系统错误的枚举类，以及Response的装箱拆箱辅助类
4. security：包含鉴权相关文件
5. service：包含Feign接口
6. swagger：Swagger暴露接口的配置类

### 模块eureka-server
1. 服务注册中心

### 模块hystrix-dashboard
1. Hystrix监测端

### 模块zuul-gateway
1. Zuul网关，用于服务聚合以及提供SwaggerUI
