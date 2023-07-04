package com.zstu.fruit.dao;

import com.zstu.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //查询库存列表
    List<Fruit> getFruitList();

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
