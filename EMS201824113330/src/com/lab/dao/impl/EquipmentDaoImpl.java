package com.lab.dao.impl;

import com.lab.bean.Equipment;
import com.lab.dao.EquipmentDao;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-07-20:08
 * @description
 */
public class EquipmentDaoImpl extends BaseDao implements EquipmentDao {
    @Override
    public List<Equipment> queryAllEquipments() {
        String sql = "select * from equipment";
        return queryForList(Equipment.class, sql);
    }

    @Override
    public int addEquipment(Equipment equipment) {
        String sql = "insert into equipment(`id`,`name`,`specification`,`price`,`buydate`,`position`,`imgPath`,`manager`) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return update(sql, equipment.getId(), equipment.getName(), equipment.getSpecification(), equipment.getPrice(), equipment.getBuyDate(), equipment.getPosition(), equipment.getImgPath(), equipment.getManager());
}

    @Override
    public int deleteEquipmentById(String id) {
        String sql = "delete from equipment where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateEquipmentInfo(Equipment equipment, String oldId) {
        String sql = "update equipment set id=?,name=?,specification=?,price=?,buydate=?,position=?,imgPath=?,manager=? where id=?";
        return update(sql, equipment.getId(), equipment.getName(), equipment.getSpecification(), equipment.getPrice(), equipment.getBuyDate(), equipment.getPosition(), equipment.getImgPath(), equipment.getManager(), oldId);
    }



    @Override
    public Equipment queryById(String id) {
        String sql = "select * from equipment where id=?";
        return queryForOne(Equipment.class, sql, id);
    }

    @Override
    public List<Equipment> queryByName(String name) {
        String sql = "select * from equipment where name=?";
        return queryForList(Equipment.class, sql, name);
    }

    @Override
    public List<Equipment> queryByBuydate(String year) {
        String sql = "select * from equipment where year(buydate)=?";
        return queryForList(Equipment.class, sql, year);
    }

    @Override
    public List<Equipment> queryByPosition(String position) {
        String sql = "select * from equipment where position=?";
        return queryForList(Equipment.class, sql, position);
    }

    @Override
    public List<Equipment> queryByManager(String manager) {
        String sql = "select * from equipment where manager=?";
        return queryForList(Equipment.class, sql, manager);
    }

    @Override
    public List<Equipment> queryByDepartment(String department) {
        String sql = "select * from equipment where department=?";
        return queryForList(Equipment.class, sql, department);
    }

    @Override
    public List<Equipment> queryBySpecification(String specification) {
        String sql = "select * from equipment where specification=?";
        return queryForList(Equipment.class, sql, specification);
    }

    @Override
    public List<Equipment> queryByPrice(String price) {
        String sql = "select * from equipment where price=?";
        return queryForList(Equipment.class, sql, price);
    }
}
