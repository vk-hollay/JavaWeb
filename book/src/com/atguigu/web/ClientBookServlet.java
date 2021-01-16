package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hollay
 * @create 2020-11-23-16:32
 * @description
 */
public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
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
        page.setUrl("clientBookServlet?action=page");
        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 处理分页功能，查询当前分页数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String minString = req.getParameter("min");
        String maxString = req.getParameter("max");
        if ("".equals(minString) && "".equals(maxString)) {
            req.getRequestDispatcher("clientBookServlet?action=page").forward(req, resp);
            return;
        }
        //1 获取请求的参数 pageNo 和 pageSize
        String pageNoString = req.getParameter("pageNo");
        String pageSizeString = req.getParameter("pageSize");
        int pageNo = pageNoString == null ? 1 : Integer.parseInt(pageNoString);
        int pageSize = pageSizeString == null ? Page.PAGE_SIZE : Integer.parseInt(pageSizeString);
        int min = minString == "" ? 0 : Integer.parseInt(minString);
        int max = maxString == "" ? Integer.MAX_VALUE : Integer.parseInt(maxString);

        //2 调用 BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        // 这里 url不能加 min 和 max，不然空参数时点查询后再点击下一页的时候回显会出问题，会回显出后台设置的默认值
        //page.setUrl("clientBookServlet?action=pageByPrice&min=" + min + "&max=" + max);
        page.setUrl("clientBookServlet?action=pageByPrice&min=" + minString + "&max=" + maxString);

        //3 保存 Page 对象到 Request 域中
        req.setAttribute("page", page);
        //4 请求转发到 pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
