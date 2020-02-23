# distribute

## 简介
该系统为违章处理服务系统，上海，北京，广州三个地点的系统分别运行在对应的三台服务器上。主要用户有交警与车主。交警登录系统之后可以对车辆添加违章信息，添加完成之后对应的信息保存在交警所在位置的服务器中。车主登录之后可以查询自己的车辆信息与违章记录。

前端采用bootstrap框架、Thymeleaf模板引擎、vue.js完成界面的渲染。后端主要使用spring boot+mybatis框架完成后台模块的功能实现，使用maven管理工具管理项目中用到的包等其他技术依赖；使用RMI完成服务器的远程互相调用。
