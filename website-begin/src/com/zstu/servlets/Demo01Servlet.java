package com.zstu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.zstu.servlets
 * Description:
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/3 - 14:22
 * @Version: v1.0
 */
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo01......");
        //服务端内部转发
        //req.getRequestDispatcher("demo02").forward(req,resp);
        resp.sendRedirect("demo02");
    }
}
