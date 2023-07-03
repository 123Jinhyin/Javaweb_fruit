package com.zstu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Provider;

/**
 * ClassName: SessionSerlet
 * Package: com.zstu.servlets
 * Description:
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/3 - 9:50
 * @Version: v1.0
 */
public class SessionServlet extends HelloServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session,如果获取不到，则创建一个新的
        HttpSession session = req.getSession();
        System.out.println("session ID : " +  session.getId());
        session.setAttribute("uname", "lina");
    }
}
