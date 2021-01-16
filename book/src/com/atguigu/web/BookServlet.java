package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Hollay
 * @create 2020-11-21-11:08
 * @description
 */
public class BookServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    /**
     * 处理分页功能，查询当前分页数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        String pageNoString = req.getParameter("pageNo");
        String pageSizeString = req.getParameter("pageSize");
        int pageNo = pageNoString == null ? 1 : Integer.parseInt(pageNoString);
        int pageSize = pageSizeString == null ? Page.PAGE_SIZE : Integer.parseInt(pageSizeString);
        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        // 设置 url ， 为分页请求地址
        page.setUrl("manager/bookServlet?action=page");
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        1、获取请求的参数==封装成为 Book 对象 ( 一次性把所有的请求参数 注入 到 JavaBean 中)
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
//        2、调用 BookService.addBook()保存图书
        bookService.addBook(book);
//        3、跳到图书列表页面    /manager/bookServlet?action=queryList
/*      req.getRequestDispatcher("/manager/bookServlet?action=queryList").forward(req, resp);
           不能使用请求转发，因为请求转发会出现刷新页面表单重复提交的 bug。
        原因：
            当用户提交完请求，浏览器会记录下最后一次请求的全部信息。当用户按下功能键 F5，就会发起浏览器记录的最后一次请求。
            这里最后一次请求是 addBook，所以刷新页面会发起浏览器记录的最后一次请求，即再次 addBook。

         queryList(req, resp);
         上述请求转发是转发到仍是本 sevlet， 所以其实同直接调用  queryList 没啥区别。
*/
//        解决方法：使用请求重定向。因为请求转发只是一次请求，而请求重定向是两次请求，浏览器记录的最后一次请求信息已不是 addBook，而是 queryList。
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=queryList");
        int pageNo = Integer.parseInt(req.getParameter("pageNo")) + 1; //+1保证一定是最后一页
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookService.deleteBookById(Integer.valueOf(id));
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=queryList");
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=queryList");
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 查询所有图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 通过 BookService 查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到 Request 域中
        req.setAttribute("books", books);
        //3、请求转发到 /pages/manager/book_manager.jsp 页面(第一个 / 映射到工程项目的 Web 目录下 )
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }

    // getBook 用于回显数据到表单项中
    protected void getBook(HttpServletRequest req,  HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(Integer.valueOf(id));
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }
}
