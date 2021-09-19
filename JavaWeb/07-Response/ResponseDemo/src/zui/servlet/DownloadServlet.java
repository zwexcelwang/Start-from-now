package zui.servlet;


import zui.utils.DownloadUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;



@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，文件名称
        String filename = req.getParameter("filename");
        //找到文件服务器路径
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/img/" + filename);
        //使用字节输入流加载文件进内存
        FileInputStream fileInputStream = new FileInputStream(realPath);

        //设置响应头类型
        String mimeType = servletContext.getMimeType(filename);
        resp.setContentType(mimeType);
        // 解决中文文件名的问题, 获取请求头
        String agent = req.getHeader("user-agent");
        // 使用工具类方法编码文件名即可
        filename = DownloadUtil.getFileName(agent, filename);
        //设置响应头打开方式,download_name就是下载的时候显示的名字
//        resp.setHeader("content-disposition", "attachment;filename=" + "download_name");
        resp.setHeader("content-disposition", "attachment;filename=" + filename);

        //将输入流的数据写出到输出流中
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        byte[] buff = new byte[1024*8];     //缓冲区
        int len = 0;    //读到的个数
        while((len = fileInputStream.read(buff)) != -1) {   // 说明没到文件的末尾
            servletOutputStream.write(buff, 0 ,len);
        }

        fileInputStream.close();
    }
}
