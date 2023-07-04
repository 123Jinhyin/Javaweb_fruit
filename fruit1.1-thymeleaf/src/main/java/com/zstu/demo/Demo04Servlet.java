package com.zstu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo02Servlet
 * Package: com.zstu.demo
 * Description:演示request保存作用域
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/4 - 15:05
 * @Version: v1.0
 */

@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取session保存作用域保存的数据
        Object obj = req.getSession().getAttribute("uname");
        System.out.println("uname = " + obj);
    }
}
