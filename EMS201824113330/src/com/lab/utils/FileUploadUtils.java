package com.lab.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 201824113330
 * @create 2020-12-12-20:55
 * @description
 */
public class FileUploadUtils {

    private Map<String, String> mp = new HashMap<>();

    /**
     * 用于 enctype="multipart/form-data" 的 form表单, 上传文件，并将所有表单数据项封装到 map 中
     * @param req
     */
    public FileUploadUtils(HttpServletRequest req) {

        try {
            // 解决上传文件的文件名中文乱码问题
            req.setCharacterEncoding("utf-8");

            if (ServletFileUpload.isMultipartContent(req)) {
                // 创建FileItemFactory工厂实现类
                FileItemFactory fileItemFactory = new DiskFileItemFactory();
                // 创建用于解析上传数据的工具类ServletFileUpload类
                ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

                // 解析上传的数据，得到每一个表单项FileItem
                List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
                // 循坏判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : fileItemList) {
                    if (fileItem.isFormField()) { //普通表单项
                        //System.out.print("表单项的name属性值：" + fileItem.getFieldName() + "  ----->  ");
                        //System.out.println("表单项的value属性值：" + fileItem.getString("UTF-8"));  //参数 UTF-8 解决乱码问题
                        mp.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
                    } else { //上传的文件
                        //System.out.print("表单项的name属性值：" + fileItem.getFieldName() + "  ----->  ");
                        //System.out.println("上传的文件名：" + fileItem.getName());
                        //System.out.println("表单项的value属性值：" + fileItem.getString("UTF-8"));  //参数 UTF-8 解决乱码问题

                        // 通过是否有文件名判断是否有上传文件，没有则跳过下面的文件上传
                        if ("".equals(fileItem.getName()) || fileItem.getName() == null) {
                            continue;
                        }

                        // 获取文件保存路径（保存在哪个文件夹下）
                        String savePath = req.getServletContext().getRealPath("/upload/");
                        System.out.println("图片文件保存路径： " + savePath);
                        // 文件路径若不存在, 则先创建
                        File savePathDir = new File(savePath);
                        if (!savePathDir.exists()) {
                            savePathDir.mkdir();
                        }
                        // 文件名（使其唯一）
                        String fileName = System.currentTimeMillis() + "_" + fileItem.getName();
                        // 完整的文件路径
                        String filePath = savePath + fileName;
                        // 将上传的文件写到 参数 file 所指向抽硬盘位置
                        fileItem.write(new File(filePath));
                        System.out.println("文件上传成功");

                        String fileRelativePath = "upload/" + fileName;
                        mp.put(fileItem.getFieldName(), fileRelativePath);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将所有表单数据注入到 javabean中
     * @param bean
     * @param <T>
     * @return
     */
    public <T> T copyParamToBean(T bean) {
        WebUtils.copyParamToBean(this.mp, bean);
        return bean;
    }

    /**
     * 获取表单中指定参数名的参数值
     * （javax.servlet.HttpServletRequest.getParameter(String) returns null when the ContentType is multipart/form-data）
     * @param parameterName
     * @return
     */
    public String getParameterValue(String parameterName) {
        for (Map.Entry<String, String>entry : this.mp.entrySet()) {
            if (entry.getKey().equals(parameterName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 删除设备的同时也把上传到服务器的图片文件删除
     * @param req
     * @param imgPath
     */
    public static void deleteFile(HttpServletRequest req, String imgPath) {
        String savePath = req.getServletContext().getRealPath("/upload/");
        File[] files = new File(savePath).listFiles();
        if (files == null || "".equals(imgPath) || imgPath == null) {
            return;
        }
        for (File file : files) {
            //System.out.println(file.getName());
            if (imgPath.contains(file.getName())) {
                file.delete();
                System.out.println("图片已从服务器删除！");
                break;
            }
        }
    }

}
