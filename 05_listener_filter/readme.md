### Filter过滤器
它的作用是：拦截请求（可用于权限检查），过滤响应。

### Filter 过滤器的使用步骤：
1. 编写一个类去实现 Filter 接口
2. 实现过滤方法 doFilter()
3. 到 web.xml 中去配置 Filter 的拦截路径

### 拦截请求常见的应用场景有：
    1、权限检查
    2、日记操作
    3、事务管理
    ……等等
    
### 多个过滤器
1. 在多个Filter过滤器执行的时候，它们执行的优先顺序
是由它们在web.xml中从上到下配置的顺序决定。
2. 所有filter和目标资源默认都执行在同一个线程中。
3. 多个Filter共同执行的时候，它们都使用同一个Request对象。

### 三种匹配模式
1. 精准匹配`<url-pattern>/target.jsp</url-pattern>`
2. 目录匹配`<url-pattern>/admin/*</url-pattern>`
3. 后缀名匹配`<url-pattern>*.html</url-pattern>`