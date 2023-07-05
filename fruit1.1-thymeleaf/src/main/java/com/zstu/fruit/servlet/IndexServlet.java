package com.zstu.fruit.servlet;

import com.zstu.fruit.dao.FruitDAO;
import com.zstu.fruit.dao.impl.FruitDAOImpl;
import com.zstu.fruit.pojo.Fruit;
import com.zstu.ssm.springmvc.ViewBaseServlet;
import com.zstu.ssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: IndexSetvlet
 * Package: com.zstu.fruit.servlet
 * Description:
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/3 - 15:05
 * @Version: v1.0
 */
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer pageNum = 1;
        String pageNumStr = req.getParameter("pageNum");
        if(StringUtil.isNotEmpty(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }

        HttpSession session = req.getSession();
        session.setAttribute("pageNum", pageNum);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(pageNum);
        //保存到session作用域
        session.setAttribute("fruitList", fruitList);

        int fruitCount = fruitDAO.getFruitCount();
        int pageCount = (fruitCount + 5 - 1)/5;
        session.setAttribute("pageCount", pageCount);
        //此处的视图名称是index
        //那么thymeleaf会将这个逻辑视图名称对应到物理视图名称上去
        //逻辑视图名称： index
        //物理视图名称： view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是： / + index + .html
        super.processTemplate("index", req, resp);
    }
}
