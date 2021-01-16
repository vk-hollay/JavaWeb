## 书城项目
[toc]

### 图书模块 - CRUD：
1. 编写图书模块的数据库表
2. 编写图书模块的 JavaBea
3. 编写图书模块的 Dao 和测试 Dao
4. 编写图书模块的 Service 和测试 Service
5. 编写图书模块的 Web 层，和页面联调测试

【 Web层(Servlet程序) ~~---访问---~~> Service层 ~~---访问---~~> Dao层 】


### problem：
1. 数据表建立的时候id是设为主键、自增的，在对改数据表内容进行增删测试的时候，
发现第一次 add 时 id 是从 20 --> 21, delete 21, 再add， id 变为20 --> 22， 直接跳过 21 。
2. Object 不能强制类型转换为 Interger，会报类型转换异常。
    * 原因：Object其实也是有自己的“格式类型”的，即如果一个Object原本也是Integer，那么它可以强转为Integer没有问题，但若它原本是其他类型的，则不能强转为Integer，否则会报类型转换异常。
            这也是子类可以强转为父类，但父类不可强转为子类的原因。
            只有子类的对象向上转型成父类对象时，这种子类转化过来的父类对象才可以强制转型成子类对象。
    * 解决方法： 先将Object类转换为String类，再将String类转换为integer类：`Integer.valueOf(String.valueOf(obj))`
       
                


### Attention & Note：
1. 数据库建库建表时最好同时设置好数据库字符编码为utf-8 。
2. javaBean实体类定义变量都得定义成包装类，因为数据库查询有可能返回null。(定义成基本数据类型似乎也行，因为基本数据类型和其包装类之间会进行自动装箱、自动拆箱)
3. 写完接口再写完其实现类，需要对其进行测试：在接口代码中的第一行按 ctrl + shift + T 自动生成测试类。
4. 提交form表单时，若请求方式为 post，则需要在 Servlet 程序的 doPost 方法第一行设置编码格式`req.setCharacterEncoding("utf-8");`, 
否则会出现中文乱码问题。（或使用过滤器统一设置编码格式）。若请求方式为 get，则无需此操作。
5. 如果一个jsp页面需要跳转到另一个jsp页面，并且需要从数据库获取数据并显示出来，
   如果这样直接从第一个jsp跳转到第二个jsp是没有数据的，即访问jsp无法直接得到数据，
   那么这时可以让程序先去访问Servlet程序，将要得到的数据保存到Request域中，再请求转发到需要跳转到的jsp页面。
6. 当对一个form表单内容进行填写完点提交后，页面跳转到另一个页面，这时刷新页面会出现表单重复提交的问题。
    * 原因：当用户提交完请求，浏览器会记录下最后一次请求的全部信息。当用户按下功能键 F5，就会发起浏览器记录的最后一次请求。
    * 解决方法：使用请求重定向（不能使用请求转发）。因为请求转发只是一次请求，而请求重定向是两次请求，利用请求重定向两次请求的特点，
    使得浏览器记录的最后一次请求信息已不是前面那个表单提交的请求了。
7. Referer请求头记录网页来源地址，通过`req.getHeader("Referer")`可获取。
8. 获取项目绝对路径：
`String basePath = request.getScheme() //获取 ip 协议
                       + "://"
                       + request.getServerName() //获取服务器主机名
                       + ":"
                       + request.getServerPort() //获取端口号
                       + request.getContextPath() //获取项目路径
                       + "/";`
9. 数据库事务管理：
    1. 要确保所有操作要么都成功。要么都失败。就必须要使用数据库的事务。
    2. 要确保所有操作都在一个事务内，就必须要确保，所有操作都使用同一个Connection连接对象。
        * 如何确保所有操作都使用同一个Connection连接对象?
            * 我们可以使用ThreadLocal对象。来确保所有操作都使用同一个Connection对象。
            * ThreadLocal要确保所有操作都使用同一个Connection连接对象，那么操作的前提条件是所有操作都必须在同一个线程中完成!!!
10. 请求重定向路径要写绝对路径（即前面要加上项目名）`resp.sendRedirect(req.getContextPath() + "/pages/client/department.jsp");`<br/>
请求转发则可写相对路径`req.getRequestDispatcher("pages/client/department.jsp").forward(req, resp);`

### Thinking：
1. 市面上购物车的实现技术版本有:
   1. Session版本(把购物车信息保存到Session域中) ----> 本项目使用这种方法
   2. 数据库版本(把购物车信息，保存到数据库)、
   3. redis+数据库+Cookie (使用Cookie+Redis缓存，和数据库)

