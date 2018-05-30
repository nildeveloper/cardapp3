package com.edu.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class VerifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        HttpSession session = request.getSession();
        int width = 85;
        int height = 35;
        // 设置浏览器不缓存图片
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        // 创建内训图片并获得其图形上下文
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        // 产生随即验证码，定义验证码字符表
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands = new char[4];
        for (int i = 0; i < rands.length; i++) {
            int rand = (int)(Math.random()*36);
            rands[i] = chars.charAt(rand);
        }
        // 产生图像，花背景
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0,0,width,height);
        // 随机产生120个干扰点
        for (int i = 0; i < 120; i++) {
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);
            int red = (int) (Math.random()*255);
            int green = (int)(Math.random()*255);
            int blue = (int)(Math.random()*255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x,y,1,0);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font(null,Font.ITALIC|Font.BOLD, 20));
        // 在不同高度上输出验证码不同字符
        g.drawString("" + rands[0],1,19);
        g.drawString("" + rands[1],16,25);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3],46,26);
        // 将验证码放入到session
        session.setAttribute("realCode", String.valueOf(rands));
        g.dispose();
        // 将图像输出到客户端
        ServletOutputStream sos = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.setUseCache(false);  // 使用内存缓存
        ImageIO.write(image,"JPEG",baos);
        byte[] buffer = baos.toByteArray();
        response.setContentLength(buffer.length);
        sos.write(buffer);
        baos.close();
        sos.close();
    }
}
