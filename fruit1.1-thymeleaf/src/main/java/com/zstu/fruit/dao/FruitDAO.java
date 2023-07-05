package com.zstu.fruit.dao;

import com.zstu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询指定页码上的库存列表，每页显示5条
    List<Fruit> getFruitList(String keyword, Integer pageNum);

    //查询库存总记录条数
    int getFruitCount(String keyword);

    //新增库存
    boolean addFruit(Fruit fruit);

    //修改指定的库存记录
    boolean updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);

    //删除特定库存记录
    boolean delFruit(String fname);

    //根据Fid获取
    Fruit getFruitByFid(Integer fid);

    //根据Fid删除指定的库存记录
    void delFruitByFid(Integer fid);
}
