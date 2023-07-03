package com.zstu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: GetDataServlet
 * Package: com.zstu.servlets
 * Description:
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/3 - 11:14
 * @Version: v1.0
 */
public class GetDataServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object obj = req.getSession().getAttribute("uname");
        System.out.println("uname : " + obj);
    }
}
