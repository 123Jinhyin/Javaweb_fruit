package com.zstu.fruit.servlet;

import com.zstu.fruit.dao.FruitDAO;
import com.zstu.fruit.dao.impl.FruitDAOImpl;
import com.zstu.fruit.pojo.Fruit;
import com.zstu.ssm.springmvc.ViewBaseServlet;
import com.zstu.ssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: FruitServlet
 * Package: com.zstu.fruit.servlet
 * Description:
 *
 * @Author: ZSTU_JY
 * @Create: 2023/7/5 - 14:13
 * @Version: v1.0
 */

@WebServlet("/fruit.do")
public class FruitServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");

        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        switch (operate) {
            case "index" :
                index(req, resp);
                break;
            case "add" :
                add(req, resp);
                break;
            case "del" :
                del(req, resp);
                break;
            case "edit" :
                edit(req, resp);
                break;
            case "update" :
                update(req, resp);
                break;
            case "to_add" :
                to_add(req, resp);
                break;
            default:
                throw new RuntimeException("operate值非法！");
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");

        //获取参数
        String fidStr = req.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        //执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        //资源跳转
        //super.processTemplate("index", req, resp);
        //req.getRequestDispatcher("index.html").forward(req, resp);
        //重定向，目的是重新给IndexServlet发请求，重新获取数据库数据
        resp.sendRedirect("fruit.do");
    }
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            super.processTemplate("edit", req, resp);
        }
    }
    private void to_add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.processTemplate("add", req, resp);
    }
    private void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fidStr = req.getParameter("fid");
        if(StringUtil.isNotEmpty(fidStr)) {
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruitByFid(fid);
        }

        resp.sendRedirect("fruit.do");
    }
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("utf-8");

        //获取参数
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("price");
        Integer price = Integer.parseInt(priceStr);
        String fcountStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = req.getParameter("remark");

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);

        fruitDAO.addFruit(fruit);

        resp.sendRedirect("fruit.do");
    }
    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer pageNum = 1;
        HttpSession session = req.getSession();

        String oper = req.getParameter("oper");
        //如果oper != null,说明 通过表单的查询按钮点击过来的
        //如果oper == null,说明 不是通过表单的查询按钮点击过来的

        String keyword = null;
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明点击表单查询发送过来的请求
            //此时，pageNum应该还原为1， keyword应该从请求参数中获取
            pageNum = 1;
            keyword = req.getParameter("keyword");
            if(StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        }else {
            //此处不是点击表单发送过来的请求
            //keyword从session作用域获取
            String pageNumStr = req.getParameter("pageNum");
            if(StringUtil.isNotEmpty(pageNumStr)) {
                pageNum = Integer.parseInt(pageNumStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null) {
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }

        session.setAttribute("pageNum", pageNum);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNum);
        //保存到session作用域
        session.setAttribute("fruitList", fruitList);

        int fruitCount = fruitDAO.getFruitCount(keyword);
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
