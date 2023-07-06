package com.zstu.fruit.controller;

import com.zstu.fruit.dao.FruitDAO;
import com.zstu.fruit.dao.impl.FruitDAOImpl;
import com.zstu.fruit.pojo.Fruit;
import com.zstu.ssm.springmvc.ViewBaseServlet;
import com.zstu.ssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

public class FruitController extends ViewBaseServlet {
    private FruitDAO fruitDAO = new FruitDAOImpl();
    
    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {
        //执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price, fcount, remark));
        //资源跳转
        //super.processTemplate("index", req, resp);
        //req.getRequestDispatcher("index.html").forward(req, resp);
        //重定向，目的是重新给IndexServlet发请求，重新获取数据库数据
        return "redirect:fruit.do";
    }
    private String edit(Integer fid, HttpServletRequest req) {
        if (fid != null) {
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            req.setAttribute("fruit", fruit);
            //super.processTemplate("edit", req, resp);
            return "edit";
        }
        return "error";
    }
    private String to_add(HttpServletRequest req) {
        return "add";
    }
    private String del(Integer fid, HttpServletRequest req) {
        if(fid != null) {
            fruitDAO.delFruitByFid(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }
    private String add(String fname, Integer price, Integer fcount, String remark) {
        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitDAO.addFruit(fruit);
        return "redirect:fruit.do";
    }
    private String index(String oper, String keyword, Integer pageNum, HttpServletRequest req) throws ServletException {
        HttpSession session = req.getSession();
        
        //如果oper != null,说明 通过表单的查询按钮点击过来的
        //如果oper == null,说明 不是通过表单的查询按钮点击过来的
        if(StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //此时，pageNum应该还原为1， keyword应该从请求参数中获取
            pageNum = 1;
            if(StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        }else {
            //keyword从session作用域获取
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null) {
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }
        //重新更新当前页的值
        session.setAttribute("pageNum", pageNum);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNum);

        session.setAttribute("fruitList", fruitList);
        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 5 - 1)/5;
        session.setAttribute("pageCount", pageCount);
        
        return "index";
    }
}
