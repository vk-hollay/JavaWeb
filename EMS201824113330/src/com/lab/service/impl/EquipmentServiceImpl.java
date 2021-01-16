package com.lab.service.impl;

import com.lab.bean.Employee;
import com.lab.bean.Equipment;
import com.lab.dao.EquipmentDao;
import com.lab.dao.impl.EquipmentDaoImpl;
import com.lab.service.EquipmentService;

import java.util.List;

/**
 * @author 201824113330
 * @create 2020-12-07-21:42
 * @description
 */
public class EquipmentServiceImpl implements EquipmentService {

    private EquipmentDao equipmentDao = new EquipmentDaoImpl();

    @Override
    public List<Equipment> showAll() {
        return equipmentDao.queryAllEquipments();
    }

    @Override
    public int add(Equipment equipment) {
        return equipmentDao.addEquipment(equipment);
    }

    @Override
    public int delete(String id) {
        return equipmentDao.deleteEquipmentById(id);
    }

    @Override
    public int update(Equipment equipment, String oldId) {
        return  equipmentDao.updateEquipmentInfo(equipment, oldId);
    }

    @Override
    public Equipment queryById(String id) {
        return equipmentDao.queryById(id);
    }

    public List<Equipment> queryByCondition(String condition, String parameter) {
        List<Equipment> equipments  = null;
        if (condition.equals("name")) {
            equipments = equipmentDao.queryByName(parameter);
        } else if(condition.equals("specification")) {
            equipments = equipmentDao.queryBySpecification(parameter);
        } else if (condition.equals("price")) {
            equipments = equipmentDao.queryByPrice(parameter);
        } else if (condition.equals("position")) {
            equipments = equipmentDao.queryByPosition(parameter);
        } else if (condition.equals("manager")) {
            equipments = equipmentDao.queryByManager(parameter);
        } else if (condition.equals("buyDate")) {
            equipments = equipmentDao.queryByBuydate(parameter);
        }
        return equipments;

    }
}
