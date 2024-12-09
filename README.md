# Java_and_Web_Application
Course: Java_and_Web_Application

# Environment Setup

+ Apache Tomcat 11.0
+ Smart Tomcat 4.7.3
+ Azul Zulu 17.0.13 (JDK17)
+ IntelliJ IDEA Community Edition 2024.1.4
+ MySQL 8.4
+ Windows 11

# TO DO

计划实现一个校园论坛系统，预期实现功能如下：

- [x] 用户注册与登录：
+ 用户可以创建账户、登录、修改个人资料。
- [x] 帖子管理：
+ 用户可以发布新帖子，编辑和删除自己的帖子。
- [x] 评论系统：
+ 用户可以对帖子进行评论，支持评论的编辑和删除。
+ 对评论进行排序（例如按时间或点赞数排序）。
- [ ] 点赞与举报：
+ 支持用户点赞帖子或评论。
+ 提供帖子或评论的举报功能，管理员可以查看举报内容。
- [ ] 搜索功能：
+ 允许用户搜索帖子或评论（基于标题、内容或标签）。
- [X] 个人主页与通知系统：
+ 用户可以查看自己的帖子、评论和点赞历史。
+ 系统可以向用户发送通知（如新回复、点赞通知等）。
- [X] 管理员管理后台：
+ 管理员可以审核帖子和评论，处理举报内容。
+ 管理员可以对用户进行相关操作。

# Update Logs

## 2024-11-02
+ 完成基本框架搭建

## 2024-11-07
+ 计划实现一个校园论坛系统

## 2024-11-10
+ 学习了一些基本的JavaWeb开发概念：[JavaBean](https://blog.csdn.net/CZB_xiaoniu/article/details/79470703)、[java知识之Controller+Dao+Service+Filter+Pojo+Utils+Vo各层分析](https://blog.csdn.net/qq_38881474/article/details/96447154)
+ 实现可能用到的类：管理员/用户、发帖/回复

## 2024-11-12
+ Mybatis便利JDBC：[MyBatis(优点&缺点)&MyBatis使用详解！](https://zhuanlan.zhihu.com/p/338487669)、[Mybatis 框架快速入门（超详细）](https://blog.csdn.net/weixin_43883917/article/details/113731380)

## 2024-11-13
+ MyBatis + 网站调用，实现“增”操作
+ 暂时有点写不动了，把注册和登录网页的css写了
+ 新增：用户页面，主页面，退出的Servlet

## 2024-11-14
+ 游客帖子广场，游客点赞filter处理

## 2024-11-15
+ 作者信息的网页
+ 注册页面验证码添加
+ 游客模式下，查看帖子详情页面
+ 现在，用户可以创建账户、登录/登出、修改个人资料。

## 2024-11-16
+ 用户页面查看帖子详情，and可以对帖子进行评论/点赞

## 2024-11-18
+ 完善部分css
+ 完善帖子广场，用户模式下“我的帖子”功能

## 2024-11-19
+ 用户可以发布新帖子，编辑和删除自己的帖子
+ 导出数据库供参考
+ 完善一部分管理员页面

## 2024-11-20
+ 添加设置/取消精选贴功能
+ 继续完善管理员页面（帖子列表&用户管理）

## 2024-11-21
+ css（用户页面下作者信息）
+ 发帖/更新帖子/管理员删帖子的Servlet
+ 修改为markdown语法发帖/更新帖子，不过有些问题，还得改。（后面还需要做图片水印和文件上传）

## 2024-11-22
+ markdown支持（查看、编辑），帖子预览过滤markdown

## 2024-11-24
+ 删除帖子、用户的Servlet
+ 水印处理还有问题，还需要进一步修改

## 2024-11-26
+ 依据点赞数进行排序

## 2024-11-29
+ 优化帖子显示逻辑

## 2024-12-04
+ 布局优化
+ 用户信息输出为json

## 2024-12-05
+ 文件下载功能
+ 服务器端通过Jackson将java对象序列化成json字符串返回
+ 客户端通过Ajax方式异步访问服务器API接口获取json数据
+ 客户端通过jquery将返回json数据渲染成页面上的html表格
+ 客户端通过获取form表单数据，转换成json提交到服务器
+ 服务器端将json反序列化成java对象，并存储到数据库

## 2024-12-06
+ 文件上传功能

## 2024-12-08
+ 利用maven构建war包（后续会更新Setup步骤，包括数据库）

## 2024-12-09
+ 添加初始化前端页面（如果没有swubbs数据库，就进入初始化页面）