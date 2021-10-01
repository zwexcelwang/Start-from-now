package zui.web.servlet;

import com.alibaba.druid.support.monitor.annotation.AggregateType;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //通知服务器不要缓存
        resp.setHeader("pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        resp.setHeader("expires", "0");

        int width = 80;
        int height = 30;
        //创建一对象，在内存中图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //美化图片
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0,0, width, height);

//        graphics.setColor(Color.BLUE);
//        graphics.drawRect(0, 0, width-1, height-1);

        String checkCode = getCheckCode();
        req.getSession().setAttribute("checkCodeInSession", checkCode);

        graphics.setColor(Color.YELLOW);
        graphics.setFont(new Font("黑体", Font.BOLD, 24));
        graphics.drawString(checkCode, 15, 25);

//        //干扰线
//        graphics.setColor(Color.GREEN);
//        for(int j=0; j<6; j++) {
//            int x1 = ran.nextInt(width);
//            int x2 = ran.nextInt(width);
//            int y1 = ran.nextInt(height);
//            int y2 = ran.nextInt(height);
//            graphics.drawLine(x1, y1, x2, y2);
//        }

        //将图片输出到页面显示
        ImageIO.write(image, "png", resp.getOutputStream());
    }


    private String getCheckCode() {
        String str = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        StringBuilder stringBuilder =  new StringBuilder();
        Random ran = new Random();
        for(int i=0; i<4; i++) {
            int index = ran.nextInt(str.length());
            char ch = str.charAt(index);
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

}

