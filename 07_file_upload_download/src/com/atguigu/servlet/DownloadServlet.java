package com.atguigu.servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author Hollay
 * @create 2020-11-04-21:13
 * @description
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1. 获取要下载的文件名
        String downloadFileName = req.getParameter("fileName");
        //String downloadFileName = "d.jpg";
        System.out.println(downloadFileName);
//        2.读取要下载的文件内容（通过ServletContext对象可以读取）
        ServletContext servletContext = getServletContext();
        // 获取要下载的文件类型
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        System.out.println("下载的文件类型：" + mimeType);
//        4. 再回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);
//        5. 还要告诉客户端收到的数据是用于下载使用(还是使用响应头)
       /*
                Content-Disposition 响应头，表示收到数据怎么处理
                attachment 表示附件，表示下载使用
                filename= 表示指定下载的文件名
        */
        //resp.setHeader("Content-Disposition", "attachment; filename=" + downloadFileName);

        // 解决不同浏览器的中文乱码问题
        // 通过判断请求头中 User-Agent 这个请求头来判断是否是火狐浏览器
        if (req.getHeader("User-Agent").contains("Firefox")) {
            // 是火狐浏览器就使用 BASE64 编解码 解决附件中文名问题
//            String str = "attachment; fileName=" + "=?utf-8?B?" + new BASE64Encoder().encode("中文.jpg".getBytes("utf-8")) + "?=";
            String str = "attachment; fileName=" + "=?utf-8?B?" + new BASE64Encoder().encode(downloadFileName.getBytes("utf-8")) + "?=";
            // 设置到响应头中
            resp.setHeader("Content-Disposition", str);
        } else {
            // 其他浏览器（IE或谷歌）就使用 URLEncoder 解决 IE 和谷歌浏览器的 附件中文名问题， 把中文名进行 UTF-8 编码操作。
            // url编码是把汉字转换为 %XX%XX 的格式（）
//            String str = "attachment; fileName=" + URLEncoder.encode("中文.jpg", "UTF-8");
            String str = "attachment; fileName=" + URLEncoder.encode(downloadFileName, "UTF-8");
            // 然后把编码后的字符串设置到响应头中
            resp.setHeader("Content-Disposition", str);
        }


        /**
         * / 斜杠被服务器解析表示地址为http://ip:port/工程名/   映射到 web目录
         */
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
        // 获取响应的输出流
        OutputStream outputStream = resp.getOutputStream();
//        3. 把下载的文件内容回传给客户端
        //读取输入流中全部的数据， 复制给输出流，输出给客户端
        IOUtils.copy(resourceAsStream, outputStream);

    }
}
