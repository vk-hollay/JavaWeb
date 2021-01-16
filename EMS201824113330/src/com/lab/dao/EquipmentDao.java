package com.lab.dao;

import com.lab.bean.Employee;
import com.lab.bean.Equipment;

import java.sql.Date;
import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-07-19:26
 * @description
 */
public interface EquipmentDao {

    /**
     * 查询所有设备
     * @return
     */
    public List<Equipment> queryAllEquipments();

    /**
     * 添加设备
     * @param equipment
     * @return
     */
    public int addEquipment(Equipment equipment);

    /**
     * 根据 设备id 删除设备
     * @param id
     * @return
     */
    public int deleteEquipmentById(String id);

    /**
     * 修改设备所有信息（包括设备编号）
     * @param equipment
     * @param oldId
     * @return
     */
    public int updateEquipmentInfo(Equipment equipment, String oldId);


    public Equipment queryById(String id);

    public List<Equipment> queryByName(String name);

    public List<Equipment> queryBySpecification(String specification);

    public List<Equipment> queryByPrice(String price);

    public List<Equipment> queryByBuydate(String year);

    public List<Equipment> queryByPosition(String position);

    public List<Equipment> queryByManager(String manager);

    public List<Equipment> queryByDepartment(String Department);


}
