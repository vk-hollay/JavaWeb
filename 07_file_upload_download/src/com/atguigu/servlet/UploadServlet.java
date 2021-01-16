package com.atguigu.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Hollay
 * @create 2020-11-04-14:28
 * @description
 */
public class UploadServlet extends HttpServlet {

    /**
     * 用来处理上传的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*      System.out.println("文件上传过来了！");

        ServletInputStream inputStream = req.getInputStream();

        byte[] buffer = new byte[1024000];
        int read = inputStream.read(buffer);
        System.out.println(new String(buffer, 0, read));
*/
        // 解决上传文件的文件名中文乱码问题
        req.setCharacterEncoding("utf-8");
        // 解决浏览器响应显示中文乱码问题
        resp.setContentType("text/html;charset=UTF-8");

        //1 先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
        if (ServletFileUpload.isMultipartContent(req)) {
            // 创建FileItemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                // 解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(req);
                // 循坏判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()) {
                        //普通表单项
                        System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                        //参数 UTF-8 解决乱码问题
                        System.out.println("表单项的value属性值：" + fileItem.getString("UTF-8"));
                    } else {
                        //上传的文件
                        System.out.println("表单项的name属性值：" + fileItem.getFieldName());
                        System.out.println("上传的文件名：" + fileItem.getName());
                        // 将上传的文件写到 参数 file 所指向抽硬盘位置
                        fileItem.write(new File("E:\\IdeaJavaProject\\JavaWeb\\07_file_upload_download\\web\\file\\" + fileItem.getName()));
                        resp.getWriter().write("文件上传成功！");
                    }
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
