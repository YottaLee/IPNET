package com.ipnet.datav;

import com.ipnet.datav.area.Area;
import com.ipnet.datav.area.AreaDao;
import com.ipnet.datav.data_item.DataItem;
import com.ipnet.datav.data_item.DataItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lzb
 * @date 2018/11/1 0:41
 */
@Component
public class DatavUpdate {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private DataItemDao dataItemDao;

    @Scheduled(cron = "0 * * * * ?")//every minute
    public void updateArea(){

        int times = 10;
        List<Area> areaList = areaDao.findAll();
        while (times-->0){

            Area area = areaList.get(this.randomInt(areaList.size() - 1));
            area.setValue1(this.randomDouble(10));
            area.setValue2(this.randomDouble(10));
            this.areaDao.save(area);
            System.out.println(area);
        }

        System.out.println("update datav every minute");

    }

    @Scheduled(cron = "* * * * * ?")//every second
    public void updateDataItem(){
        List<DataItem> itemList = this.dataItemDao.findAll();
        itemList.forEach(dataItem -> {
            dataItem.setValue(this.randomInt(1000));
            this.dataItemDao.save(dataItem);
        });
    }

    private int randomInt(int limit){
        return (int)(Math.random() * limit);
    }

    private double randomDouble(double limit){
        return Math.random() * limit;
    }

}
