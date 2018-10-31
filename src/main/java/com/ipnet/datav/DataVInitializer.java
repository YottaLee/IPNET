package com.ipnet.datav;

import com.ipnet.datav.area.Area;
import com.ipnet.datav.area.AreaDao;
import com.ipnet.datav.area.AreaTranslator;
import com.ipnet.datav.data_item.DataItem;
import com.ipnet.datav.data_item.DataItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author lzb
 * @date 2018/11/1 0:19
 */
@Component
public class DataVInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private DataItemDao dataItemDao;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        this.initArea();
        this.initDataItem();

    }


    public void initArea(){
        try {
            String filePath = this.getClass().getClassLoader().getResource("datav/area_list.csv").getPath();
            List<Area> areaList = new AreaTranslator().initArea(filePath);
            this.areaDao.saveAll(areaList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDataItem(){
        String[] itemNames = {"growth","index"};
        for (String itemName : itemNames) {
            DataItem item = new DataItem();
            item.setId(itemName);
            item.setValue((int)(Math.random()*1000));
            this.dataItemDao.save(item);
        }

    }

}
