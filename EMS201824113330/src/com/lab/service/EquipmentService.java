package com.lab.service;

import com.lab.bean.Equipment;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-07-21:34
 * @description
 */
public interface EquipmentService {

    public List<Equipment> showAll();

    public int add(Equipment equipment);

    public int delete(String id);

    public int update(Equipment equipment, String oldId);

    /**
     * 根据 设备 id 查询
     * @param id
     * @return
     */
    public Equipment queryById(String id);

    /**
     * 根据指定条件和指定的参数查询（除 id 外）
     * @param condition
     * @param parameter
     * @return
     */
    public List<Equipment> queryByCondition(String condition, String parameter);

}
