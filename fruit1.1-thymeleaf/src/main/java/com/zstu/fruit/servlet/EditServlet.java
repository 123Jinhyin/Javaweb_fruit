package com.zstu.fruit.servlet;

import com.zstu.fruit.dao.FruitDAO;
import com.zstu.fruit.dao.impl.FruitDAOImpl;
import com.zstu.fruit.pojo.Fruit;
import com.zstu.ssm.springmvc.ViewBaseServlet;
import com.zstu.ssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: EditServlet
 * Package: com.zstu.fruit.servlet
 * Description:
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/4 - 16:09
 * @Version: v1.0
 */

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            super.processTemplate("edit", req, resp);
        }
    }
}
