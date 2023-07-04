package com.zstu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.zstu.demo
 * Description:演示request保存作用域
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/4 - 14:52
 * @Version: v1.0
 */
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向request保存作用域
        req.setAttribute("uname","lili");
        //2.客户端重定向
        //resp.sendRedirect("demo02");

        //3.服务器端转发
        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}
