### 什么是 AJAX 请求

AJAX 即“Asynchronous Javascript And XML”（异步 JavaScript 和 XML），是指一种创建交互式网页应用的网页开发技术。

ajax 是一种浏览器通过 js 异步发起请求，局部更新页面的技术。

Ajax 请求的局部更新，浏览器地址栏不会发生变化。局部更新不会舍弃原来页面的内容。

### jQuery 中的 AJAX 请求
1. url 表示请求的地址
2. type 表示请求的类型 GET 或 POST 请求
3. data 表示发送给服务器的数据
    * 格式有两种：
        1. name=value&name=value
        2. {key:value}
4. success 请求成功，响应的回调函数
5. dataType 响应的数据类型
    * 常用的数据类型有：
        1. text 表示纯文本
        2. xxml 表示 xml 数据
        3. json 表示 json 对象