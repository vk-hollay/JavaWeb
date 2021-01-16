package com.lab.test;

import com.lab.bean.Equipment;
import com.lab.dao.EquipmentDao;
import com.lab.dao.impl.EquipmentDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author 201824113330
 * @create 2020-12-07-21:07
 * @description
 */
public class EquipmentDaoTest {

    EquipmentDao equipmentDao = new EquipmentDaoImpl();

    @Test
    public void queryAllEquipments() {
        List<Equipment> equipmentList = equipmentDao.queryAllEquipments();
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment.toString());
        }

    }

    @Test
    public void addEquipment() {
        Equipment equipment = new Equipment("708", "硬盘", "50*60", 25.0, "2020-12-2", "仓库A", "11", null);
        int i = equipmentDao.addEquipment(equipment);
        System.out.println(i);
    }

    @Test
    public void deleteEquipmentById() {
        int i = equipmentDao.deleteEquipmentById("1");
        System.out.println(i);
    }

    @Test
    public void updateEquipmentInfo() {
        //int i = equipmentDao.updateEquipmentInfo(new Equipment("2", "硬盘", "50*60",36.3, "2020-8-2", "仓库A", "11", "15236"));
        //System.out.println(i);
    }

    @Test
    public void queryById() {
        Equipment equipment = equipmentDao.queryById("2");
        System.out.println(equipment.toString());
    }

    @Test
    public void queryByName() {
        List<Equipment> equipmentList = equipmentDao.queryByName("硬盘");
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment.toString());
        }
    }

    @Test
    public void queryByBuydate() {
        List<Equipment> equipmentList = equipmentDao.queryByBuydate("2020");
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment.toString());
        }
    }

    @Test
    public void queryByPositon() {
    }

    @Test
    public void queryByManagerName() {
    }

    @Test
    public void queryByDepartmentName() {
    }
}