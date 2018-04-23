package cn.hdu.usv.dao.impl;

import cn.hdu.usv.dao.ShipInforDao;
import cn.hdu.usv.mavlink.mavlinkjava.common.ShipInformation;
import cn.hdu.usv.utils.Server2WebProtocol;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xiaoqiang
 * @create 2018-04-06 13:43
 */
public class ShipInforImplTest {

    ShipInforDao shipdaoDao = new ShipInforImpl();
    @Test
    public void saveShipInfor() {

        ShipInformation ship = new ShipInformation();
        ship.throttle = 4;
        ship.direction=3;
        ship.controlvol = 3;
        ship.tractionvol = 12;
        ship.shiptemp = 23;
        ship.watertemp = 22;
        ship.ph = 52;
        ship.latitude = 321;
        ship.longitude = 728;

        shipdaoDao.saveShipInfor(ship);
    }

    @Test
    public void getShipInfor() {
        ShipInformation ship = shipdaoDao.getShipInfor(1);
        JSONObject json = Server2WebProtocol.getSingleJsonObject(ship);
        System.out.println(json);
    }

    @Test
    public void getAll() {
    }

    @Test
    public void deleteShipInfor() {
    }

    @Test
    public void updateShipInfor() {

        ShipInformation ship = new ShipInformation();
        ship.throttle = 34;
        ship.direction=333;
        ship.controlvol = 311;
        ship.tractionvol = 12;
        ship.shiptemp = 232;
        ship.watertemp = 22;
        ship.ph = 2;
        ship.latitude = 321;
        ship.longitude = 7228;

        shipdaoDao.updateShipInfor(2, ship);
    }

    @Test
    public void getLastInfor() {

        ShipInformation ship  = shipdaoDao.getLastInfor();
        JSONObject json = Server2WebProtocol.getSingleJsonObject(ship);
        System.out.println(json);

    }

    @Test
    public void getPre3() {
    }

    @Test
    public void getpre5() {
    }

    @Test
    public void getpre7() {
    }

    @Test
    public void getPre1() {
    }

    @Test
    public void getSpecialData() {
    }
}